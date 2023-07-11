package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name ="UserLeaveDetails")
public class UserLeaveDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sno;

    @Column
    private String leaveType;

    @Column
    private String username;

    @Column
    private String accuredLeave;

    @Column
    private String usedLeave;

    @Column
    private String Balance;

    public UserLeaveDetails(int sno,String leaveType, String accuredLeave, String usedLeave, String Balance,String username) {
        this.sno=sno;
        this.leaveType = leaveType;
        this.accuredLeave = accuredLeave;
        this.usedLeave = usedLeave;
        this.Balance = Balance;
        this.username=username;
    }

    public UserLeaveDetails() {
    }
}
