package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name="AdminApproveContact")
public class AdminApproveContactEntity {

    @Id
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="address_line_two")
    private String address_line_two;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
    @Column(name="city_pin_code")
    private String city_pin_code;
    @Column(name="status")
    private String status;
    @Column(name="blood_group")
    private String blood_group;

    public AdminApproveContactEntity(String blood_group,String email, String address, String address_line_two, String city, String state, String country, String city_pin_code, String status) {
        this.email = email;
        this.address = address;
        this.address_line_two = address_line_two;
        this.city = city;
        this.state = state;
        this.country = country;
        this.city_pin_code = city_pin_code;
        this.status = status;
        this.blood_group=blood_group;
    }

    public AdminApproveContactEntity() {
    }

    public String getBlood_group(){
        return blood_group;
    }
    public void setBlood_group(String blood_group){
        this.blood_group=blood_group;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_line_two() {
        return address_line_two;
    }

    public void setAddress_line_two(String address_line_two) {
        this.address_line_two = address_line_two;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity_pin_code() {
        return city_pin_code;
    }

    public void setCity_pin_code(String city_pin_code) {
        this.city_pin_code = city_pin_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
