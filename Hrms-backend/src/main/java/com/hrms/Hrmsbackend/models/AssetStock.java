package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "AssetStock")
public class AssetStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sno")
    private int sno;

    @Column(name = "parchaseDate")
    private String parchaseDate;

    @Column(name = "invoiceNumber")
    private String  invoiceNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String  status;

    @Column(name = "brand")
    private String  brand;

    @Column(name = "model")
    private String model;

    @Column(name = "originalValue")
    private String  originalValue;

    @Column(name = "value")
    private String  value;

    @Column(name = "warrantyDate")
    private String  warrantyDate;

    @Column(name = "warrantyStatus")
    private String  warrantyStatus;
}
