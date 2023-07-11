package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    AvgAttendanceRepo avgAttendanceRepo;

    @Autowired
    UserLeaveDetailsRepo leaveDetailsRepo;

    @Autowired
    UserLeaveRequestRepo leaveRequestRepo;

    public List<AdminEntity> getManagerPeople(String email) {

        List<AdminEntity> managerPeople = adminRepo.findAll().stream().filter(data->
                email.equalsIgnoreCase(data.getManageremailid())).collect(Collectors.toList());
        return managerPeople;
    }

    public  List<AvgAttendanceEntity> getManagerTeamAttendance(String email) {
        List<AdminEntity> managerPeople = getManagerPeople(email);

        List<AvgAttendanceEntity> managerTeamAttendance = new ArrayList<>();
        String teamMemaberEmail;
        ArrayList<String> teamEmail = new ArrayList<>();
        Iterator iterator = managerPeople.iterator();

        while (iterator.hasNext()){
           AdminEntity ad = (AdminEntity) iterator.next();
           teamEmail.add(ad.getEmail());
    }

        System.out.println("Team Email:"+teamEmail);
        ArrayList<AvgAttendanceEntity> attendance = (ArrayList<AvgAttendanceEntity>) avgAttendanceRepo.findAll();
        System.out.println("All attendance:"+attendance);

        ListIterator teamEmailiterator = teamEmail.listIterator();
        ListIterator attendanceIterator = attendance.listIterator();

        while (teamEmailiterator.hasNext()){
            teamMemaberEmail= String.valueOf(teamEmailiterator.next());

            while (attendanceIterator.hasNext()){
                AvgAttendanceEntity user = (AvgAttendanceEntity) attendanceIterator.next();
                System.out.println(teamMemaberEmail);
               // System.out.println(teamMemaberEmail+"="+user.getEmail());
                    if (teamMemaberEmail.equalsIgnoreCase(user.getEmail())){
                    managerTeamAttendance.add(user);
                     }
              }
            while (attendanceIterator.hasPrevious()) {
                attendanceIterator.previous();
            }
        }

        return managerTeamAttendance;
    }

    public List<UserLeaveDetails> getManagerTeamLeaveDetails(String email) {
        List<AdminEntity> managerPeople = getManagerPeople(email);
        List<String> teamEmail = new ArrayList<>();
        Iterator iterator = managerPeople.iterator();
        List<UserLeaveDetails> ManagerUserLeaveDetails = new ArrayList<>();
        List<UserLeaveRequestEntity> ManagerUserLeaveRequestEntity = new ArrayList<>();
        while (iterator.hasNext()){
            AdminEntity ad = (AdminEntity) iterator.next();
            teamEmail.add(ad.getEmail());
        }
/**
 * to get each employee leave details from USER_LEAVE_DETAILS
 */
        List<UserLeaveDetails> userLeaveDetails = leaveDetailsRepo.findAll();
        List<UserLeaveRequestEntity> userLeaveRequestDetails = leaveRequestRepo.findAll();

        ListIterator teamEmailIterator = teamEmail.listIterator();
        ListIterator userLeaveDetailsIterator = userLeaveDetails.listIterator();
        ListIterator userLeaveRequestDetailsIterator = userLeaveRequestDetails.listIterator();

        while (teamEmailIterator.hasNext()){
            String ManagerUserEmail = (String) teamEmailIterator.next();
            while (userLeaveDetailsIterator.hasNext()) {
                UserLeaveDetails user = (UserLeaveDetails) userLeaveDetailsIterator.next();
                if(user.getUsername().equalsIgnoreCase(ManagerUserEmail)){
                    ManagerUserLeaveDetails.add(user);
                }
            }
            while (userLeaveDetailsIterator.hasPrevious()) {
                userLeaveDetailsIterator.previous();
            }

        }
        System.out.println(ManagerUserLeaveDetails);
        System.out.println(ManagerUserLeaveRequestEntity);


        System.out.println(teamEmail);


        return ManagerUserLeaveDetails;
    }

    public List<AdminEntity> getAllManager() {
        List<AdminEntity> managerPeople= adminRepo.findAll().stream().
                filter(data->"Manager".equals(data.getRole())).collect(Collectors.toList());
        return  managerPeople;
    }
}
