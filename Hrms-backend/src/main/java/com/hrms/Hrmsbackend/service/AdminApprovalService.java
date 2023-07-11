package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.AdminApprovalEntity;
import com.hrms.Hrmsbackend.models.AdminApprovalEntitytwo;
import com.hrms.Hrmsbackend.models.AdminApproveContactEntity;
import com.hrms.Hrmsbackend.models.AdminApproveProfessionalEntity;
import com.hrms.Hrmsbackend.repo.AdminContactRepo;
import com.hrms.Hrmsbackend.repo.AdminFinanceRepo;
import com.hrms.Hrmsbackend.repo.AdminProfessionalRepo;
import com.hrms.Hrmsbackend.repo.AdminUpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AdminApprovalService {

    @Autowired
    AdminUpdateRepo adminUpdateRepo;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    AdminFinanceRepo adminFinanceRepo;
    @Autowired
    AdminContactRepo adminContactRepo;
    @Autowired
    AdminProfessionalRepo adminProfessionalRepo;
    @Autowired
    UserContactService userContactService;


    @Autowired
    ProfessionalDetailsService professionalDetailsService;


    public Map<String, String> UpdateReq(Map<String, String> data) {
        Map<String,String> map = new HashMap<>();
        AdminApprovalEntity adminApprovalEntity =  new AdminApprovalEntity();
        adminApprovalEntity.setEmail(data.get("email"));
        adminApprovalEntity.setDob(data.get("dob"));
        adminApprovalEntity.setRole(data.get("role"));
        adminApprovalEntity.setDpartment(data.get("depart"));
        adminApprovalEntity.setRelation(data.get("relation"));
        adminApprovalEntity.setStatus(data.get("status"));
        adminApprovalEntity.setContactno(data.get("conatctno"));
        adminApprovalEntity.setContactname(data.get("contactname"));
        adminApprovalEntity.setPhoneno(data.get("phoneno"));
        adminApprovalEntity.setStatus1("pending");
        webSocketService.sendMessage("update");
        adminUpdateRepo.save(adminApprovalEntity);
        map.put("status","pending");

        return  map;
    }
    public Map<String, String> aprroveupdatereq(Map<String, String> data) {
        String email = data.get("email");
        AdminApprovalEntity adminApprovalEntity =adminUpdateRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));

        Map<String,String> map = new HashMap<>();

//        adminApprovalEntity.setStatus1("Approved");
//        adminUpdateRepo.save(adminApprovalEntity);
        map.put("status","Approved");
        adminService.updateuserinfo(adminApprovalEntity);
        return  map;

    }

    public List<AdminApprovalEntity> getallreq() {
        return adminUpdateRepo.findAll();
    }

    public Map<String, String> rejectadminupdate(Map<String, String> data) {
        String email = data.get("email");

        AdminApprovalEntity adminApprovalEntity = adminUpdateRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        HashMap<String, String> map = new HashMap<>();
//        adminApprovalEntity.setStatus1("Rejected");
        adminUpdateRepo.deleteById(email);
//        adminUpdateRepo.save(adminApprovalEntity);
        map.put("status", "Rejected");
        return map;
    }


    public Map<String, String> UpdateReqforfinace(Map<String, String> data) {
        Map<String,String> map = new HashMap<>();
        AdminApprovalEntitytwo adminApprovalEntitytwo =  new AdminApprovalEntitytwo();
        adminApprovalEntitytwo.setEmail(data.get("email"));
        adminApprovalEntitytwo.setName_as_per_name(data.get("pan_name"));
        adminApprovalEntitytwo.setPannumber(data.get("pannumber"));
        adminApprovalEntitytwo.setAdharname(data.get("aadhar_name"));
        adminApprovalEntitytwo.setAdharno(data.get("adharno"));
        adminApprovalEntitytwo.setBankname(data.get("bank_name"));
        adminApprovalEntitytwo.setAccountno(data.get("accountno"));
        adminApprovalEntitytwo.setIfsccode(data.get("ifsccode"));
        adminApprovalEntitytwo.setNameasperbank(data.get("nameasperbank"));
        adminApprovalEntitytwo.setStatus("pending");
        webSocketService.sendMessage("update");

        adminFinanceRepo.save(adminApprovalEntitytwo);
        map.put("status","pending");

        return  map;

    }

    public Map<String, String> rejectfinanceadminupdate(Map<String, String> data) {
        String email = data.get("email");

        AdminApprovalEntitytwo adminApprovalEntitytwo = adminFinanceRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        HashMap<String, String> map = new HashMap<>();
//        adminApprovalEntity.setStatus1("Rejected");
        adminFinanceRepo.deleteById(email);
//        adminUpdateRepo.save(adminApprovalEntity);
        map.put("status", "Rejected");
        return map;
    }

    public Map<String, String> aprroveupdatereqfinancial(Map<String, String> data) {
        String email = data.get("email");
        AdminApprovalEntitytwo adminApprovalEntitytwo =adminFinanceRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));

        Map<String,String> map = new HashMap<>();

