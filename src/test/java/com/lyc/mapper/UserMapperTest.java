package com.lyc.mapper;


import com.lyc.domain.SellerLog;
import com.lyc.domain.User;
import com.lyc.domain.UserLog;
import com.lyc.domain.UserStopProductLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("testname2");
        user.setPassword("testpassword3");
        user.setRole(1);
        Integer row = userMapper.insert(user);
        System.out.println(row);
    }

    @Test
    public void testFindByUsername(){
        User user = userMapper.findByUsername("testname1",1);
        System.out.println(user);
    }

    @Test
    public void testFindAllSeller(){
        List<User> seller = userMapper.findAllSeller();
        System.out.println(seller);
    }

    @Test
    public void testDeleteUserById(){
        int i = userMapper.deleteUserById(8);
        System.out.println(i);
    }

    @Test
    public void testResetPasswordById(){
        int i = userMapper.resetPasswordById(7);
        System.out.println(i);
    }

    @Test
    public void testInsertLog(){
        userMapper.insertLog(new UserLog(14,new Date(),"127.0.0.1","登录"));
    }

    @Test
    public void testInsertUserStopProductLog(){
//        userMapper.insertLog(new UserLog(14,new Date(),"127.0.0.1","登录"));
        userMapper.insertUserStopProductLog(new UserStopProductLog(14,123,50,new Date()));
    }

    @Test
    public void testInsertSellerLog(){
//        userMapper.insertLog(new UserLog(14,new Date(),"127.0.0.1","登录"));
//        userMapper.insertUserStopProductLog(new UserStopProductLog(14,123,50,new Date()));
        userMapper.insertSellerLog(new SellerLog(new Date(),"127.0.0.1","123","456",14));

    }

    @Test
    void findAllUserId(){
        System.out.println(userMapper.findAllUserId());
    }

    @Test
    void findUserCategoryCustom(){
        System.out.println(userMapper.findUserCategoryCustom());
    }

    @Test
    void findSellerAchievement(){
        System.out.println(userMapper.findSellerAchievement(24,7));
    }

    @Test
    void insertOperationLog(){
        userMapper.insertOperationLog(14,"123","456","127.0.0.1",new Date());

    }
}
