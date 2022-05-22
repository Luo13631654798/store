package com.lyc.service;

import com.lyc.domain.User;
import com.lyc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void testRegist(){
        User user = new User();
        user.setUsername("user");
        user.setPassword("12345");
        user.setRole(1);
        userService.regist(user);
    }

    @Test
    public void testLogin(){
        User user = userService.login("testname1", "testpassword1",1);
        System.out.println(user);
    }

    @Test
    public void testQueryAllSeller(){
        List<User> allSeller = userService.queryAllSeller();
        System.out.println(allSeller);
    }

    @Test
    public void getNumByCustomer(){

        double[] nums = userService.getVectorByUid(14);
        int i=0;
        for (double num : nums) {
            System.out.println(num);
            if (num==6.5){
                System.out.println("-------"+i);
            }
            i++;
        }
    }

    @Test
    void getMaxSimilarity(){
        List<Map.Entry<Integer, Double>> list = userService.getMaxSimilarity(28);
        int index = 0;

        for (Map.Entry<Integer, Double> entry : list) {
            if (index<10){
                System.out.println(entry.getKey()+"->"+entry.getValue());
            }

        }
    }

    @Test
    void getProduct(){
        List<Map.Entry<Integer, Double>> maxSimilarity = userService.getMaxSimilarity(14);
        System.out.println(maxSimilarity);
        System.out.println(userService.getProducts(maxSimilarity));
    }



}
