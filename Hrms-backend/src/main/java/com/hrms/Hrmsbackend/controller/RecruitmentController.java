package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AdminCreateJob;
import com.hrms.Hrmsbackend.models.JobEntity;
import com.hrms.Hrmsbackend.models.JobReferalEntity;
import com.hrms.Hrmsbackend.service.AdminService;
import com.hrms.Hrmsbackend.service.RecruitmentService;
import com.hrms.Hrmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
public class RecruitmentController {
    @Autowired
    UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    RecruitmentService recruitmentService;

    @GetMapping("/getopenjob")
    public Map<String,String> getOpenJobs() {
        return recruitmentService.getOpenJobs();
    }

    @GetMapping("/getJobcreatDetail")
    public List<AdminCreateJob> getcreatejobInfo()
    {
        return recruitmentService.CreatejobList();
    }

    @GetMapping("/product")
    public List<JobEntity> getAllProduct() {
        return this.recruitmentService.getAllProducts();
    }

    @PostMapping("/jobreferal")
    public JobReferalEntity createjob(@RequestBody JobReferalEntity jobReferal){
        return recruitmentService.createjob(jobReferal);
    }
}
