package com.lyc.mapper;


import com.lyc.domain.SellerLog;
import com.lyc.domain.User;
import com.lyc.domain.UserLog;
import com.lyc.domain.UserStopProductLog;
import com.lyc.domain.VO.SalesExceptionVO;
import com.lyc.domain.VO.SellerAchievementVO;
import com.lyc.domain.VO.UserCategoryCustomVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper{
    /**
     * 插入用户数据
     * @param user 待插入用户对象
     * @return 受影响的行数，根据返回值判断是否成功插入
     */
    public int insert(User user);


    /**
     * 根据用户名和身份查询用户
     * @param username 用户名
     * @param role 身份，0代表销售员，1代表普通用户
     * @return
     */
    public User findByUsername(String username,int role);

    /**
     * 查找所有销售员信息
     * @return
     */
    public List<User> findAllSeller();

    /**
     * 通过id删除用户
     * @param id 用户id
     * @return 受影响的行数
     */
    public int deleteUserById(Integer id);


    /**
     * 查询总用户数量
     * @return
     */
    public int findUserNum();

    /**
     * 通过id重置密码
     * @param id id
     * @return 受影响的行数
     */
    public int resetPasswordById(Integer id);

    /**
     * 插入用户日志
     * @param userLog 日志
     */
    public void insertLog(UserLog userLog);

    /**
     * 记录用户在商品页停留的时长
     * @param userStopProductLog 日志实体
     */
    public void insertUserStopProductLog(UserStopProductLog userStopProductLog);

    /**
     * 插入销售员操作日志
     * @param sellerLog 销售员操作日志
     */
    public void insertSellerLog(SellerLog sellerLog);


    /**
     * 查询所有用户id
     * @return
     */
    public List<Integer> findAllUserId();

    /**
     * 查询所有用户每种商品的消费额
     * @return
     */
    public List<UserCategoryCustomVO> findUserCategoryCustom();

    /**
     * 查询销售员业绩
     * @param uid
     * @param time
     * @return
     */
    public SellerAchievementVO findSellerAchievement(Integer uid,Integer time);

    /**
     * 插入管理员&销售员操作日志
     * @param uid
     * @param username
     * @param operation
     * @param ip
     * @param time
     */
    public void insertOperationLog(Integer uid, String username, String operation, String ip, Date time);
}
