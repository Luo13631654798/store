package com.lyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.domain.User;
import com.lyc.domain.VO.PidAndNumVO;
import com.lyc.domain.VO.SellerAchievementVO;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public interface UserService{

    /**
     * 用户注册方法
     * @param user 用户
     */
    public void regist(User user);

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 密码
     * @param role 身份
     * @return
     */
    public User login(String username,String password,int role);

    /**
     * 获取所有销售员信息
     * @return
     */
    public List<User> queryAllSeller();

    /**
     * 删除用户
     * @param id 带删除用户id
     * @return
     */
    public int deleteUser(Integer id);

    /**
     * 重置密码
     * @param id
     * @return
     */
    public int resetPassword(Integer id);

    /**
     * 获取某用户的行为向量（购买某商品记1，浏览某商品记0.5）
     * @param id
     * @return
     */
    public double[] getVectorByUid(Integer id);

    /**
     * 计算余弦相似度最高的n个用户
     * @param uid
     * @return
     */
    public List<Map.Entry<Integer,Double>> getMaxSimilarity(Integer uid);


    /**
     * 获得相似用户集合购买的商品，并统计相似用户购买的商品的数量，进行排序
     * @param list
     * @return
     */
    public PriorityQueue<Integer> getProducts(List<Map.Entry<Integer,Double>> list);

    /**
     * 获取用户-商品类-消费额矩阵
     * @return
     */
    public void getUserCategoryConsumption();

    /**
     * 查询销售员业绩
     * @param uid
     * @param time
     * @return
     */
    public SellerAchievementVO getSellerAchievement(Integer uid, Integer time);

}
