package com.lyc.mapper;

import com.lyc.domain.Order;
import com.lyc.domain.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Test
    void insertOrder() {
        Order order = new Order();
        order.setUid(14);
        order.setRecvName("张三");
        order.setOrderTime(new Date());
        order.setTotalPrice((long) 1000);
        orderMapper.insertOrder(order);
    }

    @Test
    void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000008);
        orderItem.setTitle("戴尔Dell 燃700R1605经典版银色");
        orderMapper.insertOrderItem(orderItem);
    }

    @Test
    void findLastOidByUid() {
        System.out.println(orderMapper.findLastOidByUid(14));
    }

    @Test
    void findByUid() {
        System.out.println(orderMapper.findByUid(14));
    }

    @Test
    void findByOid() {
        System.out.println(orderMapper.findByOid(5));
    }

    @Test
    public void findOrderItemPidByUid(){
        System.out.println(orderMapper.findOrderItemPidByUid(14));
    }

    @Test
    void findProductSalesByTimeAndCategory(){
        System.out.println(orderMapper.findProductSalesByTimeAndCategory(500,3));
    }
}