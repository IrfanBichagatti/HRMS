package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProfileController {
    @Autowired
    FinancialDetailsService financialDetailsService;
    @Autowired
    ProfessionalDetailsService professionalDetailsService;
    @Autowired
    AdminApprovalService adminApprovalService;
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    UserContactService userContactService;



    @PutMapping("/uploadimage/{email}")
    public ResponseEntity<HttpStatus> uploadFileimage(@RequestParam("file") MultipartFile file, @PathVariable("email") String email) {

        try {
            adminService.uploadimage(file,email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllAdmincontactDetails")
    public List<AdminApproveContactEntity> getAllAdminDetails(){

        return adminApprovalService.getAllContactDetails();
    }

    @PostMapping("/adminupdateContactDetails")
    public Map<String,String> adminupdateFinancialDetails(@RequestBody Map<String,String>email){
        return adminApprovalService.adminupdateContactDetails(email);
    }
    @PostMapping("/rejectContactDetails")
    public Map<String,String>rejectFinancialDetails(@RequestBody Map<String,String>email){
        return adminApprovalService.rejectContactDetails(email);
    }

    @PostMapping("/approveContactDetails")
    public Map<String,String>approveFinancialDetails(@RequestBody Map<String,String>email){
        return adminApprovalService.approveContactDetails(email);
    }
    @GetMapping("/getAlladminprofessionaldetails")
    public List<AdminApproveProfessionalEntity>getAllProfessionalDetails(){

        return adminApprovalService.getAllprofessionalDetails();
    }

    @PostMapping("/adminupdateprofessionalDetails")
    public Map<String,String>adminupdateProfessioalDetails(@RequestBody Map<String,String>email){
        return adminApprovalService.adminupdateProfessionalDetails(email);
    }
    @PostMapping("/rejectprofessionalDetails")
    public Map<String,String>rejectprofessionalDetails(@RequestBody Map<String,String>email){
        return adminApprovalService.rejectprofessionalDetails(email);
    }

    @PostMapping("/approveprofessionalDetails")
    public Map<String,String>approveprofessionalDetails(@RequestBody Map<String,String>email){
        return adminApprovalService.approveprfessionalDetails(email);
    }

    @PostMapping("/rejectupdate")
    public Map<String,String> rejectupdate(@RequestBody Map<String, String> data){
        return  adminApprovalService.rejectadminupdate(data);
    }
    @PostMapping("/updaterequestforfinance")
    public Map<String,String> updatebyadminfinance(@RequestBody Map<String,String> data)
    {
        return adminApprovalService.UpdateReqforfinace(data);
    }

    @PostMapping("/rejectfinancupdate")
    public Map<String,String> rejectfinanceupdate(@RequestBody Map<String, String> data){
        return  adminApprovalService.rejectfinanceadminupdate(data);
    }
    @PostMapping("/approvefinancialupdaterequest")
    public Map<String,String> approvebyadminfinacial(@RequestBody Map<String,String> data)
    {
        return adminApprovalService.aprroveupdatereqfinancial(data);
    }
    @GetMapping("/getallpendingrequestfinance")
    public List<AdminApprovalEntitytwo> getallupdatereqfi()
    {
        return adminApprovalService.getallreqfinaance();
    }


    @PostMapping("/updaterequest")
    public Map<String,String> updatebyadmin(@RequestBody Map<String,String> data)
    {
        return adminApprovalService.UpdateReq(data);
    }
    @PostMapping("/approveupdaterequest")
    public Map<String,String> approvebyadmin(@RequestBody Map<String,String> data)
    {
        return adminApprovalService.aprroveupdatereq(data);
    }
    @GetMapping("/getallpendingrequest")
    public List<AdminApprovalEntity> getallupdatereq()
    {
        return adminApprovalService.getallreq();
    }

    @PutMapping("/updatebasicdetail/{id}")
    public UserEntity updateEmployee(@PathVariable String id, @RequestBody UserEntity userEntity) {
        return userService.updateuserinfo(id, userEntity);
    }

    @PostMapping("/addcontactdetail")
    public ResponseEntity<UserContactEntity> addEmployee(@RequestBody UserContactEntity userContactEntity) {
        UserContactEntity userContactEntity1 = userContactService.usercontactdetail(userContactEntity);
        return new ResponseEntity<>(userContactEntity1, HttpStatus.CREATED);
    }

    @GetMapping("/getconatctdetail/{id}")
    public Optional<UserContactEntity> getcontactdetail(@PathVariable("id") String id) {
        Optional<UserContactEntity> userContactEntity1 = userContactService.getcontactdetail(id);
        return userContactEntity1;
    }

    @PutMapping("/updatecontactdetail/{id}")
    public UserContactEntity updateContactDetail(@PathVariable String id, @RequestBody UserContactEntity userContactEntity) {
        return userContactService.updateContactinfo(id, userContactEntity);
    }

    @PostMapping("/AddFinancialDetails")
    public FinancialDetails addFinancialDetails(@RequestBody FinancialDetails financialDetails) {
        return financialDetailsService.saveFinancialDetails(financialDetails);
    }

    @GetMapping("/getFinancialDetails/{id}")
    public Optional<FinancialDetails> getfinancialDetails(@PathVariable String id) {
        return financialDetailsService.getFinancialByID(id);
    }

    @PostMapping("/AddProfessionalDetails")
    public ProfessionalDetails addProfessionalDetails(@RequestBody ProfessionalDetails professionalDetails) {
        return professionalDetailsService.saveprofessionalDetails(professionalDetails);
    }


    @PutMapping("/updateProfessionalDetails/{id}")
    public ProfessionalDetails updateprofessionalDetails(@PathVariable String id, @RequestBody ProfessionalDetails professionalDetails) {
        return professionalDetailsService.UpdateProfessionalDetailsbyId(id, professionalDetails);
    }

    @PutMapping("/updateFinancialDetails/{id}")
    public FinancialDetails updatefinancialDetails(@PathVariable String id, @RequestBody FinancialDetails financialDetails) {

        return financialDetailsService.UpdateFinancialDetailsbyId(id, financialDetails);
    }
}
