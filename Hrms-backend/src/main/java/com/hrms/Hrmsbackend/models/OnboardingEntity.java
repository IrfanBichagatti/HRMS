package com.hrms.Hrmsbackend.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "Onboarding")
public class OnboardingEntity {
    public OnboardingEntity() {
    }
    @Id
    @Column(name = "email")
    private String email;

    @Column(name="FirstName")
    private String firstname;
    @Column(name="MiddleName")
    private String middlename;
    @Column(name="LastName")
    private String lastname;
    @Column(name="preferedName")
    private String preferedname;
    @Column(name ="dateofBirth")
    private  String dateofBirth;
    @Column(name ="title")
    private  String Title;
    @Column(name ="department")
    private  String Department;
    @Column(name ="manager")
    private  String Manager;
    @Column(name ="homeaddress")
    private  String homeaddress;
    @Column(name ="status")
    private  String status;

}
