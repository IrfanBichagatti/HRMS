package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AlertData")
public class AlertData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sno")
    private int sno;

    @Column(name = "Email")
    private String email;

    @Column(name = "Message")
    private String message;

    public AlertData(String email, String message) {
        this.email = email;
        this.message = message;
    }


    public AlertData() {
    }

}
