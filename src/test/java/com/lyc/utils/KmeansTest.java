package com.lyc.utils;

import com.lyc.mapper.UserMapper;
import com.lyc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KmeansTest {

    @Autowired
    private UserService userService;

    @Test
    void readTable() {
    }

    @Test
    void randomList() {
    }

    @Test
    void minNumber() {
    }

    @Test
    void eudistance(){
        userService.getUserCategoryConsumption();
        List<List<Long>> rlist = Kmeans.randomList();
        System.out.println(Kmeans.matrix);
        System.out.println(Kmeans.randomlist);
        Kmeans.eudistance(rlist);
        Kmeans.kmeans();
    }

    @Test
    void newList() {
    }

    @Test
    void same() {
    }

    @Test
    void kmeans() {
    }

    @Test
    void main() {
    }
}