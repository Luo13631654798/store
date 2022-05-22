package com.lyc.controller;

import com.lyc.domain.Order;
import com.lyc.domain.OrderItem;
import com.lyc.domain.SellerLog;
import com.lyc.domain.VO.ProductSalesVO;
import com.lyc.mapper.UserMapper;
import com.lyc.service.OrderService;
import com.lyc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, Integer cids[], HttpSession session){
        Order order = orderService.create(aid, getUsernameFromSession(session).getId(), cids);
        return new JsonResult<Order>(OK,order);
    }

    @RequestMapping("/getOrderItem")
    public JsonResult<List<OrderItem>> getOrderItemByOid(Integer oid) {

        return new JsonResult<List<OrderItem>>(OK,orderService.getOrderItemByOid(oid));

    }

    @RequestMapping("/getOrder")
    public JsonResult<List<Order>> getOrderByUid(HttpSession session) {
        return new JsonResult<List<Order>>(OK,orderService.getOrderByUid(getUsernameFromSession(session).getId()));
    }

    @RequestMapping("/getOne")
    public JsonResult<Order> getOne(Integer oid) {
        return new JsonResult<Order>(OK,orderService.getOneByOid(oid));
    }

    @RequestMapping("/getSalesReport")
    public JsonResult<List<ProductSalesVO>> getSalesReport(Integer cid, Integer time, HttpServletRequest request, HttpSession session) {
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"查询id为【"+cid+"】类商品"+time+"天内的销售报表",getUsernameFromSession(session).getId()));

        return new JsonResult<List<ProductSalesVO>>(OK,orderService.getSalesReportByTimeAndCategoryId(time, cid));
    }
}
