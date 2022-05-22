package com.lyc.service;

import com.lyc.domain.VO.CartVO;

import java.util.List;

public interface CartService {

    /**
     * 把商品添加到购物车中
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增数量
     */
    public void addToCart(Integer uid,Integer pid,Integer amount);

    /**
     * 根据用户id查询所有购物车数据
     * @param uid 用户id
     * @return 购物车值对象
     */
    public List<CartVO> getByUid(Integer uid);

    /**
     *更新购物车商品项数量
     * @param cid 购物车id
     * @param add 为1表示添加，为0表示减少
     * @return
     */
    public int updateNum(Integer cid,Integer add);

    /**
     * 根据购物车id查询购物车数据
     * @param cids 购物车id数组
     * @return 购物车VO值对象
     */
    public List<CartVO> getVOByCid(Integer[] cids);

    /**
     * 删除购物车数据
     * @param cid 购物车id
     * @return 受影响行数
     */
    public int deleteByCid(Integer cid);
}
