package com.lyc.service;

import com.lyc.domain.Address;
import com.lyc.domain.Order;
import com.lyc.domain.OrderItem;
import com.lyc.domain.VO.ProductSalesVO;

import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     * @param aid 地址id
     * @param uid 用户id
     * @param cids 购物车订单列表
     * @return 创建的订单
     */
    public Order create(Integer aid, Integer uid, Integer[] cids);

    /**
     * 查询用户所有订单
     * @param uid 用户id
     * @return 订单列表
     */
    public List<Order> getOrderByUid(Integer uid);

    /**
     * 查询某订单中的所有商品项
     * @param oid 订单id
     * @return  订单项列表
     */
    public List<OrderItem> getOrderItemByOid(Integer oid);

    /**
     * 根据订单号查询订单
     * @param oid 订单号
     * @return 订单
     */
    public Order getOneByOid(Integer oid);


    /**
     * 获取销售报表
     * @param time
     * @param cid
     * @return
     */
    public List<ProductSalesVO> getSalesReportByTimeAndCategoryId(Integer time,Integer cid);
}
