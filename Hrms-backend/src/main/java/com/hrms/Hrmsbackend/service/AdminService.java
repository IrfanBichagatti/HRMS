package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.helper.Helper;
import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.in;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    AdminUpdateRepo adminUpdateRepo;

    @Autowired
    AdminCreateJobRepo adminCreateJobRepo;

    @Autowired
    EmployeeAssetRepo employeeAssetRepo;

    @Autowired
    private JoblistRepo joblistRepo;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public AdminService(){}

    public AdminService(AdminRepo adminRepo){this.adminRepo=adminRepo;}

    public void admin(AdminEntity adminEntity){
        String encode=this.passwordEncoder.encode(adminEntity.getPassword());
        adminEntity.setPassword(encode);
        String confpassword=this.passwordEncoder.encode(adminEntity.getConfirmpassword());
        adminEntity.setConfirmpassword(confpassword);
        adminRepo.save(adminEntity);
    }

    public Iterable<AdminEntity> showAllUsers(){
        return adminRepo.findAll();
    }

    public void updatePass(Map<String, String> userInput)  {
        AdminEntity adminEntity=adminRepo.findByEmail(userInput.get("email"));

        adminEntity.setEmail(userInput.get("email"));
        adminEntity.setPassword(userInput.get("password"));
        adminEntity.setConfirmpassword(userInput.get("confirmpassword"));
        String encode=this.passwordEncoder.encode(adminEntity.getPassword());
        adminEntity.setPassword(encode);
        String confpassword=this.passwordEncoder.encode(adminEntity.getConfirmpassword());
        adminEntity.setConfirmpassword(confpassword);
        adminRepo.save(adminEntity);
    }

    public AdminEntity getOne(String id) {
        return adminRepo.findByEmail(id);
    }

    public AdminEntity updateuserinfo(AdminApprovalEntity adminApprovalEntity) {
        String email = adminApprovalEntity.getEmail();
        AdminEntity adminEntity = adminRepo.findByEmail(email);
        adminEntity.setContactnumber(adminApprovalEntity.getContactno());

        adminEntity.setEmail(adminApprovalEntity.getEmail());
        adminEntity.setDepartment(adminApprovalEntity.getDpartment());
        adminEntity.setContactnumber(adminApprovalEntity.getPhoneno());
        adminEntity.setRole(adminApprovalEntity.getRole());
        adminEntity.setRelation(adminApprovalEntity.getRelation());
        adminEntity.setStatus(adminApprovalEntity.getStatus());
        adminEntity.setContactname(adminApprovalEntity.getContactname());
        adminEntity.setContactno(adminApprovalEntity.getContactno());
        adminUpdateRepo.deleteById(email);
        return adminRepo.save(adminEntity);
    }

    public AdminEntity uploadimage(MultipartFile file, String email) throws IOException {
        AdminEntity adminEntity = adminRepo.findByEmail(email);

        try {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                adminEntity.setName(filename);
                adminEntity.setData(file.getBytes());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        return adminRepo.save(adminEntity);
    }


    public AdminEntity getFile(String email) {
        return  adminRepo.findByEmail(email);
    }


    public AdminCreateJob adminCreateJob(@RequestBody AdminCreateJob adminCreateJob) {
        return adminCreateJobRepo.save(adminCreateJob);
    }



    public AdminCreateJob updateJobdetailbysno(int sno, AdminCreateJob adminCreateJob) {
        AdminCreateJob creatjob1= adminCreateJobRepo.findById(sno)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + sno));
        creatjob1.setSno(adminCreateJob.getSno());
        creatjob1.setTitle(adminCreateJob.getTitle());
        creatjob1.setNumberofPositions(adminCreateJob.getNumberofPositions());
        creatjob1.setOpenJobs(adminCreateJob.getOpenJobs());
        creatjob1.setJobProfile(adminCreateJob.getJobProfile());
        creatjob1.setTotalExperience(adminCreateJob.getTotalExperience());
        creatjob1.setTechnologies(adminCreateJob.getTechnologies());
        creatjob1.setApplication(adminCreateJob.getApplication());
        creatjob1.setShortlist(adminCreateJob.getShortlist());
        creatjob1.setScreening(adminCreateJob.getScreening());
        creatjob1.setInterview(adminCreateJob.getInterview());
        creatjob1.setOffered(adminCreateJob.getOffered());
        creatjob1.setConfirmed(adminCreateJob.getConfirmed());
        return adminCreateJobRepo.save(adminCreateJob);
    }


    public void save(MultipartFile file) {

        try {
            List<JobEntity> jobEntities = Helper.convertExcelToListOfProduct(file.getInputStream());
            this.joblistRepo.saveAll(jobEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
