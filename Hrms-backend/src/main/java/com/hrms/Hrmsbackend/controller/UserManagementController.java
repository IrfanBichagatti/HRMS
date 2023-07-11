package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AdminEntity;
import com.hrms.Hrmsbackend.models.AvgAttendanceEntity;
import com.hrms.Hrmsbackend.models.ResignationEntity;
import com.hrms.Hrmsbackend.models.UserLeaveDetails;
import com.hrms.Hrmsbackend.service.ResignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public class UserManagementController {

    @Autowired
    ResignService resignService;

    @GetMapping("/getallresignrequest")
    public List<ResignationEntity> getallresigndetail() {
        return resignService.getallresin();
    }

    @GetMapping("/approveresign/{email}")
    public Map<String,String> approvrresign(@PathVariable String email) {
        return resignService.apprroverequest(email);
    }

    @GetMapping("/rejectresign/{email}")
    public Map<String,String>  rejectresign(@PathVariable String email) {
        return resignService.rejectresign(email);
    }

    @GetMapping("/getallresignedpeople")
    public List<ResignationEntity> getresigndata() {
        return resignService.getallresignedrequest();
    }

    @GetMapping("/getallnoticeperiodpeople")
    public List<ResignationEntity> getnoticeperioddata() {
        return resignService.getallnoticeperiodserviving();
    }

    @GetMapping("/getresignedcount")
    public List<Integer> getusercount(){
        return resignService.getallusercount();
    }
}
