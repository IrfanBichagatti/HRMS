package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.ProfessionalDetails;
import com.hrms.Hrmsbackend.models.UserEntity;
import com.hrms.Hrmsbackend.repo.ProfessionalDetailsRepository;
import com.hrms.Hrmsbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PeopleService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private ProfessionalDetailsRepository professionalDetailsRepository;

    public List<UserEntity> finduserinfo() {
        return userRepo.findAll();
    }

    public Optional<ProfessionalDetails> getProfessionalByid(String id){
        return professionalDetailsRepository.findById(id);
    }
}
