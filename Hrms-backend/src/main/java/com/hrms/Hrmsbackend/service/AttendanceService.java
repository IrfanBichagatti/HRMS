package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AlertData;
import com.hrms.Hrmsbackend.models.AvgAttendanceEntity;
import com.hrms.Hrmsbackend.models.UserAttendance;
import com.hrms.Hrmsbackend.models.UserRegularizationEntity;
import com.hrms.Hrmsbackend.repo.AlertRepo;
import com.hrms.Hrmsbackend.repo.AvgAttendanceRepo;
import com.hrms.Hrmsbackend.repo.UserRegularizationRepo;
import com.hrms.Hrmsbackend.repo.userAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private userAttendanceRepo userAttendanceRepo;
    @Autowired
    private UserRegularizationRepo userRegularizationRepo;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private AlertRepo alertRepo;
    @Autowired
    AvgAttendanceRepo avgAttendanceRepo;

    public List<UserAttendance> getUserAttendanceStatus(String Username) {
        List<UserAttendance> userAttendances = userAttendanceRepo.findAll();
        List<UserAttendance> userAttendance = new ArrayList<UserAttendance>();
        Iterator<UserAttendance> iterator = userAttendances.iterator();
        while (iterator.hasNext()){
            UserAttendance ua = (UserAttendance) iterator.next();
            if (Username.equals(ua.getUsername())) {
                System.out.print(ua);
                userAttendance.add(ua);
            }
        }
        Collections.reverse(userAttendance);
        return userAttendance;
    }

    public UserRegularizationEntity regularizeRequest(UserRegularizationEntity regularizationEntity) {
        webSocketService.sendMessage("request");
        return userRegularizationRepo.save(regularizationEntity);

    }

    public List<UserRegularizationEntity> getUserRegularizeRequest(String username) {
        List<UserRegularizationEntity> regularizationRequests = userRegularizationRepo.findAll();
        List<UserRegularizationEntity> regularizationRequest = new ArrayList<UserRegularizationEntity>();

        Iterator<UserRegularizationEntity> iterator = regularizationRequests.iterator();
        while (iterator.hasNext()){
            UserRegularizationEntity ur = (UserRegularizationEntity) iterator.next();
            if (username.equals(ur.getUsername())) {
                System.out.print(ur);
                regularizationRequest.add(ur);
            }
        }
        Collections.reverse(regularizationRequest);
        return regularizationRequest;
    }

    public Map<String,String> approveRegularizeRequest(int sno) {
        String approveRegMsg = "Dear User Your Regularization request has been approved";

        Map<String,String> map = new HashMap<>();
        String intime,outtime;
        int in,out,avg,inmin,outmin ,avgmin;
        String[] intime1;
        String[] outtime1;
        UserRegularizationEntity request = userRegularizationRepo.findById(sno).orElse(new UserRegularizationEntity());
        UserAttendance updateTime = userAttendanceRepo.findById(sno).orElse(new UserAttendance());
        intime= request.getIntime();
        outtime= request.getOuttime();
        intime1 = intime.split(":", 3);
        outtime1 = outtime.split(":", 3);
        in= Integer.parseInt(intime1[0]);
        inmin= Integer.parseInt(intime1[1]);

        out = Integer.parseInt(outtime1[0]);
        outmin = Integer.parseInt(outtime1[1]);

        avg=out-in;
        avgmin = outmin-inmin;

        updateTime.setAvgHrsOfDay(String.valueOf(avg)+":"+String.valueOf(avgmin)+":00");
        updateTime.setClockIn(String.valueOf(intime));
        updateTime.setClockOut(String.valueOf(outtime));

        userAttendanceRepo.save(updateTime);
        request.setStatus("Approved");
        userRegularizationRepo.save(request);
        map.put("status","Approved");

        AlertData alertData = new AlertData(request.getUsername(),approveRegMsg);
        alertRepo.save(alertData);

        webSocketService.sendMessage("request");

        return map;
    }

    public List<UserRegularizationEntity> getAllPendingRegulrizationRequest() {
        List<UserRegularizationEntity> allRegularizationRequests = userRegularizationRepo.findAll();
        List<UserRegularizationEntity> regularizationRequest = new ArrayList<UserRegularizationEntity>();

        Iterator<UserRegularizationEntity> iterator = allRegularizationRequests.iterator();
        while (iterator.hasNext()){
            UserRegularizationEntity ur = (UserRegularizationEntity) iterator.next();
            if ("pending".equals(ur.getStatus())) {
                System.out.print(ur);
                regularizationRequest.add(ur);
            }
        }
        Collections.reverse(regularizationRequest);
        return regularizationRequest;
    }

    public Map<String, String> rejectRegularizeRequest(int sno) {

        String rejectRegMsg = "Dear User Your Regularization request has been rejected";

        Map<String,String> map = new HashMap<>();

        UserRegularizationEntity request = userRegularizationRepo.findById(sno).orElse(new UserRegularizationEntity());
        request.setStatus("Rejected");
        userRegularizationRepo.save(request);
        map.put("status","Rejected");

        AlertData alertData = new AlertData(request.getUsername(),rejectRegMsg);
        alertRepo.save(alertData);

        webSocketService.sendMessage("request");

        return map;
    }
    public AvgAttendanceEntity getAvgAttendance(String email) throws ParseException {
        List<UserAttendance> user = userAttendanceRepo.findAll().stream().filter(data ->
                data.getUsername().equalsIgnoreCase(email)).collect(Collectors.toList());

        List<String> allInTime = new ArrayList<>();
        List<String> allOutTime = new ArrayList<>();
        List<String> dayWorkingHrs = new ArrayList<>();
        Iterator iterator = user.iterator();

        while (iterator.hasNext()){
            UserAttendance ud = (UserAttendance) iterator.next();
            allInTime.add(ud.getClockIn());
            allOutTime.add(ud.getClockOut());
            dayWorkingHrs.add(ud.getAvgHrsOfDay());
        }

        long inTimeSum = 0L,outTimeSum = 0L,wokingHrsSum = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


        for (int i = 0; i < allInTime.size(); i++){
            inTimeSum += sdf.parse(allInTime.get(i)).getTime();
            outTimeSum += sdf.parse(allOutTime.get(i)).getTime();
            wokingHrsSum += sdf.parse(dayWorkingHrs.get(i)).getTime();
        }
        String avgInTime,avgOutTime,avgWorkingHrs;
        if(allInTime.size()==0){
            avgInTime = "00:00:00";
            avgOutTime = "00:00:00";
            avgWorkingHrs ="00:00:00";
        }else {

            avgInTime = sdf.format(new Date((inTimeSum / allInTime.size())));
//        System.out.println("avg In Time is:"+sdf.format(avgInTime));

            avgOutTime = sdf.format(new Date((outTimeSum / allOutTime.size())));
//        System.out.println("avg Out Time is:"+sdf.format(avgOutTime));

            avgWorkingHrs = sdf.format(new Date((wokingHrsSum / dayWorkingHrs.size())));
//        System.out.println("avg working Time is:"+sdf.format(avgWorkingHrs));
        }
        List<UserAttendance> listOfPresentDays = user.stream().filter(data ->
                data.getRemarks().equalsIgnoreCase("Present")).collect(Collectors.toList());

        String TotalWorkingDays = String.valueOf(listOfPresentDays.size());
//        System.out.println("Total Working Days:"+TotalWorkingDays);

//        System.out.println("email:"+email);

        AvgAttendanceEntity avgAttendanceEntity = new AvgAttendanceEntity(email,avgInTime,avgOutTime,avgWorkingHrs,TotalWorkingDays);
        avgAttendanceRepo.save(avgAttendanceEntity);
        return avgAttendanceEntity;
    }
}
