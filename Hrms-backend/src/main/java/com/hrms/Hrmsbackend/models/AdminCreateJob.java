package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="AdminCreateJob")
public class AdminCreateJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sno")
    private int sno;

    private String title;
    private int NumberofPositions;
    private String OpenJobs;
    private String JobProfile;
    private String TotalExperience;
    private String Technologies;
    private String Application;
    private String Shortlist;
    private String Screening;
    private String Interview;
    private String Offered;
    private String Confirmed;

    public AdminCreateJob(int sno, String title, int numberofPositions, String openJobs, String jobProfile, String totalExperience, String technologies, String application, String shortlist, String screening, String interview, String offered, String confirmed) {
        this.sno = sno;
        this.title = title;
        this.NumberofPositions = numberofPositions;
        this.OpenJobs = openJobs;
        this.JobProfile = jobProfile;
        this.TotalExperience = totalExperience;
        this.Technologies = technologies;
        this.Application = application;
        this.Shortlist = shortlist;
        this.Screening = screening;
        this.Interview = interview;
        this.Offered = offered;
        this.Confirmed = confirmed;
    }



    public AdminCreateJob(){

    }
}
