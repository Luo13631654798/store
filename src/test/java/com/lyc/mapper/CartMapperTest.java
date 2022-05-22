package com.lyc.mapper;

import com.lyc.domain.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;
    @Test
    void insert() {
        Cart cart = new Cart();
        cart.setNum(5);
        cart.setPrice((long) 4549);
        cart.setUid(14);
        cart.setPid(10000008);
        cartMapper.insert(cart);
    }

    @Test
    void updateNumByCid() {
        cartMapper.updateNumByCid(1,6);
    }

    @Test
    void findByUidAndPid() {
        cartMapper.findByUidAndPid(14,10000008);
    }

    @Test
    void findByUid() {
        System.out.println(cartMapper.findByUid(14));
    }

    @Test
    void findVOByCid() {
        cartMapper.findVOByCid(new Integer[]{1,2,4});
    }

    @Test
    void deleteByCid() {
        cartMapper.deleteByCid(5);
    }
}