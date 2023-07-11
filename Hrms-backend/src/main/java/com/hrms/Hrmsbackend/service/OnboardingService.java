package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.AdminEntity;
import com.hrms.Hrmsbackend.models.OnboardingEntity;
import com.hrms.Hrmsbackend.repo.OnboardingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OnboardingService {

    @Autowired
    OnboardingRepo onboardingRepo;


    public OnboardingEntity onboardingform(OnboardingEntity onboardingEntity){
        onboardingEntity.setStatus("pending");
        return onboardingRepo.save(onboardingEntity);
    }

    public List<OnboardingEntity> onboardingform1(){
        List<OnboardingEntity> allonboardform = onboardingRepo.findAll();
        List<OnboardingEntity> onboard =new ArrayList<>();

        Iterator<OnboardingEntity> iterator= allonboardform.iterator();
        while (iterator.hasNext()){
            OnboardingEntity li=(OnboardingEntity) iterator.next();
            if("pending".equals(li.getStatus())){
                onboard.add(li);
            }
        }
        Collections.reverse(onboard);
        return onboard;
    }

    public OnboardingEntity acceptonboard(String email) {
        OnboardingEntity onboardingEntity = onboardingRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        onboardingEntity.setStatus("Approved");
        return  onboardingRepo.save(onboardingEntity);
    }

    public OnboardingEntity rejectonboard(String email) {
        OnboardingEntity onboardingEntity = onboardingRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        onboardingEntity.setStatus("Rejected");
        return  onboardingRepo.save(onboardingEntity);
    }

    public Iterable<OnboardingEntity> AllUsers() {
        return onboardingRepo.findAll();
    }
    public OnboardingEntity acceptdoc(String email) {
        OnboardingEntity onboardingEntity = onboardingRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        onboardingEntity.setStatus("Approved");
        return onboardingRepo.save(onboardingEntity);

    }

}
