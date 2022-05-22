package com.lyc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Test
    void findProductByCategoryId(){
        System.out.println(productService.findProductByCategoryId(3, 12, 1));
    }

    @Test
    void findUserProductLog(){
        System.out.println(productService.findUserProductLog(3,1));
    }

    @Test
    void findHotProduct(){
        System.out.println(productService.findHotProduct().size());
    }

}
