
package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "Userinfo")
public class UserEntity {

    public UserEntity(){

    }

    @Id
    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name="FirstName")
    private String firstname;

    @Column(name="LastName")
    private String lastname;

    @Column(name ="Employeno")
    private String empno;

    @Column(name = "joiningD")
    private String joiningDate;

    @Column(name ="dob")
    private  String dateofBirth;

    @Column(name="marriagestatus")
    private String status;

    @Column(name ="Role")
    private String role;

    @Column(name="ContactNumber")
    private String contactnumber;

    @Column(name ="Gender")
    private String gender;

    @Column(name="Contactname")
    private String contactname;

    @Column(name="Relation")
    private String relation;

    @Override
    public String toString() {
        return "UserEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", empno='" + empno + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", dateofBirth='" + dateofBirth + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", contactnumber='" + contactnumber + '\'' +
                ", gender='" + gender + '\'' +
                ", contactname='" + contactname + '\'' +
                ", relation='" + relation + '\'' +
                ", contactno='" + contactno + '\'' +
                ", country='" + country + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public UserEntity(String contactname, String relation, String contactno, String country) {
        this.contactname = contactname;
        this.relation = relation;
        this.contactno = contactno;
        this.country = country;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name="Contactno")
    private String contactno;

    @Column(name="country")
    private String country;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public UserEntity(String department) {
        this.department = department;
    }

    @Column(name="department")
    private String department;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserEntity(String email, String password, String firstname, String lastname, String empno, String joiningDate, String dateofBirth, String status, String role, String contactnumber, String gender) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.empno = empno;
        this.joiningDate = joiningDate;
        this.dateofBirth = dateofBirth;
        this.status = status;
        this.role = role;
        this.contactnumber = contactnumber;
        this.gender = gender;
    }

}

