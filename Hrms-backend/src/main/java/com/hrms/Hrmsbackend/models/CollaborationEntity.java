package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Collab")
@Getter
@Setter
public class CollaborationEntity {
    public CollaborationEntity(){

    }
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private  String email;
    @Column
    private  String content;
    @Column
    private  String firstname;
    @Column
    private String lastname;

    @Override
    public String toString() {
        return "CollaborationEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public CollaborationEntity(int id, String email, String content, String firstname, String lastname) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
