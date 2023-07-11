package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Table(name = "AvgAttendanceEntity")
public class AvgAttendanceEntity {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "avgInTime")
   private String  avgInTime;

    @Column(name = "avgOutTime")
    private String  avgOutTime;

    @Column(name = "avgWorkingHrs")
    private String avgWorkingHrs;

    @Column(name = "presentDays")
    private String  presentDays;

    public AvgAttendanceEntity(String email, String avgInTime, String avgOutTime, String avgWorkingHrs, String presentDays) {
        this.email = email;
        this.avgInTime = avgInTime;
        this.avgOutTime = avgOutTime;
        this.avgWorkingHrs = avgWorkingHrs;
        this.presentDays = presentDays;
    }

    public AvgAttendanceEntity() {

    }
}
