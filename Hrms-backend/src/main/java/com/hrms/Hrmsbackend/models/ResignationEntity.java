package com.hrms.Hrmsbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Getter
@Setter
@Table(name = "resign")
public class ResignationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "emailid")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;
    @Lob
    @Column(name = "comment1")
    private String comment;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "applydate")
    private Date applydate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "lastday")
    private Date lastday;

    @Column(name ="noticeperiod")
    private  int noticeperiod;

    @Column(name = "status")
    private String status;

    @Column(name = "status1")
    private String status1;

    @Override
    public String toString() {
        return "ResignationEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", comment='" + comment + '\'' +
                ", applydate=" + applydate +
                ", lastday=" + lastday +
                ", noticeperiod=" + noticeperiod +
                ", status='" + status + '\'' +
                ", status1='" + status1 + '\'' +
                '}';
    }

    public ResignationEntity(int id, String email, String firstname, String lastname, String comment, Date applydate, Date lastday, int noticeperiod, String status, String status1) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.comment = comment;
        this.applydate = applydate;
        this.lastday = lastday;
        this.noticeperiod = noticeperiod;
        this.status = status;
        this.status1 = status1;
    }

    public ResignationEntity(){

    }

}
