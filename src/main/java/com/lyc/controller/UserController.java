package com.lyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.lyc.domain.*;
import com.lyc.domain.VO.SellerAchievementVO;
import com.lyc.mapper.ProductMapper;
import com.lyc.mapper.UserMapper;
import com.lyc.service.ProductService;
import com.lyc.service.UserService;
import com.lyc.service.ex.InsertException;
import com.lyc.service.ex.UsernameDuplicatedException;
import com.lyc.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @RequestMapping("/regist")
    public JsonResult<Void> regist(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(1);
        userService.regist(user);
        return new JsonResult<>(OK);
    }


    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, int role, HttpServletRequest request,HttpSession session) throws ParseException {

        User user = userService.login(username, password,role);

        session.setAttribute("user",user);

        userMapper.insertLog(new UserLog(getUsernameFromSession(session).getId(),new Date(),request.getRemoteAddr(),"登录"));

        return new JsonResult<User>(OK,user);
    }

    @RequestMapping("/logout")
    public JsonResult<User> logout(HttpSession session,HttpServletRequest request) throws ParseException {
        userMapper.insertLog(new UserLog(getUsernameFromSession(session).getId(),new Date(),request.getRemoteAddr(),"注销"));


        session.invalidate();
//        session.removeAttribute("user");



        return new JsonResult<User>(OK);
    }

    @RequestMapping("/recommend")
    public JsonResult<List<Product>> recommend(HttpSession session){
        User user = (User) session.getAttribute("user");

        List<Map.Entry<Integer, Double>> maxSimilarity = userService.getMaxSimilarity(user.getId());

        PriorityQueue<Integer> productsIds = userService.getProducts(maxSimilarity);

//        System.out.println(productsIds);

        List<Integer> list = new ArrayList<>(productsIds);

        List<Product> productList = new ArrayList<>();
        for (Integer pid : list) {
            Product product = productMapper.findProductById(pid);
            productList.add(product);
        }
        System.out.println(list);
        return new JsonResult<List<Product>>(OK,productList);
    }

    @RequestMapping("/queryAllSeller")
    public JsonResult<List<User>> queryAllSeller(){
        List<User> sellerList = userService.queryAllSeller();
        return new JsonResult<List<User>>(OK,sellerList);
    }

    @RequestMapping("/deleteSeller")
    public ModelAndView deleteSeller(int id,HttpServletRequest request,HttpSession session){
        userService.deleteUser(id);
        ModelAndView modelAndView = new ModelAndView();
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"删除id为【"+id+"】销售员",getUsernameFromSession(session).getId()));

        modelAndView.setViewName("redirect:/web/pages/manager/seller_manage.html");
        return modelAndView;
    }

    @RequestMapping("/resetPassword")
    public ModelAndView resetPassword(int id,HttpServletRequest request,HttpSession session){
        userService.resetPassword(id);
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"重置id为【"+id+"】销售员密码为12345",getUsernameFromSession(session).getId()));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/web/pages/manager/seller_manage.html");
        return modelAndView;
    }

    @RequestMapping("/addSeller")
    public JsonResult<Void> addSeller(String username, String password,HttpServletRequest request,HttpSession session){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(0);
        userService.regist(user);
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"添加用户名为【"+username+"】销售员",getUsernameFromSession(session).getId()));
        return new JsonResult<>(OK);
    }

    @RequestMapping("/getUserFromSession")
    public JsonResult<User> addSeller(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return new JsonResult<User>(OK,user);
    }

    @RequestMapping("/userStopLog")
    public void UserStopLog(Integer pid, Integer second, HttpSession session){
        Integer uid = getUsernameFromSession(session).getId();
        userMapper.insertUserStopProductLog(new UserStopProductLog(uid,pid,second,new Date()));
        productMapper.updateProductScore(pid,0.5);
    }


    @RequestMapping("/getSellerAchievement")
    public JsonResult<SellerAchievementVO> getSellerAchievement(Integer uid, Integer time,HttpServletRequest request,HttpSession session){
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"查询id为【"+uid+"】销售员"+time+"天内销售业绩",getUsernameFromSession(session).getId()));

        return new JsonResult<SellerAchievementVO>(OK,userService.getSellerAchievement(uid, time));
    }
}
