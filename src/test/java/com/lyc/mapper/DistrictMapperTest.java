package com.lyc.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;
    @Test
    void findByParent() {
        System.out.println(districtMapper.findByParent("110100"));
    }

    @Test
    void findNameByCode() {
        districtMapper.findNameByCode("230000");
    }
}