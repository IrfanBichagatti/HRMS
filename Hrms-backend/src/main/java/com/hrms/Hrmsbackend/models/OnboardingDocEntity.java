package com.hrms.Hrmsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "OnboardDoccEmp")
public class OnboardingDocEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "Email")
    private String email;

    private String name;
    private String type;
    @Lob
    private byte[] data;
    private String status;


    public OnboardingDocEntity( String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
