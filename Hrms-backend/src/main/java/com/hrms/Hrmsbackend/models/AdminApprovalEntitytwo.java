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
@Table(name = "adminapprovalfinance")
@Getter
@Setter
public class AdminApprovalEntitytwo {
    public AdminApprovalEntitytwo(){

    }
    @Id
    @Column(name="email")
    private String email;

    @Column(name ="pan_name")
    private  String name_as_per_name;

    @Column(name = "pannumber")
    private  String pannumber;

    @Column(name ="aadhar_name")
    private  String adharname;

    @Column(name="adharno")
    private  String adharno;

    @Column(name = "bank_name")
    private  String bankname;

    @Column(name="nameasperbank")
    private  String nameasperbank;

    @Column(name = "ifsccode")
    private String ifsccode;

    @Column(name = "accountno")
    private String accountno;

    @Column(name = "status")
    private  String status;

    @Override
    public String toString() {
        return "AdminApprovalEntitytwo{" +
                "email='" + email + '\'' +
                ", name_as_per_name='" + name_as_per_name + '\'' +
                ", pannumber='" + pannumber + '\'' +
                ", adharname='" + adharname + '\'' +
                ", adharno='" + adharno + '\'' +
                ", bankname='" + bankname + '\'' +
                ", nameasperbank='" + nameasperbank + '\'' +
                ", ifsccode='" + ifsccode + '\'' +
                ", accountno='" + accountno + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public AdminApprovalEntitytwo(String email, String name_as_per_name, String pannumber, String adharname, String adharno, String bankname, String nameasperbank, String ifsccode, String accountno, String status) {
        this.email = email;
        this.name_as_per_name = name_as_per_name;
        this.pannumber = pannumber;
        this.adharname = adharname;
        this.adharno = adharno;
        this.bankname = bankname;
        this.nameasperbank = nameasperbank;
        this.ifsccode = ifsccode;
        this.accountno = accountno;
        this.status = status;
    }
}
