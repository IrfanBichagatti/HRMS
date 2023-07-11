package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "joblist")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobId;
    private String jobTitle;
    private String jobTechnology;
    private Integer Noofposition;
    private Integer Totalexperiance;
    private String Jobprofile;

    public JobEntity(Integer jobId, String jobTitle, String jobTechnology, Integer noofposition, Integer totalexperiance, String jobprofile) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobTechnology = jobTechnology;
        Noofposition = noofposition;
        Totalexperiance = totalexperiance;
        Jobprofile = jobprofile;
    }
    public JobEntity(){

    }
}
