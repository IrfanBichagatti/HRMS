package com.hrms.Hrmsbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table(name = "quize")
@Getter
@Setter
public class QuizEntity {
    public  QuizEntity(){

    }
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private  String email;
    @Column
    private  String firstname;
    @Column
    private String lastname;
    @Column
    private String question;
    @Column
    private String answer;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    @Column
    private Date expirydate;

    public QuizEntity(int id, String email, String firstname, String lastname, String question, String answer, Date expirydate) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.question = question;
        this.answer = answer;
        this.expirydate = expirydate;
    }
}
