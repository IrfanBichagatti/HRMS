package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AdminEntity;
import com.hrms.Hrmsbackend.models.OnboardingEntity;
import com.hrms.Hrmsbackend.models.ResignationEntity;
import com.hrms.Hrmsbackend.repo.AdminRepo;
import com.hrms.Hrmsbackend.repo.OnboardingRepo;
import com.hrms.Hrmsbackend.repo.ResignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ResignService {
    @Autowired
    private ResignRepo resignRepo;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    private OnboardingRepo onboardingRepo;

    public List<ResignationEntity> getallresin() {

//        LocalDateTime today =  LocalDateTime.now();
//        LocalDateTime sameDayNextMonth = today.plusMonths(2);
//        LocalDateTime tomorrow = today.plusDays(60);
//        System.out.println(today);
//        System.out.println(tomorrow);
//        System.out.println(sameDayNextMonth);
//        return resignRepo.findAll();

        List<ResignationEntity> allresignrequest = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();

        Iterator<ResignationEntity> iterator = allresignrequest.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if ("pending".equals(ul.getStatus())) {
                System.out.print(ul);
                resignrequest.add(ul);
            }
        }

        Collections.reverse(resignrequest);
        return resignrequest;
    }

    public ResignationEntity applyforresign(ResignationEntity resignationEntity) {
        Date date = new Date();
        LocalDateTime today =  LocalDateTime.now();
        resignationEntity.setApplydate(date);
        LocalDateTime tomorrow = today.plusDays(60);
        System.out.println(tomorrow);
        Instant instant = tomorrow.atZone(ZoneId.systemDefault()).toInstant();
        Date date1 = Date.from(instant);
        resignationEntity.setLastday(date1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date d1 = sdf.parse(date);
//        Date d2 = sdf.parse(date1);
        long diffInMillis = date.getTime() - date1.getTime();
        long dateDiffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        resignationEntity.setNoticeperiod( Math.abs((int) dateDiffInDays));
        System.out.println(dateDiffInDays);
        resignationEntity.setStatus("pending");
      //  resignationEntity.setStatus1("pending");

        return resignRepo.save(resignationEntity);
    }

    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(fixedRate = 1000)
    public Map<String,String> updatenoticeperiod() {
        List<ResignationEntity> resignationEntity1 = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        Iterator<ResignationEntity> iterator = resignationEntity1.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if (ul.getStatus().equalsIgnoreCase("reject"))
            {
                ul.setNoticeperiod(60);
            }
        else {
            Date date = new Date();
            Date date1 = ul.getLastday();
            long diffInMillis = date.getTime() - date1.getTime();
            long dateDiffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            if (dateDiffInDays >= -1)
            {
                ul.setNoticeperiod(0);
            }
            else {
                ul.setNoticeperiod((int) Math.abs(dateDiffInDays));
            }


            resignRepo.save(ul);
            System.out.println(dateDiffInDays);
        }

        }
        map.put("status","success");
        return map;

    }

//    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask() {
//        System.out.println(
//                "Fixed delay task - " + System.currentTimeMillis() / 1000);
//    }

    public Map<String,String>  apprroverequest( String email) {
        List<ResignationEntity> resignationEntity1 = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        Iterator<ResignationEntity> iterator = resignationEntity1.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if ("pending".equals(ul.getStatus())) {
               ul.setStatus("Approved");
                resignrequest.add(ul);
                resignRepo.save(ul);
            }
        }
        map.put("status","approve");
        return map;
    }

    public Map<String,String>  rejectresign( String email) {
        Map<String,String> map = new HashMap<>();
        List<ResignationEntity> resignationEntity1 = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();

        Iterator<ResignationEntity> iterator = resignationEntity1.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if (ul.getStatus().equalsIgnoreCase("pending")) {
                ul.setStatus("reject");
                resignRepo.save(ul);
            }
        }
        map.put("status","reject");
        return  map;
    }

    public List<ResignationEntity> getresignrequest(String email) {
        List<ResignationEntity> allresignrequest = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();

        Iterator<ResignationEntity> iterator = allresignrequest.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if (email.equals(ul.getEmail())) {
                System.out.print(ul);
                resignrequest.add(ul);
            }
        }

//        Collections.reverse(resignrequest);
        return resignrequest;
    }

    public List<ResignationEntity> getallresignedrequest(){
        List<ResignationEntity> allresignrequest = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();
        Date date = new Date();
        Iterator<ResignationEntity> iterator = allresignrequest.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if (ul.getNoticeperiod()==0) {
                ul.setStatus1("resigned");
                resignrequest.add(ul);
                resignRepo.save(ul);
            }
        }
        return  resignrequest;
    }

    public List<ResignationEntity> getallnoticeperiodserviving() {
        List<ResignationEntity> allresignrequest = resignRepo.findAll();
        List<ResignationEntity> resignrequest = new ArrayList<>();
        Date date = new Date();
        Iterator<ResignationEntity> iterator = allresignrequest.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if (ul.getStatus().equalsIgnoreCase("Approved")) {
                resignrequest.add(ul);
            }
        }
        return  resignrequest;
    }



    public List<Integer> getallusercount() {
        int count=0;
        int count1=0;
        int count2=0;
        int count3=0;
        List<ResignationEntity> allresignrequest = resignRepo.findAll();
        List<AdminEntity> adminEntities = adminRepo.findAll();
        List<OnboardingEntity> onboardingEntities = onboardingRepo.findAll();
        List<Integer> resignrequest = new ArrayList<>();
        Iterator<ResignationEntity> iterator = allresignrequest.iterator();
        while (iterator.hasNext()){
            ResignationEntity ul = (ResignationEntity) iterator.next();
            if (ul.getStatus().equalsIgnoreCase("resigned")) {
                count++;
            }
            else if (ul.getStatus().equalsIgnoreCase("Approved")){
                count1++;
            }
        }

        Iterator<AdminEntity> iterator1 = adminEntities.iterator();
        while (iterator1.hasNext()) {
            AdminEntity admin = (AdminEntity) iterator1.next();
            count2++;

        }


        Iterator<OnboardingEntity> iterator2 = onboardingEntities.iterator();
        while (iterator2.hasNext()){
            OnboardingEntity onboardingEntity = (OnboardingEntity) iterator2.next();
            count3++;

        }
        resignrequest.add(count);
        resignrequest.add(count1);
        resignrequest.add(count2);
        resignrequest.add(count3);
        return resignrequest;
    }
}
