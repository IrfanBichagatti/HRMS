package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "adminapproval")
@Getter
@Setter
public class AdminApprovalEntity {
    public  AdminApprovalEntity(){

    }
    @Id
    @Column(name="email")
    private String email;

    @Column(name="dob")
    private  String dob;

    @Column(name="role")
    private  String role;

    @Column(name="depart")
    private  String dpartment;

    @Column(name="relation")
    private String relation;

    @Column(name="status")
    private  String status;

    @Column(name="phoneno")
    private  String phoneno;

    @Column(name="conatctno")
    private  String contactno;

    @Column(name="contactname")
    private  String contactname;

    public AdminApprovalEntity(String email, String dob, String role, String dpartment, String relation, String status, String phoneno, String contactno, String contactname, String status1) {
        this.email = email;
        this.dob = dob;
        this.role = role;
        this.dpartment = dpartment;
        this.relation = relation;
        this.status = status;
        this.phoneno = phoneno;
        this.contactno = contactno;
        this.contactname = contactname;
        this.status1 = status1;
    }

    @Column(name="status1")
    private  String status1;

    @Override
    public String toString() {
        return "AdminApprovalEntity{" +
                "email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", role='" + role + '\'' +
                ", dpartment='" + dpartment + '\'' +
                ", relation='" + relation + '\'' +
                ", status='" + status + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", contactno='" + contactno + '\'' +
                ", contactname='" + contactname + '\'' +
                ", status1='" + status1 + '\'' +
                '}';
    }
}
