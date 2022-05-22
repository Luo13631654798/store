package com.lyc.controller;

import com.lyc.domain.Product;
import com.lyc.domain.ProductCategory;
import com.lyc.domain.SellerLog;
import com.lyc.domain.VO.SalesExceptionVO;
import com.lyc.domain.VO.UsernameProductDateVO;
import com.lyc.mapper.ProductMapper;
import com.lyc.mapper.UserMapper;
import com.lyc.service.ProductService;
import com.lyc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @RequestMapping("/getProductCategoryBySellerid")
    public JsonResult<List<ProductCategory>> getProductCategoryBySellerid(Integer id){
        List<ProductCategory> categoryList = productService.findProductCategoryBySellerid(id);
        return new JsonResult<List<ProductCategory>>(OK,categoryList);
    }

    @RequestMapping("/deleteProductCategorySeller")
    public JsonResult<Void> deleteProductCategorySeller(Integer id,HttpServletRequest request,HttpSession session){
        productService.deleteProductCategorySeller(id);
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"移除商品id为【"+id+"】类商品的销售员",getUsernameFromSession(session).getId()));

        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/findNullSellerProductCategory")
    public JsonResult<List<String>> findNullSellerProductCategory(){
        List<String> nullSellerProductCategory = productService.findNullSellerProductCategory();
        return new JsonResult<List<String>>(OK,nullSellerProductCategory);
    }
    @RequestMapping("/addProductCategorySeller")
    public JsonResult<Void> addProductCategorySeller(String pName,Integer sid,HttpServletRequest request,HttpSession session){
        productService.addProductCategorySeller(pName, sid);
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"添加id为【"+sid+"】销售员对【"+pName+"】类商品的管理权",getUsernameFromSession(session).getId()));

        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/findProductByKeyWord")
    public JsonResult<List<Product>> findProductByKeyWord(String keyWord, Integer page){
        return new JsonResult<List<Product>>(OK,productService.findProductByKeyWord(keyWord,pageSize,(page-1)*12));
    }

    @RequestMapping("/findProductById")
    public JsonResult<Product> findProductById(Integer id){
        Product product = productService.findProductById(id);
        return new JsonResult<Product>(OK,product);
    }

    @RequestMapping("/findProductByCategoryId")
    public JsonResult<List<Product>> findProductByCategoryId(int categoryId, Integer page){
        return new JsonResult<List<Product>>(OK,productService.findProductByCategoryId(categoryId,pageSize,(page-1)*12));
    }

    @RequestMapping("/findByCategoryId")
    public JsonResult<List<Product>> findByCategoryId(int categoryId,HttpServletRequest request,HttpSession session){
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"查看【"+productMapper.findCategoryName(categoryId)+"】类商品",getUsernameFromSession(session).getId()));

        return new JsonResult<List<Product>>(OK,productService.findByCategoryId(categoryId));
    }

    @RequestMapping("/deleteByPid")
    public JsonResult<Void> deleteByPid(int pid,HttpServletRequest request,HttpSession session){
        Product product = productService.findProductById(pid);
        productService.deleteByPid(pid);
        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"删除商品【"+product.getTitle()+"】",getUsernameFromSession(session).getId()));

        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/insert")
    public JsonResult<Void> insert(Integer cid, Long price, String title, Integer num, HttpServletRequest request, HttpSession session){

        System.out.println(cid+title);
        Product product = new Product();
        product.setNum(num);
        product.setPrice(price);
        product.setCategoryId(cid);
        product.setTitle(title);
        product.setItemType(title);
        product.setStatus(1);
        product.setPriority(1);
        productService.insert(product);

        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"增加商品【"+title+"】",getUsernameFromSession(session).getId()));


        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/update")
    public JsonResult<Void> update(Integer cid,Long price,String title,Integer num,Integer cids,HttpServletRequest request,HttpSession session){

        Product product = new Product();
        product.setNum(num);
        product.setPrice(price);
        product.setId(cid);
        product.setCategoryId(cids);
        product.setTitle(title);
        product.setItemType(title);
        product.setStatus(1);
        product.setPriority(1);
        productService.update(product);


        userMapper.insertSellerLog(new SellerLog(new Date(),request.getRemoteAddr(),getUsernameFromSession(session).getUsername(),"更新商品【"+title+"】",getUsernameFromSession(session).getId()));


        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/userProductLog")
    public JsonResult<List<UsernameProductDateVO>> findUserProductLog(Integer cid, Integer flag) {
        if (flag==0){
            return new JsonResult<List<UsernameProductDateVO>>(OK,productMapper.findUserStopLogByCategoryId(cid));
        }

        return new JsonResult<List<UsernameProductDateVO>>(OK,productMapper.findOrderItemLogByCategoryId(cid));
    }

    @RequestMapping("/hotProduct")
    public JsonResult<List<Product>> hotProduct() {
        return new JsonResult<List<Product>>(OK,productService.findHotProduct());
    }

    @RequestMapping("/getException")
    public JsonResult<List<SalesExceptionVO>> getException() {
        return new JsonResult<List<SalesExceptionVO>>(OK,productMapper.findAllException());
    }

}
