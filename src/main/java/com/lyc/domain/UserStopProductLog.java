package com.lyc.domain;

import lombok.Data;

import java.util.Date;
@Data
public class UserStopProductLog {
    private Integer uid;
    private Integer pid;
    private Integer second;
    private Date date;

    public UserStopProductLog(Integer uid, Integer pid, Integer second, Date date) {
        this.uid = uid;
        this.pid = pid;
        this.second = second;
        this.date = date;
    }

    public UserStopProductLog(Integer uid, Integer pid, Integer second) {
        this.uid = uid;
        this.pid = pid;
        this.second = second;
    }

}
