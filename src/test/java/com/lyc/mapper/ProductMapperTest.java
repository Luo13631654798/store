package com.lyc.mapper;


import com.lyc.domain.Product;
import com.lyc.domain.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;
    @Test
    void getProductCategoryBySellerId() {
        List<ProductCategory> category = productMapper.getProductCategoryBySellerId(5);
        System.out.println(category);
    }
    @Test
    void deleteProductCategorySeller(){
        int i = productMapper.deleteProductCategorySeller(2);
        System.out.println(i);
    }
    @Test
    void findNullSellerProductCategory(){
        List<String> nullSellerProductCategory = productMapper.findNullSellerProductCategory();
        System.out.println(nullSellerProductCategory);
    }

    @Test
    void addProductCategorySeller(){
        int i = productMapper.addProductCategorySeller("商品音像",24);
        System.out.println(i);
    }
    @Test
     void findProductByKeyWord(){
        System.out.println(productMapper.findProductByKeyWord("戴尔",12,12));
    };
    @Test
     void findProductById(){
        System.out.println(productMapper.findProductById(10000007));

    }
    @Test
    void findProductByCategoryId(){
        System.out.println(productMapper.findProductByCategoryId(3,12,1));
    }

    @Test
    void deleteByPid() {
        productMapper.deleteByPid(10000001);
    }

    @Test
    void findUserStopLogByCategoryId() {
        System.out.println(productMapper.findUserStopLogByCategoryId(6));
    }

    @Test
    void findOrderItemLogByCategoryId() {
        System.out.println(productMapper.findOrderItemLogByCategoryId(3));
    }

    @Test
    public void findAllId() {
        System.out.println(productMapper.findAllId());
    }

    @Test
    public void findPidByUid() {
        System.out.println(productMapper.findPidByUid(14));
    }

    @Test
    void updateProductScore(){
        productMapper.updateProductScore(10000002,0.5);
    }

    @Test
    void findHotProduct(){
        System.out.println(productMapper.findHotProduct());
    }

    @Test
    void insertExceptionLog(){
        productMapper.insertExceptionLog(14,100000001,"异常",new Date());
    }

    @Test
    void findAllException(){
        System.out.println(productMapper.findAllException());
    }

    @Test
    void findProductStopNum(){
        System.out.println(productMapper.findProductStopNum());
    }

    @Test
    void findProductBoughtNum(){
        System.out.println(productMapper.findProductBoughtNum());
    }
}