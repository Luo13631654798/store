package com.lyc.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class Product implements Serializable {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;

}
