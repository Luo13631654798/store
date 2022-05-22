package com.lyc.mapper;

import com.lyc.domain.Order;
import com.lyc.domain.OrderItem;
import com.lyc.domain.VO.PidAndNumVO;
import com.lyc.domain.VO.ProductSalesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单表
     * @param order 订单
     * @return 受影响行数
     */
    public int insertOrder(Order order);
    /**
     * 插入订单项表
     * @param orderItem 订单项
     * @return 受影响行数
     */
    public int insertOrderItem(OrderItem orderItem);

    /**
     * 根据用户id查找最新订单号
     * @param uid 用户id
     * @return
     */
    public int findLastOidByUid(Integer uid);

    /**
     * 查询用户所有订单
     * @param uid 用户id
     * @return 订单列表
     */
    public List<Order> findByUid(Integer uid);

    /**
     * 根据订单号查询订单
     * @param oid 订单号
     * @return 订单
     */
    public Order findOneByOid(Integer oid);

    /**
     * 查询某订单中的所有商品项
     * @param oid 订单id
     * @return  订单项列表
     */
    public List<OrderItem> findByOid(Integer oid);

    /**
     * 通过用户id查询商品项
     * @param uid
     * @return
     */
    public List<PidAndNumVO> findOrderItemPidByUid(Integer uid);


    /**
     * 查询某商品类在指定时间内的销量
     * @param time
     * @param cid
     * @return
     */
    public List<ProductSalesVO> findProductSalesByTimeAndCategory(Integer time, Integer cid);
}
