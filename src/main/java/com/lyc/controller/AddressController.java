package com.lyc.controller;

import com.lyc.domain.Address;
import com.lyc.domain.User;
import com.lyc.service.AddressService;
import com.lyc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private AddressService addressService;

    @RequestMapping("/addNewAddress")
    public JsonResult<Void> addNewAddress(HttpSession session,Address address){
        User user = getUsernameFromSession(session);
        addressService.addNewAddress(user.getId(),address);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/findByUid")
    public JsonResult<List<Address>> findByUid(HttpSession session){
        User user = getUsernameFromSession(session);
        return new JsonResult<List<Address>>(OK,addressService.findByUid(user.getId()));
    }
    @RequestMapping("/setDefaultAddress")
    public JsonResult<Void> setDefaultAddress(Integer aid,HttpSession session){
        User user = getUsernameFromSession(session);
        addressService.setDefaultAddress(aid,user.getId());
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("/deleteAddress")
    public JsonResult<Void> deleteAddress(Integer aid,HttpSession session){
        User user = getUsernameFromSession(session);
        addressService.deleteAddress(aid,user.getId());
        return new JsonResult<Void>(OK);
    }
}
