package com.lyc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DistrictServiceTest {
    @Autowired
    private DistrictService districtService;
    @Test
    void getByParent(){
        System.out.println(districtService.getByParent("86"));
    }
}