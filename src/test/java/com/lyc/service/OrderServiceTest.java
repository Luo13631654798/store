package com.lyc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    void create() {
        orderService.create(6,14,new Integer[]{3,4});
    }

    @Test
    void getSalesReportByTimeAndCategoryId(){
        System.out.println(orderService.getSalesReportByTimeAndCategoryId(500,3));
    }
}