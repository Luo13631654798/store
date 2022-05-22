package com.lyc.controller;


import com.lyc.domain.VO.CartVO;
import com.lyc.service.CartService;
import com.lyc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
    @Autowired
    private CartService cartService;

    @RequestMapping("/addToCart")
    public JsonResult<Void> addToCart(HttpSession session,Integer pid,Integer amount){
        cartService.addToCart(getUsernameFromSession(session).getId(),pid,amount);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("/getCart")
    public JsonResult<List<CartVO>> getCart(HttpSession session){
        return new JsonResult<List<CartVO>>(OK,cartService.getByUid(getUsernameFromSession(session).getId()));
    }

    @RequestMapping("/updateNum")
    public JsonResult<Void> updateNum(Integer cid,Integer add){
        cartService.updateNum(cid,add);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/showSelectCart")
    public JsonResult<List<CartVO>> showSelectCart(Integer cids[]){
        return new JsonResult<List<CartVO>>(OK,cartService.getVOByCid(cids));
    }

    @RequestMapping("delete")
    public JsonResult<Void> deleteByCid(Integer cid) {
        cartService.deleteByCid(cid);
        return new JsonResult<Void>(OK);
    }
}
