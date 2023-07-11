package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.OnboardingAdmin;
import com.hrms.Hrmsbackend.repo.OnboardingAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class OnboardingAdminService {
    @Autowired
    OnboardingAdminRepo onboardingAdminRepo;

    public OnboardingAdmin store(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        OnboardingAdmin onboardingAdmin = new OnboardingAdmin(fileName,file.getContentType(),file.getBytes());
        return onboardingAdminRepo.save(onboardingAdmin);
    }
    public OnboardingAdmin getFile(String id){
        return onboardingAdminRepo.findById(id).get();
    }
    public Stream<OnboardingAdmin> getAllFiles()
    {
        return onboardingAdminRepo.findAll().stream();
    }

    public OnboardingAdmin acceptonboard(String id) {
        OnboardingAdmin onboardingAdmin = onboardingAdminRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        onboardingAdmin.setStatus("Approved");
        return  onboardingAdminRepo.save(onboardingAdmin);
    }

    public OnboardingAdmin rejectonboard(String id) {
        OnboardingAdmin onboardingAdmin = onboardingAdminRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        onboardingAdmin.setStatus("Rejected!! Incorrect documents please reupload");
        return  onboardingAdminRepo.save(onboardingAdmin);
    }
}
