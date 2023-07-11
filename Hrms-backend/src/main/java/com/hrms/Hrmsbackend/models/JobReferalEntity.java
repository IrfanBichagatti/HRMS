package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="Jobrefrencedetail")
public class JobReferalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String firstname;
    private String lastname;
    private String contactemail;
    private String contactno;
    private String appliedposition;
    private String technology;
    private String refrencefirstname;
    private String refrencelastname;
    private String refrenceemail;

    public JobReferalEntity(int id, String firstname, String lastname, String contactemail, String contactno, String appliedposition, String technology, String refrencefirstname, String refrencelastname, String refrenceemail) {
        Id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactemail = contactemail;
        this.contactno = contactno;
        this.appliedposition = appliedposition;
        this.technology = technology;
        this.refrencefirstname = refrencefirstname;
        this.refrencelastname = refrencelastname;
        this.refrenceemail = refrenceemail;
    }
    public JobReferalEntity(){}
}
