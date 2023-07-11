package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Getter
@Setter
@Table(name = "AssetView")
public class AssetView {
    @Id
    @Column(name ="assetName")
    private String assetName;

    @Column(name ="total")
    private String total;

    @Column(name ="alloted")
    private String alloted;

    @Column(name ="inventory")
    private String inventory;

    @Column(name ="damaged")
    private String damaged;

    @Column(name ="lost")
    private String lost;
}
