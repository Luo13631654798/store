package com.lyc.service.impl;

import com.lyc.domain.Address;
import com.lyc.domain.Order;
import com.lyc.domain.OrderItem;
import com.lyc.domain.Product;
import com.lyc.domain.VO.CartVO;
import com.lyc.domain.VO.ProductSalesVO;
import com.lyc.mapper.OrderMapper;
import com.lyc.mapper.ProductMapper;
import com.lyc.service.AddressService;
import com.lyc.service.CartService;
import com.lyc.service.OrderService;
import com.lyc.service.ex.InsertException;
import com.lyc.service.ex.LowStocksException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Order create(Integer aid, Integer uid, Integer[] cids) {
        List<CartVO> cartVOList = cartService.getVOByCid(cids);

        Long total = 0L;
        for (CartVO cartVO : cartVOList) {
            total+=cartVO.getNum()*cartVO.getRealPrice();
            if (cartVO.getNum()>=30){
                productMapper.insertExceptionLog(uid,cartVO.getPid(),"单次购买量超过30",new Date());
            }
        }

        Address address = addressService.getByAid(aid, uid);

        Order order = new Order();
        order.setUid(uid);

        order.setRecvName(address.getName());
        order.setRecvAddress(address.getAddress());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());

        order.setTotalPrice(total);
        order.setStatus(0);
        order.setOrderTime(new Date());
        int i = orderMapper.insertOrder(order);
        if (i!=1){
            throw new InsertException("插入时异常");
        }


        for (CartVO cartVO : cartVOList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setPid(cartVO.getPid());
            orderItem.setOid(orderMapper.findLastOidByUid(uid));
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            int i1 = orderMapper.insertOrderItem(orderItem);
            Product product = productMapper.findProductById(cartVO.getPid());
            Integer num = product.getNum();
            productMapper.updateProductScore(cartVO.getPid(),cartVO.getNum().doubleValue());
            if (num==0){
                throw new LowStocksException("商品库存不足，请重试");
            }else {
                product.setNum(num - 1);
            }
            cartService.deleteByCid(cartVO.getCid());
            if (i1!=1){
                throw new InsertException("插入时异常");
            }
        }
        if (order.getTotalPrice()>100000){
            productMapper.insertExceptionLog(uid,null,"单次订单额大于10万元",new Date());
        }
        return order;

    }

    @Override
    public List<OrderItem> getOrderItemByOid(Integer oid) {

        return orderMapper.findByOid(oid);

    }

    @Override
    public Order getOneByOid(Integer oid) {
        return orderMapper.findOneByOid(oid);
    }

    @Override
    public List<ProductSalesVO> getSalesReportByTimeAndCategoryId(Integer time, Integer cid) {
        return orderMapper.findProductSalesByTimeAndCategory(time,cid);
    }

    @Override
    public List<Order> getOrderByUid(Integer uid) {
        return orderMapper.findByUid(uid);
    }
}
