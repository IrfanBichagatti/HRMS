package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.ResignationEntity;
import com.hrms.Hrmsbackend.service.ResignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public class ExitManagementController {

    @Autowired
    private ResignService resignService;

    @PostMapping("/applyforresign")
    public ResignationEntity applyresign(@RequestBody ResignationEntity resignationEntity){
        return (ResignationEntity) resignService.applyforresign(resignationEntity);
    }

    @GetMapping("/updatenoticeperiod")
    public Map<String,String> updateresign(){
        return  resignService.updatenoticeperiod();
    }

    @GetMapping("/getallrequest/{email}")
    public List<ResignationEntity> geturrequest(@PathVariable String email){
        return resignService.getresignrequest(email);
    }
}
