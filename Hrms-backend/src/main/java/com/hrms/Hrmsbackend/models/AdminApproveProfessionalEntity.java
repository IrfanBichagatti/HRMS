package com.hrms.Hrmsbackend.models;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.Column;

import javax.persistence.Id;


@Data
@Entity
@Table(name="AdminApproveprofessional")

public class AdminApproveProfessionalEntity {

    @Id
    @Column(name="email")
    private String email;

    @Column(name="degree")
    private String degree;
    @Column(name="specification")
    private String specification;
    @Column(name="college")
    private String college;
    @Column(name="primary_skill")
    private String primary_skill;
    @Column(name="secondary_skill")
    private String secondary_skill;
    @Column(name="organization_name")
    private String organization_name;

    @Column(name="status")
    private String status;

    public AdminApproveProfessionalEntity(){
        super();
    }
    public AdminApproveProfessionalEntity(String status,String email,String degree,String specification,String college,String primary_skill,String secondary_skill,String organization_name){
        this.email=email;
        this.degree=degree;
        this.specification=specification;
        this.college=college;
        this.primary_skill=primary_skill;
        this.secondary_skill=secondary_skill;
        this.organization_name=organization_name;
        this.status=status;


    }


    public String getstatus(){
        return status;
    }

    public void setstatus(String status){
        this.status=status;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getDegree(){
        return degree;
    }

    public void setDegree(String degree){
        this.degree=degree;
    }

    public String getSpecification(){
        return specification;
    }

    public void setSpecification(String specification){
        this.specification=specification;
    }

    public String getCollege(){
        return college;
    }

    public void setCollege(String college){
        this.college=college;
    }

    public String getPrimary_skill(){
        return primary_skill;
    }

    public void setPrimary_skill(String primary_skill){
        this.primary_skill=primary_skill;
    }

    public String getSecondary_skill(){
        return secondary_skill;
    }

    public void setSecondary_skill(String secondary_skill){
        this.secondary_skill=secondary_skill;
    }

    public String getOrganization_name(){
        return organization_name;
    }

    public void setOrganization_name(String organization_name){
        this.organization_name=organization_name;
    }
}
