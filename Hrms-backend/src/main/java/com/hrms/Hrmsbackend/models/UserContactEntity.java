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
@Table(name = "Usercontactinfo")
@Getter
@Setter
public class UserContactEntity {

    public UserContactEntity(){

    }
    @Id
    @Column(name = "Email")
    private String email;

    @Override
    public String toString() {
        return "UserContactEntity{" +
                "email='" + email + '\'' +
                ", blood_group='" + blood_group + '\'' +
                ", address='" + address + '\'' +
                ", addresslinetwo='" + addresslinetwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", citypincode=" + citypincode +
                '}';
    }

    public UserContactEntity(String email, String blood_group, String address, String addresslinetwo, String city, String state, String country, String citypincode) {
        this.email = email;
        this.blood_group = blood_group;
        this.address = address;
        this.addresslinetwo = addresslinetwo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.citypincode = citypincode;
    }

    @Column(name = "bloodgrp")
    private String blood_group;

    @Column(name = "Address")
    private String address;

    @Column(name = "Addresstwo")
    private String addresslinetwo;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;

    @Column(name = "pincode")
    private String citypincode;
}
