package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AdminEntity;
import com.hrms.Hrmsbackend.repo.AdminRepo;
import com.hrms.Hrmsbackend.service.AdminService;
import com.hrms.Hrmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @Autowired
    private AdminRepo adminrepo;
    @Autowired
    private JavaMailSender mailSender;
    int otp;

    @PostMapping("/register")
    @Transactional
    public String adminfillempDet(@RequestBody AdminEntity adminEntity) {
        adminService.admin(adminEntity);
        return "Hi " + adminEntity.getFirstname() + " your registration is successful";
    }
    @GetMapping("/get-oneuser/{id}")
    public AdminEntity getOneuser(@PathVariable("id") String id) {
        return adminService.getOne(id);
    }

    @PostMapping("/signin")
    @Transactional
    public Map<String, String> login(@RequestBody Map<String, String> userInput) {
        return userService.login(userInput);
    }

    @GetMapping("/all-users")
    public Iterable<AdminEntity> showAllUsers() {
        return adminService.showAllUsers();
    }


    @PostMapping("/sendotp")
    public String getOTP(@RequestBody Map<String, String> email) {
        SimpleMailMessage message = new SimpleMailMessage();
        Random random = new Random();
        otp = random.nextInt(10000);
        message.setFrom("shevatenamrata444@gmail.com");
        AdminEntity adminEntity = adminrepo.findByEmail(email.get("email"));
        if (adminEntity.getEmail().equals(email.get("email")))
            message.setTo(email.get("email"));

        message.setText("HRMS OTP FROM HR NAMRATA=" + "   " + otp);
        mailSender.send(message);
        return "meessage";
    }

    @PostMapping("verifyotp")
    public Map<String, String> verifyOTP(@RequestBody Map<String, Integer> otp1) {
        HashMap<String, String> map = new HashMap<>();
        try {
            if (otp1.get("otp").equals(otp)) {
                map.put("status", "success");
            } else {
                map.put("status", "failure");
            }
            return map;
        } catch (Exception e) {
            map.put("status", e.getMessage());
            return map;
        }
    }
    @PostMapping("/passwordupdate")
    public String passwordupdate(@RequestBody Map<String, String> adminEntity) {
        this.adminService.updatePass(adminEntity);
        return "Password Updated";
    }

}
