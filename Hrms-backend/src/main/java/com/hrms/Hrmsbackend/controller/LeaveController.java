package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.UserLeaveDetails;
import com.hrms.Hrmsbackend.models.UserLeaveRequestEntity;
import com.hrms.Hrmsbackend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    //post user leave request
    @PostMapping("/leaveRequest")
    public UserLeaveRequestEntity leaveRequest(@RequestBody UserLeaveRequestEntity leaveRequestEntity)
    {
        return leaveService.leaveRequest(leaveRequestEntity);
    }

    //get user leave request
    @GetMapping("/getUserLeaveRequest")
    public List<UserLeaveRequestEntity> getUserLeaveRequest(@RequestParam("email") String Username)
    {
        return leaveService.getUserLeaveRequest(Username);
    }

    //admin get all user leave request
    @GetMapping("/getAllPendingLeaveRequest")
    public List<UserLeaveRequestEntity> getAllPendingLeaveRequest()  {

        return leaveService.getAllPendingLeaveRequest();
    }

    //admin approve leave request
    @PostMapping("/approveLeaveRequest")
    public Map<String, String> approveLeaveRequest(@RequestBody Map<String, String> data) {
        int sno = Integer.parseInt(data.get("sno"));
        return leaveService.approveLeaveRequest(sno);
    }

    //get approved request
    @GetMapping("/getAllApprovedLeaveRequest")
    public List<UserLeaveRequestEntity> getAllApprovedLeaveRequest(@RequestParam String email)  {

        return leaveService.getAllApprovedLeaveRequest(email);
    }

    //admin reject leave request
    @PostMapping("/rejectLeaveRequest")
    public Map<String, String> rejectLeaveRequest(@RequestBody Map<String, String> data) {
        int sno = Integer.parseInt(data.get("sno"));
        return leaveService.rejectLeaveRequest(sno);
    }

    //add leaves for particular user
    @PostMapping("/addleaves")
    public UserLeaveDetails addleaves(@RequestBody UserLeaveDetails userLeaveDetails){
        return leaveService.addleaves(userLeaveDetails);
    }

    //get particular user's leaves information
    @GetMapping("/userLeaveDetails")
    public List<UserLeaveDetails> userLeaveDetails(@RequestParam String email) {
        return leaveService.userLeaveDetails(email);
    }

}
