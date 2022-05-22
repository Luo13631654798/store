package com.lyc.controller;

import com.lyc.domain.User;
import com.lyc.service.ProductService;
import com.lyc.service.ex.*;
import com.lyc.utils.JsonResult;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class BaseController {
    //操作成功状态码：200
    public static final  int OK = 200;
    /**
     * 统一异常处理类
     * @param e 异常对象
     * @return jsonresult直接返回给前端
     */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleExcetion(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setStatus(4000);
            result.setMessage("用户名被占用");
        }else if (e instanceof InsertException){
            result.setStatus(5000);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof UsernameNotFoundException){
            result.setStatus(5001);
            result.setMessage("用户名不存在");
        }else if (e instanceof PasswordNotMatchException){
            result.setStatus(5002);
            result.setMessage("密码错误");
        }else if (e instanceof UserDeleteException){
            result.setStatus(5003);
            result.setMessage("用户删除产生未知异常");
        }else if (e instanceof ProductNotFoundException){
            result.setStatus(5004);
            result.setMessage("未查询到符合条件的商品");
        }else if (e instanceof AddressCountLimitException){
            result.setStatus(5005);
            result.setMessage("收货地址数达到上限");
        }else if (e instanceof LowStocksException){
            result.setStatus(5006);
            result.setMessage("商品库存不足");
        }else if (e instanceof OrderItemNumTooLargeException){
            result.setStatus(5007);
            result.setMessage("单件商品购买量超过最大值30，无法创建订单");
        }


        return result;
    }


    /**
     * 从session对象中获取用户
     * @param session session对象
     * @return 当前登录用户
     */
    protected final User getUsernameFromSession(HttpSession session){
        return (User) session.getAttribute("user");
    }

    protected final Integer pageSize=12;
}
