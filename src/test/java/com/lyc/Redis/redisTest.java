package com.lyc.Redis;

import org.aspectj.lang.annotation.Around;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class redisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void testRedis(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("test",123);
        System.out.println(ops.get("test"));
    }





}
