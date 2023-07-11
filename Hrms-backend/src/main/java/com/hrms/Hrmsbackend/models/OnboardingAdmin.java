package com.hrms.Hrmsbackend.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "OnboardDoccAdmin")
public class OnboardingAdmin {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    private String status;

    public OnboardingAdmin(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}