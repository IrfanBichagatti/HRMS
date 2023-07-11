package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AlertData;
import com.hrms.Hrmsbackend.models.UserLeaveDetails;
import com.hrms.Hrmsbackend.models.UserLeaveRequestEntity;
import com.hrms.Hrmsbackend.repo.AlertRepo;
import com.hrms.Hrmsbackend.repo.UserLeaveDetailsRepo;
import com.hrms.Hrmsbackend.repo.UserLeaveRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    @Autowired
    private AlertRepo alertRepo;
    @Autowired
    private UserLeaveRequestRepo userLeaveRequestRepo;
    @Autowired
    private UserLeaveDetailsRepo userLeaveDetailsRepo;
    @Autowired
    private JavaMailSender replymailSender;

    //leave

    public float totalLeave(String fromDate,String toDate,String fromLeaveShift,String toLeaveShift) {

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
        String inputString1 =fromDate ;
        String inputString2 = toDate;

        Date date1 = null;
        Date date2=null;
        try {
            date1 = myFormat.parse(inputString1);
            date2 = myFormat.parse(inputString2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        int diff = (int) (date1.getTime() - date2.getTime());
        float totalleaveperiod= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(fromLeaveShift.equalsIgnoreCase("full day") && toLeaveShift.equalsIgnoreCase("full day"))
        {

        }
        else if(fromDate.equalsIgnoreCase(toDate) && fromLeaveShift.equalsIgnoreCase("half day") && toLeaveShift.equalsIgnoreCase("half day"))
        {
            totalleaveperiod= (float) (totalleaveperiod-0.5);
        }
        else if(fromLeaveShift.equalsIgnoreCase("half day") && toLeaveShift.equalsIgnoreCase("half day"))
        {
            totalleaveperiod= (float) (totalleaveperiod-1);
        }

        else
        {
            totalleaveperiod= (float) (totalleaveperiod-0.5);
        }
        return totalleaveperiod+1;
    }

    //post mapping
    public UserLeaveRequestEntity leaveRequest(UserLeaveRequestEntity leaveRequestEntity)
    {
        String toDate=leaveRequestEntity.getFromDate();
        String fromDate=leaveRequestEntity.getToDate();
        String fromLeaveShift=leaveRequestEntity.getFromLeaveShift();
        String toLeaveShift=leaveRequestEntity.getToLeaveShift();
        String leavePeriod= String.valueOf(totalLeave(fromDate,toDate,fromLeaveShift,toLeaveShift));
        leaveRequestEntity.setLeavePeriod(leavePeriod);
        return userLeaveRequestRepo.save(leaveRequestEntity);
    }

    public List<UserLeaveRequestEntity> getUserLeaveRequest(String username) {

        List<UserLeaveRequestEntity> leaveRequests = userLeaveRequestRepo.findAll();
        List<UserLeaveRequestEntity> leaveRequest = new ArrayList<>();
        Iterator<UserLeaveRequestEntity> iterator = leaveRequests.iterator();
        while (iterator.hasNext()) {
            UserLeaveRequestEntity ul = (UserLeaveRequestEntity) iterator.next();
            if (username.equals(ul.getUsername())) {
                System.out.print(ul);
                leaveRequest.add(ul);
            }
        }
        System.out.println("array " + leaveRequest);
        Collections.reverse(leaveRequest);
        return leaveRequest;
    }

    //admin get all pending user leave request
    public List<UserLeaveRequestEntity> getAllPendingLeaveRequest(){
        List<UserLeaveRequestEntity> allLeaveRequests = userLeaveRequestRepo.findAll();
        List<UserLeaveRequestEntity> leaveRequest = new ArrayList<>();

        Iterator<UserLeaveRequestEntity> iterator = allLeaveRequests.iterator();
        while (iterator.hasNext()){
            UserLeaveRequestEntity ul = (UserLeaveRequestEntity) iterator.next();
            if ("Pending".equals(ul.getStatus())) {
                System.out.print(ul);
                leaveRequest.add(ul);
            }
        }
        Collections.reverse(leaveRequest);
        return leaveRequest;
    }

    //get all approved user leave request
    public List<UserLeaveRequestEntity> getAllApprovedLeaveRequest(String email){
        List<UserLeaveRequestEntity> allLeaveRequests = userLeaveRequestRepo.findAll();
        List<UserLeaveRequestEntity> leaveRequest = new ArrayList<>();

        Iterator<UserLeaveRequestEntity> iterator = allLeaveRequests.iterator();
        while (iterator.hasNext()){
            UserLeaveRequestEntity ul = (UserLeaveRequestEntity) iterator.next();
            if(email.equals(ul.getUsername())){
                if ("Approved".equals(ul.getStatus())) {
                    System.out.print(ul);
                    leaveRequest.add(ul);
                }
            }
        }
        Collections.reverse(leaveRequest);
        return leaveRequest;
    }


    //admin approve user leave req
    public Map<String,String> approveLeaveRequest(int sno) {
        Map<String,String> map = new HashMap<>();

        UserLeaveRequestEntity leaverequest = userLeaveRequestRepo.findById(sno).orElse(new UserLeaveRequestEntity());
        leaverequest.setStatus("Approved");
        String username=leaverequest.getUsername();
        float leavePeriod= Float.parseFloat(leaverequest.getLeavePeriod());
        System.out.println(leavePeriod);

        userLeaveRequestRepo.save(leaverequest);
        map.put("status","Approved");

        UserLeaveDetails match = new UserLeaveDetails();
        List<UserLeaveDetails> userLeaveDetails = userLeaveDetailsRepo.findAll();
        Iterator<UserLeaveDetails> iterator = userLeaveDetails.iterator();
        float BalanceLeave,UsedLeave;

        while (iterator.hasNext()){
            UserLeaveDetails ud = (UserLeaveDetails) iterator.next();
            if(ud.getLeaveType().equalsIgnoreCase(leaverequest.getLeaveType()) && ud.getUsername().equalsIgnoreCase(username))
            {
                match=ud;
                BalanceLeave= Float.parseFloat(match.getBalance());
                UsedLeave= Float.parseFloat(match.getUsedLeave());
                match.setBalance(String.valueOf(BalanceLeave-leavePeriod));
                match.setUsedLeave(String.valueOf(UsedLeave+leavePeriod));
                userLeaveDetailsRepo.save(match);
                System.out.println("match=="+match);
            }
        }
        sendReplyOnEmailLeave(sno);
        return map;
    }

    // admin reject leave req
    public Map<String,String> rejectLeaveRequest(int sno) {
        Map<String,String> map = new HashMap<>();

        UserLeaveRequestEntity leaverequest = userLeaveRequestRepo.findById(sno).orElse(new UserLeaveRequestEntity());
        leaverequest.setStatus("Rejected");
        userLeaveRequestRepo.save(leaverequest);
        map.put("status","Rejected");
        sendReplyOnEmailLeave(sno);
        return map;
    }

    //post leave details
    public UserLeaveDetails addleaves(UserLeaveDetails userLeaveDetails) {
        return userLeaveDetailsRepo.save(userLeaveDetails);
    }

    //get leave details(leave overview)
    public List<UserLeaveDetails> userLeaveDetails(String email){
        return userLeaveDetailsRepo.findAll().stream().filter(data->data.getUsername().equals(email)).collect(Collectors.toList());
    }

    //Leave Email service
    public String sendReplyOnEmailLeave( int sno) {

        UserLeaveRequestEntity leaverequest = userLeaveRequestRepo.findById(sno)
                .orElse(new UserLeaveRequestEntity());

        String approvemsg = "Dear User your leave request has been Approved "
                +"\n"+ "form  date:"+leaverequest.getFromDate()
                +"\n"+ "to  date:"+leaverequest.getToDate();

        String rejectmsg = "Dear User your leave request has been Rejected "
                +"\n"+ "form  date:"+leaverequest.getFromDate()
                +"\n"+ "to  date:"+leaverequest.getToDate();

        String status=leaverequest.getStatus();
        SimpleMailMessage emailmessage = new SimpleMailMessage();
        emailmessage.setTo(leaverequest.getUsername());
        emailmessage.setSubject("Leave Status");
        if(status.equalsIgnoreCase("Approved"))
        {
            emailmessage.setText( approvemsg );
            AlertData alertData = new AlertData(leaverequest.getUsername(),approvemsg);
            alertRepo.save(alertData);
        }
        else if(status.equalsIgnoreCase("Rejected"))
        {
            emailmessage.setText( rejectmsg );
            AlertData alertData = new AlertData(leaverequest.getUsername(),rejectmsg);
            alertRepo.save(alertData);
        }
        replymailSender.send(emailmessage);
        return "success";
    }

}
