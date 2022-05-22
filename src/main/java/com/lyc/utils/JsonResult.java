package com.lyc.utils;

import java.io.Serializable;
public class JsonResult<E> implements Serializable {
    //状态码
    private Integer status;
    //描述信息
    private String message;
    //数据信息
    private E data;
    public JsonResult(Integer status, E data) {
        this.status = status;
        this.data = data;
    }

    public JsonResult(Integer status) {
        this.status = status;
    }

    public JsonResult() {
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
