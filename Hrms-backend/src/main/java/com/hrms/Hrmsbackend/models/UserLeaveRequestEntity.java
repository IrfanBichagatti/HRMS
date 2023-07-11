package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "UserLeaveRequestEntity")
public class UserLeaveRequestEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sno")
    private int sno;

    @Column(name = "applyDate")
    private String applyDate;

    @Column(name = "username")
    private String username;

    @Column(name = "leaveType")
    private String leaveType;

    @Column(name = "fromDate")
    private String fromDate;

    @Column(name = "status")
    private String status;

    @Column(name = "toDate")
    private String toDate;

    @Column(name = "manager")
    private String manager;

    @Column(name = "userComment")
    private String userComment;

    @Column(name = "toLeaveShift")
    private String toLeaveShift;

    @Column(name = "fromLeaveShift")
    private String fromLeaveShift;

    @Column(name="leavePeriod")
    private String leavePeriod;

    public UserLeaveRequestEntity(int sno, String applyDate, String username, String status,String leaveType, String fromDate, String toDate, String manager, String userComment, String fromLeaveShift,String toLeaveShift,String leavePeriod) {
        this.sno = sno;
        this.applyDate = applyDate;
        this.username = username;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.manager = manager;
        this.status = status;
        this.userComment = userComment;
        this.fromLeaveShift = fromLeaveShift;
        this.toLeaveShift = toLeaveShift;
        this.leavePeriod=leavePeriod;
    }

    public UserLeaveRequestEntity() {
    }
}

