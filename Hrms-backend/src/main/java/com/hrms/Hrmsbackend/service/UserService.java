package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.*;
import com.hrms.Hrmsbackend.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {
    int sec=00,min=00,hr=00;
    String hrs = "00",mnts="00",secs="00";
    boolean start=true;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private Applyforjob applyforjob;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private userAttendanceRepo userAttendanceRepo;


    @Autowired
    private FinancialDetailsRepository financialDetailsRepository;
    @Autowired
    private AdminFinanceRepo adminFinanceRepo;
    @Autowired
    private  AdminUpdateRepo adminUpdateRepo;
    @Autowired
    private AlertRepo alertRepo;
    @Autowired
    JobRefrelRepo jobRefrelRepo;
    @Autowired
    private JavaMailSender replymailSender;
    @Autowired
    private AdminCreateJobRepo adminCreateJobRepo;

     PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(){}

    public UserService(UserRepo userRepo){this.adminRepo=adminRepo;}


    public Map<String, String> login(Map<String, String> userInput) {
        String email = (String) userInput.get("email");
        String password = userInput.get("password");
        String role=userInput.get("role");
        Map<String, String> response = new HashMap<String, String>();
        try {
            AdminEntity adminEntity = adminRepo.findByEmail(email);
            if (adminEntity.getEmail().equals(email) && adminEntity.getRole().equalsIgnoreCase(role)) {
                if (passwordEncoder.matches(password,adminEntity.getPassword())) {
                    if (passwordEncoder.matches(password,adminEntity.getConfirmpassword())){
                        response.put("status", "valid-user");
                        return response;
                    }
                }
                }else {
                    response.put("status", "Incorrect credentials");

            }
        } catch (Exception e) {
            response.put("status", "User doesn't exist");
            return response;
        }
        return response;
    }

    public List<UserAttendance> getUserAttendance(String Username,String dat) {
        List<UserAttendance> userAttendances = userAttendanceRepo.findAll();
        List<UserAttendance> userAttendance = new ArrayList<UserAttendance>();

        Iterator<UserAttendance> iterator = userAttendances.iterator();
        while (iterator.hasNext()){
            UserAttendance ua = (UserAttendance) iterator.next();
            System.out.println(dat == ua.getClockIndate());
            if (Username.equals(ua.getUsername()) && dat.equals(ua.getClockIndate())) {
                System.out.print(ua);
                userAttendance.add(ua);
            }
        }
        Collections.reverse(userAttendance);
        return userAttendance;
    }
    public List<UserAttendance> getEmployeAttendanceTracker(String firstname) {
        List<UserAttendance> userAttendances = userAttendanceRepo.findAll();
        List<UserAttendance> userAttendance = new ArrayList<UserAttendance>();

        Iterator<UserAttendance> iterator = userAttendances.iterator();
        while (iterator.hasNext()){
            UserAttendance ua = (UserAttendance) iterator.next();

            if (firstname.equalsIgnoreCase(ua.getFirstname()) ) {
                System.out.print(ua);
                userAttendance.add(ua);
            }
        }
        Collections.reverse(userAttendance);
        return userAttendance;
    }


    public UserEntity addEmployee(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    public Optional<UserEntity> getOne(String id) {
        return userRepo.findById(id);
    }

    public UserEntity updateuserinfo(String id, UserEntity userEntity) {
        UserEntity userEntityy = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        userEntityy.setContactnumber(userEntity.getContactnumber());
        userEntityy.setDateofBirth(userEntity.getDateofBirth());
        userEntityy.setEmail(userEntity.getEmail());
        userEntityy.setDepartment(userEntity.getDepartment());
        userEntityy.setEmpno(userEntity.getEmpno());
        userEntityy.setFirstname(userEntity.getFirstname());
        userEntityy.setRole(userEntity.getRole());
        userEntityy.setGender(userEntity.getGender());
        userEntityy.setLastname(userEntity.getLastname());
        userEntityy.setJoiningDate(userEntity.getJoiningDate());
        userEntityy.setStatus(userEntity.getStatus());

        return userRepo.save(userEntity);
    }






    public UserAttendance ClockIn(Map<String, String> userInput) {
        Date date = new Date();
        String time = String.valueOf(date.getTime());
        System.out.println("Time:"+time);
        String userDate = userInput.get("ClockIndate");
        String ClockIn = userInput.get("ClockIn");
        String ClockOut = userInput.get("ClockOut");
        String AvgHrsOfDay = userInput.get("AvgHrsOfDay");
        String Remarks = userInput.get("Remarks");
        String Username = userInput.get("Username");
        String Firstname = userInput.get("Firstname");
        String Lastname = userInput.get("Lastname");
        UserAttendance userAttendance = new UserAttendance(Username,Firstname,Lastname,userDate,ClockIn,ClockOut,AvgHrsOfDay,Remarks);
        userAttendanceRepo.save(userAttendance);
        return userAttendance;
    }


    /**
     *
     * @return
     */
    public List<UserEntity> findBirthinfo() {

        List<UserEntity> user1 =null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        String str1=str.substring(0,5);

        try {
            user1 =  userRepo.findAll().stream().filter(data -> data.getDateofBirth().substring(0,5).equals(str1)).collect(Collectors.toList());
            System.out.println(user1);
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(str1);
        return user1;
    }


    public FinancialDetails updateuserfinainfo(AdminApprovalEntitytwo adminApprovalEntitytwo) {
        String email = adminApprovalEntitytwo.getEmail();
        FinancialDetails financialDetails = financialDetailsRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        financialDetails.setname_as_pan(adminApprovalEntitytwo.getName_as_per_name());
        financialDetails.setpan_number(adminApprovalEntitytwo.getPannumber());
        financialDetails.setname_as_peraadhar(adminApprovalEntitytwo.getAdharname());
        financialDetails.setaadhar_card_number(adminApprovalEntitytwo.getAdharno());
        financialDetails.setbank_name(adminApprovalEntitytwo.getBankname());
        financialDetails.setname_as_per_bank_records(adminApprovalEntitytwo.getNameasperbank());
        financialDetails.setifsc_code(adminApprovalEntitytwo.getIfsccode());
        financialDetails.setbank_account_number(adminApprovalEntitytwo.getAccountno());
        adminFinanceRepo.deleteById(email);
        return financialDetailsRepository.save(financialDetails);
    }





    public String startClock() throws InterruptedException {


        int SECOND = 1000;
        int MINUTE = 1000 * 60;
        int HOUR = 1000 * 60 * 60;


        long startTime = System.currentTimeMillis();
        long endTime;
        long timeElapsed;
        TimeUnit.SECONDS.sleep(1);
        endTime = System.currentTimeMillis();

        timeElapsed = endTime - startTime;
        sec = Integer.parseInt(String.valueOf((timeElapsed/SECOND)));
        start=true;
        while (start){

            TimeUnit.SECONDS.sleep(1);
            sec++;
            if(sec==60) {
                sec = 00;
                min++;
                String.valueOf(timeElapsed / MINUTE);
            }
            if(min==60) {
                min = 00;
                hr++;
            }
            if(hr==12) {

                start = false;

            }

        }

        String time =hr+":"+ min+":"+sec;

        return time;
    }

    public Map<String,String>  getTime(){
        Map<String,String> map = new HashMap<>();

       String time = hr+":"+ min+":"+sec;

        if(time.equals("0:0:0")){
            map.put("time",hrs+":"+ mnts+":"+secs);
            map.put("status","stop");
        }else {
            if(hr<10)
            hrs="0"+hr;
            else
                hrs= String.valueOf(hr);
            if(min<10)
                mnts="0"+min;
            else
                mnts= String.valueOf(min);
            if(sec<10)
                secs="0"+sec;
            else
                secs= String.valueOf(sec);


            map.put("time",hrs+":"+ mnts+":"+secs);
            map.put("status","running");
        }

        return map;
    }

    public String stopClock() throws InterruptedException {
        start=false;
        TimeUnit.SECONDS.sleep(1);
        hrs = "00";mnts="00";secs="00";
        sec=00;min=00;hr=00;
        return "stop";
    }


    public  Map<String,String> getUserCount() {
        List<UserAttendance> People = userAttendanceRepo.findAll();
        List<UserAttendance> userAttendance = new ArrayList<UserAttendance>();
//        List<AdminEntity> People = adminRepo.findAll();
        ArrayList<String> teamEmail = new ArrayList<>();
        Iterator iterator = People.iterator();
        String teamMemaberEmail;
        while (iterator.hasNext()){
           UserAttendance ad = (UserAttendance) iterator.next();
            teamEmail.add(ad.getClockIndate());
        }
        ArrayList<UserAttendance> attendance = (ArrayList<UserAttendance>) userAttendanceRepo.findAll();
        ListIterator teamEmailiterator = teamEmail.listIterator();
        ListIterator attendanceIterator = attendance.listIterator();
        int count;
        Map<String,String> map=new HashMap<>();
        while (teamEmailiterator.hasNext()){
            count=0;
            teamMemaberEmail= String.valueOf(teamEmailiterator.next());

            while (attendanceIterator.hasNext()){
                UserAttendance user = (UserAttendance) attendanceIterator.next();
//                System.out.println(teamMemaberEmail);
                // System.out.println(teamMemaberEmail+"="+user.getEmail());
                if (teamMemaberEmail.equalsIgnoreCase(user.getClockIndate())){
                    if (user.getRemarks().equalsIgnoreCase("Present")){
                     count++;
                    }
                    map.put(teamMemaberEmail, String.valueOf(count));
                }
            }
            while (attendanceIterator.hasPrevious()) {
                attendanceIterator.previous();
            }
        }
//        System.out.println(map);
        Collections.reverse(userAttendance);
        return map;
    }

    public List<UserAttendance> getUserAttendanceStatus1() {
        return userAttendanceRepo.findAll();
    }

    public Map<String, String> getUserCountabsent() {
        List<UserAttendance> People = userAttendanceRepo.findAll();
        List<UserAttendance> userAttendance = new ArrayList<UserAttendance>();
//        List<AdminEntity> People = adminRepo.findAll();
        ArrayList<String> teamEmail = new ArrayList<>();
        Iterator iterator = People.iterator();
        String teamMemaberEmail;
        while (iterator.hasNext()){
            UserAttendance ad = (UserAttendance) iterator.next();
            teamEmail.add(ad.getClockIndate());
        }
        ArrayList<UserAttendance> attendance = (ArrayList<UserAttendance>) userAttendanceRepo.findAll();
        ListIterator teamEmailiterator = teamEmail.listIterator();
        ListIterator attendanceIterator = attendance.listIterator();
        int count;
        Map<String,String> map=new HashMap<>();
        while (teamEmailiterator.hasNext()){
            count=0;
            teamMemaberEmail= String.valueOf(teamEmailiterator.next());

            while (attendanceIterator.hasNext()){
                UserAttendance user = (UserAttendance) attendanceIterator.next();
//                System.out.println(teamMemaberEmail);
                // System.out.println(teamMemaberEmail+"="+user.getEmail());
                if (teamMemaberEmail.equalsIgnoreCase(user.getClockIndate())){
                    if (user.getRemarks().equalsIgnoreCase("Absent")){
                        count++;
                    }
                    map.put(teamMemaberEmail, String.valueOf(count));
                }
            }
            while (attendanceIterator.hasPrevious()) {
                attendanceIterator.previous();
            }
        }
//        System.out.println(map);
        Collections.reverse(userAttendance);
        return map;
    }

    public List<AlertData> Alert(String email){
        List<AlertData> alertData=alertRepo.findAll().stream().filter(data->data.getEmail().equals(email)).collect(Collectors.toList());
        return alertData;
    }

    public UserApplyJob applyforjob(UserApplyJob userApplyJob) {
        return applyforjob.save(userApplyJob);
    }

    public List<UserApplyJob> getjobrequestdetail() {
        return applyforjob.findAll();
    }


    public List<JobReferalEntity> getreferaldetail() { return jobRefrelRepo.findAll(); }



    public String getUserWorkingHr() {

        List<UserAttendance> totalworkinghr = userAttendanceRepo.findAll();
        List<String> workinghrslist = new ArrayList<>();
        Iterator<UserAttendance> iterator = totalworkinghr.iterator();
        while(iterator.hasNext()){
            /* System.out.println(iterator.next().getAvgHrsOfDay());*/
            workinghrslist.add(iterator.next().getAvgHrsOfDay());

        }
      /*  Iterator<String> iterator1 =workinghrslist.iterator();
        while(iterator.hasNext()){*/
        int sum = 0;
        for( String hhmm : workinghrslist ){
            String[] split = hhmm.split( ":", 3 );
            int hr = Integer.valueOf(split[ 1 ]) * 60*60 +Integer.valueOf(split[ 1 ]) * 60 + Integer.valueOf( split[ 2 ] );
            sum += hr;
        }

        String formattedWorkingTime = (int)Math.floor(sum/3600) + ":" +(int)Math.floor(sum/60) + ":" + ( sum % 60 );

        return formattedWorkingTime;
    };

    public String getUserdailyavgclockin(String date1) throws ParseException {

        List<UserAttendance> user = userAttendanceRepo.findAll().stream().filter(data ->
                date1.equals(data.getClockIndate())).collect(Collectors.toList());
//        System.out.println(user);
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

        long wokingHrsSum = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


        for (int i = 0; i < allInTime.size(); i++){
            wokingHrsSum += sdf.parse(dayWorkingHrs.get(i)).getTime();
        }
        String avgInTime,avgOutTime,avgWorkingHrs;
        if(allInTime.size()==0){
//            avgInTime = "00:00:00";
//            avgOutTime = "00:00:00";
            avgWorkingHrs ="00:00:00";
        }else {

//            avgInTime = sdf.format(new Date((inTimeSum / allInTime.size())));
//        System.out.println("avg In Time is:"+sdf.format(avgInTime));

//            avgOutTime = sdf.format(new Date((outTimeSum / allOutTime.size())));
//        System.out.println("avg Out Time is:"+sdf.format(avgOutTime));

            avgWorkingHrs = sdf.format(new Date((wokingHrsSum / dayWorkingHrs.size())));
//        System.out.println("avg working Time is:"+sdf.format(avgWorkingHrs));
        }
//        List<UserAttendance> listOfPresentDays = user.stream().filter(data ->
//                data.getRemarks().equalsIgnoreCase("Present")).collect(Collectors.toList());
//
//        String TotalWorkingDays = String.valueOf(listOfPresentDays.size());
//        System.out.println("Total Working Days:"+TotalWorkingDays);

//        System.out.println("email:"+email);

//        AvgAttendanceEntity avgAttendanceEntity = new AvgAttendanceEntity(email,avgInTime,avgOutTime,avgWorkingHrs,TotalWorkingDays);
//        avgAttendanceRepo.save(avgAttendanceEntity);
//        return avgAttendanceEntity;
        return avgWorkingHrs;
    }


}




