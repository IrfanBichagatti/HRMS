package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.service.ManagerService;
import com.hrms.Hrmsbackend.service.OnboardingService;
import com.hrms.Hrmsbackend.service.ResignService;
import com.hrms.Hrmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;
    @Autowired
    ResignService resignService;
    @Autowired
    UserService userService;
    @Autowired
    OnboardingService onboardingService;

    /**
     * Manager Team Management
     * getting manager team people
     * @return
     */
    @GetMapping("/getManagerPeople")
    public List<AdminEntity> getManagerPeople(@RequestParam String email)
    {
        return managerService.getManagerPeople(email);
    }
    /**
     * Manager Team Management
     * getting manager team Attendance summary
     * @return
     */
    @GetMapping("/getManagerTeamAttendance")
    public  List<AvgAttendanceEntity>  getManagerTeamAttendance(@RequestParam String email)
    {
        return managerService.getManagerTeamAttendance(email);
    }
    /**
     * Manager Team Management
     * getting manager team people leave details
     * @return
     */
    @GetMapping("/getManagerTeamLeaveDetails")
    public List<UserLeaveDetails> getManagerTeamLeaveDetails(@RequestParam String email)
    {
        return managerService.getManagerTeamLeaveDetails(email);
    }


    @GetMapping("/apprroverequest/{email}")
    public OnboardingEntity changestatus(@PathVariable String email){
        return onboardingService.acceptdoc(email);
    }
    @GetMapping("/rejectrequest/{email}")
    public OnboardingEntity changedstatus(@PathVariable String email){
        return onboardingService.rejectonboard(email);
    }
    @GetMapping("/getAllManager")
    public List<AdminEntity> getAllManager(){
        return managerService.getAllManager();
    }
}
