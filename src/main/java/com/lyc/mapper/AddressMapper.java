package com.lyc.mapper;

import com.lyc.domain.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    /**
     * 插入用户收货地址
     * @param address 地址
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户id查询用户地址数
     * @param id 用户id
     * @return 用户地址数
     */
    Integer countByUid(Integer id);

    /**
     * 根据用户id查询收货地址
     * @param id 用户id
     * @return 收货地址列表
     */
    public List<Address> findByUid(Integer id);

    /**
     * 根据地址id查询收货地址
     * @param aid 地址id
     * @return 收货地址
     */
    public Address findByAid(Integer aid);

    /**
     * 将用户id下的地址全部设置为非默认
     * @param uid
     * @return 受影响行数
     */
    public int setNonDefaultByUid(Integer uid);

    /**
     * 设置用户默认收货地址
     * @param aid 地址id
     * @param uid 用户id
     * @return 受影响的行数
     */
    public int setDefault(Integer aid,Integer uid);

    /**
     * 删除某条地址
     * @param aid 地址id
     * @return 受影响的行数
     */
    public int delete(Integer aid);

    /**
     * 找到某用户对应的最大地址id对应的地址
     * @param uid 用户id
     * @return 地址
     */
    public Address findMaxAid(Integer uid);
}
