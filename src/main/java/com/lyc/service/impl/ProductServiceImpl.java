package com.lyc.service.impl;

import com.lyc.domain.Product;
import com.lyc.domain.ProductCategory;
import com.lyc.domain.VO.ProductStopNumVO;
import com.lyc.domain.VO.UsernameProductDateVO;
import com.lyc.mapper.ProductMapper;
import com.lyc.service.ProductService;
import com.lyc.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductCategory> findProductCategoryBySellerid(Integer id) {
        List<ProductCategory> categoryList = productMapper.getProductCategoryBySellerId(id);
        return categoryList;
    }

    @Override
    public int deleteProductCategorySeller(Integer id) {
        int i = productMapper.deleteProductCategorySeller(id);
        return i;
    }

    @Override
    public List<String> findNullSellerProductCategory() {
        List<String> nullSellerProductCategory = productMapper.findNullSellerProductCategory();
        return nullSellerProductCategory;
    }

    @Override
    public int addProductCategorySeller(String pid, Integer sid) {
        int i = productMapper.addProductCategorySeller(pid, sid);
        return i;
    }

    @Override
    public List<Product> findProductByKeyWord(String keyWord, Integer pageSize, Integer offset) {
        return productMapper.findProductByKeyWord(keyWord,pageSize,offset);
    }

    @Override
    public Product findProductById(Integer id) {
        return productMapper.findProductById(id);
    }

    @Override
    public List<Product> findProductByCategoryId(int categoryId, int pageSize, int offset) {
        List<Product> productList = productMapper.findProductByCategoryId(categoryId, pageSize, offset);
        return productList;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }

    @Override
    public int deleteByPid(int pid) {
        return productMapper.deleteByPid(pid);
    }

    @Override
    public int insert(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int update(Product product) {
        return productMapper.update(product);
    }

    @Override
    public List<UsernameProductDateVO> findUserProductLog(Integer cid, Integer flag) {
        if (flag==0){
            return productMapper.findUserStopLogByCategoryId(cid);
        }

        return productMapper.findOrderItemLogByCategoryId(cid);
    }

    @Override
    public List<Product> findHotProduct() {
        List<Product> productList = new ArrayList<>();

        Set<Product> productSet = new HashSet<>();
        Map<Product,Double> hotScore = new HashMap<>();
        List<ProductStopNumVO> productStopNum = productMapper.findProductStopNum();
        List<ProductStopNumVO> productBoughtNum = productMapper.findProductBoughtNum();

        for (ProductStopNumVO productStopNumVO : productStopNum) {
            Product product = productMapper.findProductById(productStopNumVO.getPid());
            if (!productSet.contains(product)){
                productList.add(product);
            }
            hotScore.put(product,hotScore.getOrDefault(product,0.0)+productStopNumVO.getNum()*0.5);
        }

        for (ProductStopNumVO productBoughtNumVO : productBoughtNum) {
            Product product = productMapper.findProductById(productBoughtNumVO.getPid());
            if (!productSet.contains(product)){
                productList.add(product);
            }
            hotScore.put(product,hotScore.getOrDefault(product,0.0)+productBoughtNumVO.getNum()) ;
        }

        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (hotScore.get(o2)-hotScore.get(o1));
            }
        });
        return productList.subList(0,15);
    }




}
