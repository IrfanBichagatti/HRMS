package com.hrms.Hrmsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table (name="UserAppliedJob")
@AllArgsConstructor
@NoArgsConstructor


public class UserApplyJob {
    @Id
    @GeneratedValue

    @Column (name=" id")


    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String jobtitle;

}
