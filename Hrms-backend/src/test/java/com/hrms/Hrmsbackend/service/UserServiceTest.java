package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AdminEntity;
import com.hrms.Hrmsbackend.models.UserAttendance;
import com.hrms.Hrmsbackend.models.UserEntity;
import com.hrms.Hrmsbackend.models.UserRegularizationEntity;
import com.hrms.Hrmsbackend.repo.AdminRepo;
import com.hrms.Hrmsbackend.repo.UserRegularizationRepo;
import com.hrms.Hrmsbackend.repo.UserRepo;
import com.hrms.Hrmsbackend.repo.userAttendanceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    UserEntity user = new UserEntity("test@gmail.com", "root", "test", "unit", "12", "05-07-2021", "12-05-1999", "single", "employee", "1234567890", "female");


    AdminEntity admin = new AdminEntity("test@gmail.com", 21L, "test", "unit", "root", "root", "8668592501", "05/07/2021", "India", "External", "Employee", "Female", "29/09/1997", "Single", "test", "self", "7741958353");

    //serAttendance usera=new UserAttendance("test@gmail.com","25-02-2022","11:44","06:44","7","present");


    UserRegularizationEntity userr = new UserRegularizationEntity(12, "25-02-2022", "test@gmail.com", "on duty", "09:39", "06:30", "krishan", "forget", "pending", "");

    @MockBean
    private AttendanceService attendanceService;

    @MockBean
    UserService userService;

    @MockBean
    UserRepo userRepo;

    @MockBean
    UserRegularizationRepo userRegularizationRepo;

    @MockBean
    UserAttendance userAttendance;

    @MockBean
    userAttendanceRepo UserAttendanceRepo;



    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    AdminEntity adminEntity;

    @MockBean
    AdminRepo adminRepo;


    @Test
    void login() {
        Map<String, String> userInput = new HashMap<>();
        userInput.put("email", "test@gmail.com");
        userInput.put("password", "root");
        userInput.put("role", "employee");
        Map<String,String> res=new HashMap<>();
        when(adminRepo.findByEmail("test@gmail.com")).thenReturn(admin);
       // when((user1.getEmail().equals("test@gmail.com") && user1.getRole().equalsIgnoreCase("Employee"))).thenReturn(Boolean.TRUE);

        when(passwordEncoder.matches("root", admin.getPassword())).thenReturn(Boolean.TRUE);
        when(passwordEncoder.matches("root", admin.getConfirmpassword())).thenReturn(Boolean.TRUE);
        res.put("status", "success");
        assertEquals(res, userService.login(userInput));
    }




// get all pending regularization request


    @Test
    void getAllPendingRegulrizationRequest() {

        List<UserRegularizationEntity> user = new ArrayList<UserRegularizationEntity>();

        when(userRegularizationRepo.findAll()).thenReturn(user);

        assertEquals(user, attendanceService.getAllPendingRegulrizationRequest());
    }



}


