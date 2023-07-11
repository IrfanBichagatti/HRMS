package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.ProfessionalDetails;
import com.hrms.Hrmsbackend.models.UserEntity;
import com.hrms.Hrmsbackend.repo.UserRepo;
import com.hrms.Hrmsbackend.service.PeopleService;
import com.hrms.Hrmsbackend.service.ProfessionalDetailsService;
import com.hrms.Hrmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class PeopleController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    ProfessionalDetailsService professionalDetailsService;

    @GetMapping("/getuserinfo1")
    public List<UserEntity> getuserinfo()
    {
        return peopleService.finduserinfo();
    }

    @GetMapping("/getprofessionalDetails/{id}")
    public Optional<ProfessionalDetails> getProfessionalDetails(@PathVariable String id) {
        return peopleService.getProfessionalByid(id);
    }

}
