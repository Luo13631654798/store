package com.lyc.domain.VO;

import lombok.Data;

import java.io.Serializable;
@Data
public class CartVO implements Serializable {
    private Integer cid;
    private Integer pid;
    private Integer uid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;
}
