package com.lyc.domain;

import lombok.Data;

import java.util.Date;
@Data
public class SellerLog {
    private Date date;
    private String ip;
    private String username;
    private String operation;
    private Integer uid;

    public SellerLog(Date date, String ip, String username, String opertation, Integer uid) {
        this.date = date;
        this.ip = ip;
        this.username = username;
        this.operation = opertation;
        this.uid = uid;
    }
}
