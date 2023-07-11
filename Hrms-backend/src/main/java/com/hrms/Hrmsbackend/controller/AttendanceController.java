package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AvgAttendanceEntity;
import com.hrms.Hrmsbackend.models.UserAttendance;
import com.hrms.Hrmsbackend.models.UserRegularizationEntity;
import com.hrms.Hrmsbackend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/getUserAttendanceStatus")
    public List<UserAttendance> getUserAttendanceStatus(@RequestParam("email") String Username){

        return attendanceService.getUserAttendanceStatus(Username);
    }

    @GetMapping("/getUserRegularizeRequest")
    public List<UserRegularizationEntity> getUserRegularizeRequest(@RequestParam("email") String Username) {

        return attendanceService.getUserRegularizeRequest(Username);
    }

    @PostMapping("/regularizeRequest")
    public UserRegularizationEntity regularizeRequest(@RequestBody UserRegularizationEntity regularizationEntity) {

        return attendanceService.regularizeRequest(regularizationEntity);
    }

    /**
     * get users Avg clock in ,clock out and avg working hrs
     * @param email
     * @return
     */

    @GetMapping("/getAvgAttendance")
    public AvgAttendanceEntity getAvgAttendance(@RequestParam String email) throws ParseException {

        return attendanceService.getAvgAttendance(email);
    }


    //admin approve Regularization request
    @PostMapping("/approveRegularizeRequest")
    public Map<String, String> approveRegularizeRequest(@RequestBody Map<String, String> data) throws ParseException {
        int sno = Integer.parseInt(data.get("sno"));
        return attendanceService.approveRegularizeRequest(sno);
    }

    //admin reject Regularization request
    @PostMapping("/rejectRegularizeRequest")
    public Map<String, String> rejectRegularizeRequest(@RequestBody Map<String, String> data) {
        int sno = Integer.parseInt(data.get("sno"));
        return attendanceService.rejectRegularizeRequest(sno);
    }

    //admin
    @GetMapping("/getAllPendingRegulrizationRequest")
    public List<UserRegularizationEntity> getAllPendingRegulrizationRequest() {

        return attendanceService.getAllPendingRegulrizationRequest();
    }

}