//        adminApprovalEntity.setStatus1("Approved");
//        adminUpdateRepo.save(adminApprovalEntity);

        map.put("status","Approved");

        userService.updateuserfinainfo(adminApprovalEntitytwo);

        return  map;
    }

    public List<AdminApprovalEntitytwo> getallreqfinaance() {
        return adminFinanceRepo.findAll();
    }
    //////////////// professional
    public List<AdminApproveContactEntity> getAllContactDetails() {
        return adminContactRepo.findAll();
    }

    public Map<String, String> adminupdateContactDetails(Map<String, String> data) {
        Map<String,String> map= new HashMap<>();
        AdminApproveContactEntity adminApproveContactEntity=new AdminApproveContactEntity();
        adminApproveContactEntity.setEmail(data.get("email"));
        adminApproveContactEntity.setAddress(data.get("address"));
        adminApproveContactEntity.setAddress_line_two(data.get("address_line_two"));
        adminApproveContactEntity.setBlood_group(data.get("blood_group"));
        adminApproveContactEntity.setCity(data.get("city"));
        adminApproveContactEntity.setState(data.get("state"));
        adminApproveContactEntity.setCountry(data.get("country"));
        adminApproveContactEntity.setCity_pin_code(data.get("city_pin_code"));
        adminApproveContactEntity.setStatus("pending");
        webSocketService.sendMessage("update");
        adminContactRepo.save(adminApproveContactEntity);
        map.put("status","pending");
        return map;
    }

    public Map<String, String> rejectContactDetails(Map<String, String> data) {
        String email=data.get("email");
        AdminApproveContactEntity adminApproveContactEntity=adminContactRepo.findById(email)
                .orElseThrow(()->new ResourceNotFoundException("email doesnot exit"+email));
        Map<String,String>   map=new HashMap<>();
        adminContactRepo.deleteById(email);
        map.put("status","Rejected");
        return map;
    }

    public Map<String, String> approveContactDetails(Map<String, String> data) {
        String email=data.get("email");
        AdminApproveContactEntity adminApproveContactEntity=adminContactRepo.findById(email)
                .orElseThrow(()->new ResourceNotFoundException("email not found"+email));
        Map<String,String> map =new HashMap<>();
        map.put("status","Approved");
        userContactService.updateContactinfo1(adminApproveContactEntity);
        return map;

    }
    /////////////////////////////////////////////////////////////
    ///////////////////////////////////
    public List<AdminApproveProfessionalEntity> getAllprofessionalDetails() {
        return adminProfessionalRepo.findAll();
    }

    public Map<String, String> adminupdateProfessionalDetails(Map<String, String> data) {
        Map<String,String> map= new HashMap<>();
        AdminApproveProfessionalEntity adminApproveProfessionalEntity=new AdminApproveProfessionalEntity();
        adminApproveProfessionalEntity.setEmail(data.get("email"));
        adminApproveProfessionalEntity.setDegree(data.get("degree"));
        adminApproveProfessionalEntity.setSpecification(data.get("specification"));
        adminApproveProfessionalEntity.setCollege(data.get("college"));
        adminApproveProfessionalEntity.setPrimary_skill(data.get("primary_skills"));
        adminApproveProfessionalEntity.setSecondary_skill(data.get("secondary_skills"));
        adminApproveProfessionalEntity.setOrganization_name(data.get("organization_name"));
        adminApproveProfessionalEntity.setstatus("pending");
        webSocketService.sendMessage("update");
        adminProfessionalRepo.save(adminApproveProfessionalEntity);
        map.put("status","pending");
        return map;
    }

    public Map<String, String> rejectprofessionalDetails (Map<String, String> data) {
        String email=data.get("email");
        AdminApproveProfessionalEntity adminApproveProfessionalEntity=adminProfessionalRepo.findById(email)
                .orElseThrow(()->new ResourceNotFoundException("email doesnot exit"+email));
        Map<String,String>   map=new HashMap<>();
        adminProfessionalRepo.deleteById(email);
        map.put("status","Rejected");
        return map;
    }

    public Map<String, String> approveprfessionalDetails(Map<String, String> data) {
        String email=data.get("email");
        AdminApproveProfessionalEntity adminApproveContactEntity=adminProfessionalRepo.findById(email)
                .orElseThrow(()->new ResourceNotFoundException("email not found"+email));
        Map<String,String> map =new HashMap<>();
        map.put("status","Approved");
        professionalDetailsService.UpdateProfessionalDetailsbyId(adminApproveContactEntity);
        return map;

    }
}
