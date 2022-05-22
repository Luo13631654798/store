package com.lyc.mapper;

import com.lyc.domain.Cart;
import com.lyc.domain.VO.CartVO;

import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响行数
     */
    public int insert(Cart cart);

    /**
     * 删除购物车数据
     * @param cid 购物车id
     * @return 受影响行数
     */
    public int deleteByCid(Integer cid);
    /**
     * 更新购物车商品数量
     * @param cid 购物车id
     * @param num 更新后的数量
     * @return 受影响行数
     */
    public int updateNumByCid(Integer cid,Integer num);

    /**
     * 根据用户id和商品id查询购物车数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 购物车数据
     */
    public Cart findByUidAndPid(Integer uid,Integer pid);

    /**
     * 根据用户id查询所有购物车数据
     * @param uid 用户id
     * @return 购物车值对象
     */
    public List<CartVO> findByUid(Integer uid);

    /**
     * 通过cid查找购物车项
     * @param cid 购物车id
     * @return 购物车项
     */
    public Cart findByCid(Integer cid);

    /**
     * 根据购物车id查询购物车数据
     * @param cids 购物车id数组
     * @return 购物车VO值对象
     */
    public List<CartVO> findVOByCid(Integer[] cids);


}
