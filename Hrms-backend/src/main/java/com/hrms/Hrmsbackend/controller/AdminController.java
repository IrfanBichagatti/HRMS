package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.helper.Helper;
import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.repo.AdminCreateJobRepo;
import com.hrms.Hrmsbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminCreateJobRepo adminCreateJobRepo;

    @PostMapping("/AdminRecruitment")
    private AdminCreateJob createjob(@RequestBody AdminCreateJob adminCreateJob){
        return adminService.adminCreateJob(adminCreateJob);
    }


    @DeleteMapping("/deleteJob/{sno}")
    public AdminCreateJob deleteRequestByIed(@PathVariable("sno") int sno) {
        adminCreateJobRepo.deleteById(sno);
        return null;
    }


    @GetMapping("/getJobcreatDetail1/{sno}")
    public Optional<AdminCreateJob> getcreatejobInfo1(@PathVariable("sno") int sno)
    {
        return adminCreateJobRepo.findById(sno);
    }

    @PutMapping("/updateJobdetail/{sno}")
    public AdminCreateJob updateJobdetail(@PathVariable int sno, @RequestBody AdminCreateJob adminCreateJob) {
        return adminService.updateJobdetailbysno(sno, adminCreateJob);
    }

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true

            this.adminService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }




}


