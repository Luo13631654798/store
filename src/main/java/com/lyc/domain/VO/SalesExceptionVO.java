package com.lyc.domain.VO;

import lombok.Data;

import java.util.Date;
@Data
public class SalesExceptionVO {
    int uid;
    int pid;
    String exception;
    Date time;
}
