package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.OnboardingDocEntity;
import com.hrms.Hrmsbackend.repo.OnboardingDocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class OnboardingDocService {
    @Autowired
    OnboardingDocRepo onboardingDocRepo;


    public OnboardingDocEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        OnboardingDocEntity onboardingDocEntity = new OnboardingDocEntity(fileName,file.getContentType(),file.getBytes());
        onboardingDocEntity.setStatus("pending");
        return onboardingDocRepo.save(onboardingDocEntity);
    }

    public OnboardingDocEntity getFile(String name){
        return onboardingDocRepo.findById(name).get();
    }
    public Stream<OnboardingDocEntity> getAllFiles()
    {
        return onboardingDocRepo.findAll().stream();
    }
    //later set pending
//    public OnboardingDocEntity onboardingform(OnboardingDocEntity onboardingDocEntity){
//        onboardingDocEntity.setStatus("pending");
//        return onboardingDocRepo.save(onboardingDocEntity);
//    }

    public List<OnboardingDocEntity> onboardingform1(){
        List<OnboardingDocEntity> allonboardform = onboardingDocRepo.findAll();
        List<OnboardingDocEntity> onboard =new ArrayList<>();

        Iterator<OnboardingDocEntity> iterator= allonboardform.iterator();
        while (iterator.hasNext()){
            OnboardingDocEntity li=(OnboardingDocEntity) iterator.next();
            if("pending".equals(li.getStatus())){
                onboard.add(li);
            }
        }
        Collections.reverse(onboard);
        return onboard;
    }
}
