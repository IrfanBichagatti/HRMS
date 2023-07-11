package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AdminCreateJob;
import com.hrms.Hrmsbackend.models.JobEntity;
import com.hrms.Hrmsbackend.models.JobReferalEntity;
import com.hrms.Hrmsbackend.repo.AdminCreateJobRepo;
import com.hrms.Hrmsbackend.repo.Applyforjob;
import com.hrms.Hrmsbackend.repo.JobRefrelRepo;
import com.hrms.Hrmsbackend.repo.JoblistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class RecruitmentService {
    @Autowired
    private AdminCreateJobRepo adminCreateJobRepo;
    @Autowired
    Applyforjob applyforjob;
    @Autowired
    private JoblistRepo joblistRepo;
    @Autowired
    JobRefrelRepo jobRefrelRepo;

    public Map<String, String> getOpenJobs() {
        Map<String,String> map=new HashMap<>();
        map.put("openJobs", String.valueOf(adminCreateJobRepo.findAll().size()));
        map.put("TotalApplication", String.valueOf(applyforjob.findAll().size()));
        return map;

    }

    public List<AdminCreateJob> CreatejobList() {
        return adminCreateJobRepo.findAll();
    }

    public List<JobEntity> getAllProducts() {
        return this.joblistRepo.findAll();
    }

    public JobReferalEntity createjob(JobReferalEntity jobReferal) {
        return jobRefrelRepo.save(jobReferal);
    }

}
