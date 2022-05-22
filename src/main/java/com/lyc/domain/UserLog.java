package com.lyc.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserLog {
    private Integer uid;
    private Date logTime;
    private String ip;
    private String status;

    public UserLog(Integer uid, Date logTime, String ip, String status) {
        this.uid = uid;
        this.logTime = logTime;
        this.ip = ip;
        this.status = status;
    }
}
