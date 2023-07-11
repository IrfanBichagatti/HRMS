package com.hrms.Hrmsbackend.models;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Table(name = "admin")
public class AdminEntity {
    @Id
    @Column(name = "Email")
    private String email;

    @Column(name="empid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long Empid;

    @Column(name="FirstName")
    private String firstname;

    @Column(name="LastName")
    private String lastname;

    @Column(name = "Password")
    private String password;

    @Column(name = "Confirmpassword")
    private String confirmpassword;

    @Column(name="Contactno") //personal number
    private String contactno;

    @Column(name = "joiningD")
    private String joiningDate;

    @Column(name="country")
    private String country;

    @Column(name="department")
    private String department;

    @Column(name ="Role")
    private String role;

    @Column(name = "Gender")
    private  String gender;

    @Column(name = "managername")
    private  String managername;

    @Column(name = "manageremail")
    private  String manageremailid;

    @Column(name = "BirthdayDate")
    private String date;

    @Column(name="marriagestatus")
    private String status;


    @Column(name="Contactname") //emergencycontactname
    private String contactname;

    @Column(name="Relation")
    private String relation;

    @Column(name="ContactNumber") //emergencycontactnumber
    private String contactnumber;

    private String name;

    private String type;
    @Lob
    private byte[] data;

    public AdminEntity(String managername, String manageremailid) {
        this.managername = managername;
        this.manageremailid = manageremailid;
    }

    public AdminEntity(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public AdminEntity(String email, Long empid, String firstname, String lastname, String password, String confirmpassword, String contactno, String joiningDate, String country, String department, String role, String gender, String date, String status, String contactname, String relation, String contactnumber) {
        this.email = email;
        Empid = empid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.contactno = contactno;
        this.joiningDate = joiningDate;
        this.country = country;
        this.department = department;
        this.role = role;
        this.gender = gender;
        this.date = date;
        this.status = status;
        this.contactname = contactname;
        this.relation = relation;
        this.contactnumber = contactnumber;
    }

    public AdminEntity(){

    }

}
