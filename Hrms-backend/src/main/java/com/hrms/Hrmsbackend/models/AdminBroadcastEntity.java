package com.hrms.Hrmsbackend.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "adminBroadcastMsg")
public class AdminBroadcastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sno")
    private int sno;

    @Column(name = "Title")
    private String title;

    @Column(name = "Msg")
    private String msg;

    @Column(name="msgDate")
   private String msgdate;

    public AdminBroadcastEntity(int sno,String title, String msg,String msgdate) {
        this.sno=sno;
        this.title = title;
        this.msg = msg;
        this.msgdate=msgdate;
    }

    public AdminBroadcastEntity() {
    }
}
