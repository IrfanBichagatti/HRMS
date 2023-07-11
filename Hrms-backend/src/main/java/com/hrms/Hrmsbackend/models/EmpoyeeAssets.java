package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@Table(name = "EmpoyeeAssets")
public class EmpoyeeAssets {
    @Id
    private int empId;
    private String empName;
    private String empEmail;
    private String empStatus;

    private String laptop;
    private String monitor;
    private String headfone;
    private String hdmi;
    private String  earphone;
    private String mobile;
    private String pendrive;
    private String  laptopBag;
    private String   keyboard;
    private String  mouse;

    private String laptopBrand;
    private String  laptopModel;
    private String  laptopInvoiceNumber;

    private String  monitorBrand;
    private String  monitorModel;
    private String  monitorInvoiceNumber;

    private String  headfoneBrand;
    private String headfoneModel;
    private String headfoneInvoiceNumber;


    private String hdmiBrand;
    private String  hdmiModel;
    private String  hdmiInvoiceNumber;

    private String earphoneBrand;
    private String earphoneModel;
    private String  earphoneInvoiceNumber;

    private String mobileBrand;
    private String mobileModel;
    private String mobileInvoiceNumber;

    private String pendriveBrand;
    private String pendriveModel;
    private String pendriveInvoiceNumber;


    private String laptopBagBrand;
    private String laptopBagModel;
    private String laptopBagInvoiceNumber;

    private String  keyboardBrand;
    private String  keyboardModel;
    private String keyboardInvoiceNumber;

    private String  mouseBrand;
    private String mouseModel;
    private String mouseInvoiceNumber;
}
