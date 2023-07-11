package com.hrms.Hrmsbackend.controller;


import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.repo.*;
import com.hrms.Hrmsbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.EOFException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class HomeController {

    @Autowired
    AdminService adminService;
    @Autowired
    private ResourceFileStorageService storageService;
    @Autowired
    private ResourceEntityRepository resourceEntityRepository;
    @Autowired
    private  ResignRepo resignRepo;
    @Autowired
    UserService userService;
    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    private DocsEmpRepo docsEmpRepo;
    @Autowired
    private DocsManagerRepo docsManagerRepo;
    @Autowired
    private DocsEmpService docsEmpService;
    @Autowired
    private DocsAdminRepo docsAdminRepo;
    @Autowired
    private DocsAdminService docsAdminService;

    @Autowired
    private DocsManagerService docsManagerService;

    @Autowired
    ResignService resignService;
    @Autowired
    UserLeaveRequestRepo userLeaveRequestRepo;

    @Autowired
    OnboardingService onboardingService;
    @Autowired
    OnboardingDocService onboardingDocService;

    @Autowired
    OnboardingAdminService onboardingAdminService;

    @Autowired
    OnboardingAdminRepo onboardingAdminRepo;

    @Autowired
    JobRefrelRepo jobRefrelRepo;

    @Autowired
    private JavaMailSender mailSender;
    int otp;



    @PostMapping("/onboarding-form")
    public String onboard(@RequestBody OnboardingEntity onboardingEntity)
    {
        onboardingService.onboardingform(onboardingEntity);
        return "Details Submitted Successfully";
    }
    @GetMapping("/getonboardingdetails")
    public List<OnboardingEntity> getonboarding(){
        return onboardingService.onboardingform1();
    }
    @GetMapping("/approveonboard/{email}")
    public OnboardingEntity approveraccept(@PathVariable String email){
        return onboardingService.acceptonboard(email);
    }

    @PostMapping("/rejectonboard/{email}")
    public OnboardingEntity rejectonboard(@PathVariable String email){
        return onboardingService.rejectonboard(email);
    }

    @GetMapping("/onboardDet")
    public Iterable<OnboardingEntity> AllUsers() {
        return onboardingService.AllUsers();
    }
    @PostMapping("/Docupload-emp")
    public ResponseEntity<ResponseMessage> uploadtoemp(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            onboardingDocService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/getuserinfo2/{id}")
    public Optional<UserEntity> getOneuser2(@PathVariable("id") String id) {
        return userService.getOne(id);
    }
    @GetMapping("/Docfiles-emp")
    public ResponseEntity<List<ResponseFile>> getlistofemp() {
        List<ResponseFile> files = onboardingDocService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/Docfiles-emp/")
                    .path(dbFile.getEmail())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getEmail(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("Docfiles-emp/{id}")
    public ResponseEntity<byte[]> getDocFilesEmp(@PathVariable String id) {
        System.out.println(id);
        OnboardingDocEntity onboardingDocEntity = onboardingDocService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + onboardingDocEntity.getName() + "\"")
                .body(onboardingDocEntity.getData());
    }

    @GetMapping("/OnboardDocApprove/{id}")
    public OnboardingAdmin approvedocfile(@PathVariable("id") String id){
        return onboardingAdminService.acceptonboard(id);
    }

    @GetMapping("/OnboardDocReject/{id}")
    public OnboardingAdmin rejectdocfile(@PathVariable("id") String id){
        return onboardingAdminService.rejectonboard(id);
    }
    @GetMapping("/getDocStatus")
    public List<OnboardingAdmin> getStatus(){
        return onboardingAdminRepo.findAll();
    }

    @PostMapping("/Docupload-admin")
    public ResponseEntity<ResponseMessage> uploadtoAdmin(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            onboardingAdminService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/Docfiles-admin")
    public ResponseEntity<List<ResponseFile>> getlistAdmin() {
        List<ResponseFile> files = onboardingAdminService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/Docfiles-admin/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),

                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/Docfiles-admin/{id}")
    public ResponseEntity<byte[]> getDocFilese(@PathVariable String id) {
        System.out.println(id);
        OnboardingAdmin onboardingAdmin= onboardingAdminService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + onboardingAdmin.getName() + "\"")
                .body(onboardingAdmin.getData());
    }

    @DeleteMapping("/Docfiles-admin/{id}")
    public ResponseEntity<HttpStatus> deleteOnboardFileById(@PathVariable("id") String id) {
        try {
            onboardingAdminRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/Docfiles-admin")
    public ResponseEntity<HttpStatus> deleteOnboardAllFiles() {
        try {
            onboardingAdminRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/ClockIn")
    public UserAttendance ClockIn(@RequestBody Map<String, String> userInput) {
        System.out.println("*******ClockInRunning");
        return userService.ClockIn(userInput);
    }


    @GetMapping("/startClock")
    public String startClock() throws InterruptedException {

        return userService.startClock();
    }

    @GetMapping("/stopClock")
    public String stopClock() throws InterruptedException {

        return userService.stopClock();
    }

    @GetMapping("/getTime")
    public Map<String,String> getTime() throws InterruptedException {

        return userService.getTime();
    }


    @GetMapping("/getUserAttendanceStatus1")
    public List<UserAttendance> getUserAttendanceStatus(){

        return userService.getUserAttendanceStatus1();
    }
    @GetMapping("/getUserCount")
    public  Map<String,String> getUserCount(){

        return userService.getUserCount();
    }
    @GetMapping("/getUserCountabsent")
    public  Map<String,String> getUserCountabsent(){

        return userService.getUserCountabsent();
    }

    @GetMapping("/getUserAttendance/{dat}")
    public List<UserAttendance> getUserAttendance(@PathVariable String dat , @RequestParam("email") String Username) {

        return userService.getUserAttendance(Username,dat);
    }
    @GetMapping("/getEmployeAttendanceTracker")
    public List<UserAttendance> getEmployeAttendanceTracker( @RequestParam("firstname") String firstname) {

        return userService.getEmployeAttendanceTracker(firstname);
    }

    @DeleteMapping("/delete/{sno}")
    public ResponseEntity<HttpStatus> deleteRequestById(@PathVariable("sno") int sno) {
        try {
            userLeaveRequestRepo.deleteById(sno);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/alert")
    public List<AlertData> getAlert(@RequestParam String email)
    {
        return userService.Alert(email);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        System.out.println(id);
        ResourceEntity resourceEntity = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resourceEntity.getName() + "\"")
                .body(resourceEntity.getData());
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<HttpStatus> deleteFileById(@PathVariable("id") String id) {
        try {
            resourceEntityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/files")
    public ResponseEntity<HttpStatus> deleteAllFiles() {
        try {
            resourceEntityRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload-to-emp")
    public ResponseEntity<ResponseMessage> uploademp(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            docsEmpService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files-of-emp")
    public ResponseEntity<List<ResponseFile>> getlistemp() {
        List<ResponseFile> files = docsEmpService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files-of-emp/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files-of-emp/{id}")
    public ResponseEntity<byte[]> getFileofEmp(@PathVariable String id) {
        DocsEmpEntity docsEmpEntity = docsEmpService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docsEmpEntity.getName() + "\"")
                .body(docsEmpEntity.getData());
    }


    @PostMapping("/upload-to-manager")
    public ResponseEntity<ResponseMessage> uploadManager(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            docsManagerService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files-of-manager")
    public ResponseEntity<List<ResponseFile>> getlistmanager() {
        List<ResponseFile> files = docsManagerService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files-of-manager/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files-of-manager/{id}")
    public ResponseEntity<byte[]> getFileofManager(@PathVariable String id) {
        DocsManagerEntity docsManagerEntity = docsManagerService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docsManagerEntity.getName() + "\"")
                .body(docsManagerEntity.getData());
    }


    @PostMapping("/upload-to-admin")
    public ResponseEntity<ResponseMessage> uploadAdmin(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            docsAdminService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files-of-admin")
    public ResponseEntity<List<ResponseFile>> getListAdmin() {
        List<ResponseFile> files = docsAdminService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();
            System.out.println(fileDownloadUri);
            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files-of-admin/{id}")
    public ResponseEntity<byte[]> getFileOfAdmin(@PathVariable String id) {
        DocsAdminEntity docsAdminEntity = docsAdminService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docsAdminEntity.getName() + "\"")
                .body(docsAdminEntity.getData());
    }




    @DeleteMapping("/files-emp/{id}")
    public ResponseEntity<HttpStatus> deleteFileEmpById(@PathVariable("id") String id) {
        try {
            docsEmpRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/files-emp")
    public ResponseEntity<HttpStatus> deleteAllFilesEmp() {
        try {
            docsEmpRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/files-Manager/{id}")
    public ResponseEntity<HttpStatus> deleteFileManagerById(@PathVariable("id") String id) {
        try {
            docsManagerRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/files-Manager")
    public ResponseEntity<HttpStatus> deleteAllFilesManager() {
        try {
            docsManagerRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/files-Admin/{id}")
    public ResponseEntity<HttpStatus> deleteFileAdminById(@PathVariable("id") String id) {
        try {
            docsAdminRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/files-Admin")
    public ResponseEntity<HttpStatus> deleteAllFilesAdmin() {
        try {
            docsAdminRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-one/{id}")
    public Optional<UserEntity> getOne(@PathVariable("id") String id) {
        return userService.getOne(id);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addEmployee(@RequestBody UserEntity userEntity) {
        UserEntity userEntity1 = userService.addEmployee(userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }


    @GetMapping("/getdateofbirth")
    public List<UserEntity>  getbirthinfo()
    {
        return userService.findBirthinfo();
    }










    /////professional and contact ////////////////////////////






    @GetMapping("/filess/{email}")
    public AdminEntity getFileimage(@PathVariable String email) {
        return adminService.getFile(email);
    }



    @PostMapping("/UserAppliedJob")
    public UserApplyJob applyforjob(@RequestBody UserApplyJob userApplyJob) {
        return userService.applyforjob(userApplyJob);

    }

    @GetMapping("/UserAppliedJob1")
    public List<UserApplyJob> getJobDetailRequest(){
        return userService.getjobrequestdetail();
    }



    @GetMapping("/referal")
    public List<JobReferalEntity> getReferal() {
        return userService.getreferaldetail();
    }


    @GetMapping("/getUserworkinghr")
    public String getUserWorkingHr1(){

        return userService.getUserWorkingHr();
    }

    @GetMapping("/getUserdailyavgclockin")
    public String getUserdailyavgclockin(@RequestParam String date1) throws ParseException {

        return userService.getUserdailyavgclockin(date1);
    }
}
