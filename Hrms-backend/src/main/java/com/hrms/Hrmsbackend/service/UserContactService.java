package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.AdminApproveContactEntity;
import com.hrms.Hrmsbackend.models.UserContactEntity;
import com.hrms.Hrmsbackend.models.UserEntity;
import com.hrms.Hrmsbackend.repo.AdminContactRepo;
import com.hrms.Hrmsbackend.repo.UserContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContactService {
    @Autowired
    UserContactRepo userContactRepo;
    @Autowired
    AdminContactRepo adminContactRepo;


    public Optional <UserContactEntity> getcontactdetail(String id) {
        return userContactRepo.findById(id);
    }

    public UserContactEntity updateContactinfo(String id, UserContactEntity userContactEntity) {
        UserContactEntity userContactEntity1 = userContactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        userContactEntity1.setAddress(userContactEntity.getAddress());
        userContactEntity1.setAddresslinetwo(userContactEntity.getAddresslinetwo());
        userContactEntity1.setEmail(userContactEntity.getEmail());
        userContactEntity1.setBlood_group(userContactEntity.getBlood_group());
        userContactEntity1.setCity(userContactEntity.getCity());
        userContactEntity1.setCitypincode(userContactEntity.getCitypincode());
        userContactEntity1.setState(userContactEntity.getState());
        userContactEntity1.setCountry(userContactEntity.getCountry());
        return userContactRepo.save(userContactEntity);
    }

    public UserContactEntity usercontactdetail(UserContactEntity userContactEntity) {
        return  userContactRepo.save(userContactEntity);
    }
    public UserContactEntity updateContactinfo1(AdminApproveContactEntity adminApproveContactEntity) {
        String email=adminApproveContactEntity.getEmail();
        UserContactEntity userContactEntity1 = userContactRepo.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        userContactEntity1.setAddress(adminApproveContactEntity.getAddress());
        userContactEntity1.setAddresslinetwo(adminApproveContactEntity.getAddress_line_two());
        userContactEntity1.setBlood_group(adminApproveContactEntity.getBlood_group());
        userContactEntity1.setEmail(adminApproveContactEntity.getEmail());
        userContactEntity1.setCity(adminApproveContactEntity.getCity());
        userContactEntity1.setCitypincode(adminApproveContactEntity.getCity_pin_code());
        userContactEntity1.setState(adminApproveContactEntity.getState());
        userContactEntity1.setCountry(adminApproveContactEntity.getCountry());
        adminContactRepo.deleteById(email);
        return userContactRepo.save(userContactEntity1);
    }
}
