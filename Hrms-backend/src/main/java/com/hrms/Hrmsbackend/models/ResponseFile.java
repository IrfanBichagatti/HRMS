package com.hrms.Hrmsbackend.models;

import lombok.Data;

@Data
public class ResponseFile {
    private String id;
    private String name;
    private String url;
    private String type;
    private long size;

    public ResponseFile(String id, String name, String url, String type, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}
