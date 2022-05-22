package com.lyc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceTest {
    @Autowired
    private CartService cartService;
    @Test
    void addToCart() {
        cartService.addToCart(14,10000008,10);
        cartService.addToCart(14,10000007,10);
    }
}