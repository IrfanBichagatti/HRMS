package com.hrms.Hrmsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "UserRegularizationEntity")
public class UserRegularizationEntity {
    @Id
    @Column(name = "sno")
    private int sno;

    @Column(name = "regularizationDate")
   private String regularizationDate;

    @Column(name = "username")
   private String username;

    @Column(name = "policy")
   private String policy;

    @Column(name = "intime")
   private String intime;

    @Column(name = "outtime")
   private String outtime;

    @Column(name = "manager")
   private String manager;

    @Column(name = "userComment")
   private String userComment;

    @Column(name = "status")
    private String status;

    @Column(name = "compOfCredit")
    private String compOfCredit;



    public UserRegularizationEntity(int sno, String regularizationDate, String username, String policy, String intime, String outtime, String manager, String userComment, String status, String compOfCredit) {
        this.sno = sno;
        this.regularizationDate = regularizationDate;
        this.username = username;
        this.policy = policy;
        this.intime = intime;
        this.outtime = outtime;
        this.manager = manager;
        this.userComment = userComment;
        this.status = status;
        this.compOfCredit = compOfCredit;
    }

    public UserRegularizationEntity() {
    }
}
