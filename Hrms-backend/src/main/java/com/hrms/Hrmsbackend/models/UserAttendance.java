package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "UserAttendance")
public class UserAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sno")
    private int sno;


    @Column(name = "Username")
    private String Username;

    @Column(name = "Firstname")
    private String Firstname;

    @Column(name = "Lastname")
    private String Lastname;

    @Column(name = "ClockIndate")
    private String ClockIndate;

    @Column(name = "ClockIn")
    private String ClockIn;

    @Column(name = "ClockOut")
    private String ClockOut;

    @Column(name = "AvgHrsOfDay")
    private String AvgHrsOfDay;

    @Column(name = "Remarks")
    private String Remarks;

    public UserAttendance(String username,String firstname,String lastname, String clockIndate, String clockIn, String clockOut, String avgHrsOfDay, String remarks) {
        Username = username;
        ClockIndate = clockIndate;
        ClockIn = clockIn;
        ClockOut = clockOut;
        AvgHrsOfDay = avgHrsOfDay;
        Remarks = remarks;
        Firstname = firstname;
        Lastname = lastname;
    }

    public UserAttendance(int sno, String username, String clockIndate, String clockIn, String clockOut, String avgHrsOfDay, String remarks) {
        this.sno = sno;
        Username = username;
        ClockIndate = clockIndate;
        ClockIn = clockIn;
        ClockOut = clockOut;
        AvgHrsOfDay = avgHrsOfDay;
        Remarks = remarks;
    }

    public UserAttendance() {
    }


}
