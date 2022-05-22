package com.lyc.service;

import com.lyc.domain.Address;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface AddressService {
    /**
     * 插入收货地址
     * @param uid
     * @param address
     * @return
     */
    public int addNewAddress(Integer uid,Address address);

    /**
     * 根据用户id查询收货地址
     * @param id 用户id
     * @return 收货地址列表
     */
    public List<Address> findByUid(Integer id);

    /**
     * 根据地址id和用户id更改用户默认地址
     * @param aid 地址id
     * @param uid 用户id
     * @return
     */
    public int setDefaultAddress(Integer aid,Integer uid);

    /**
     * 删除地址
     * @param aid 地址id
     * @param uid 用户id
     * @return
     */
    public void deleteAddress(Integer aid,Integer uid);

    /**
     * 根据地址id查询地址
     * @param aid 地址id
     * @param uid 用户id
     * @return 地址
     */
    public Address getByAid(Integer aid,Integer uid);
}
