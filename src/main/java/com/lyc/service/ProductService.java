package com.lyc.service;

import com.lyc.domain.Product;
import com.lyc.domain.ProductCategory;
import com.lyc.domain.VO.UsernameProductDateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PrimitiveIterator;

public interface ProductService {
    /**
     * 通过销售员id查询其所负责的商品类
     * @param id
     * @return
     */
    public List<ProductCategory> findProductCategoryBySellerid(Integer id);
    /**
     * 删除销售员对于某类商品的管理权
     * @param id 商品种类id
     * @return 受影响的行数
     */
    public int deleteProductCategorySeller(Integer id);
    /**
     * 查询没有管理员的商品类别
     * @return 商品类别名列表
     */
    public List<String> findNullSellerProductCategory();

    /**
     * 添加销售员对于某类商品的管理权
     * @param pid 商品类别名
     * @param sid 销售员id
     * @return 受影响行数
     */
    public int addProductCategorySeller(String  pid,Integer sid);

    /**
     * 根据关键字查询商品并分页
     * @param keyWord 关键字
     * @param pageSize 页面大小
     * @param offset 偏移量（起始位置）
     * @return 商品列表
     */
    public List<Product> findProductByKeyWord(String keyWord,Integer pageSize,Integer offset);
    /**
     * 通过id查询商品
     * @param id id号
     * @return 商品
     */
    public Product findProductById(Integer id);
    /**
     * 根据商品类id查询商品并分页
     * @param categoryId 商品类id
     * @param pageSize 页面大小
     * @param offset 偏移量（起始位置）
     * @return 商品列表
     */
    public List<Product> findProductByCategoryId(int categoryId,int pageSize,int offset);

    /**
     * 根据商品类id查询商品不进行分页
     * @param categoryId 商品类id
     * @return 商品列表
     */
    public List<Product> findByCategoryId(int categoryId);

    /**
     * 根据商品id删除对应商品
     * @param pid 商品id
     * @return 受影响的行数
     */
    public int deleteByPid(int pid);

    /**
     * 插入商品
     * @param product 商品
     * @return 受影响行数
     */
    public int insert(Product product);

    /**
     * 更新商品
     * @param product 商品
     * @return 受影响行数
     */
    public int update(Product product);

    /**
     * 查询用户浏览/购买商品的日志
     * @param cid 商品类别id
     * @param flag 0代表浏览，1代表购买
     * @return 值对象列表
     */
    public List<UsernameProductDateVO> findUserProductLog(Integer cid,Integer flag);


    /**
     * 查找热门商品
     * @return
     */
    public List<Product> findHotProduct();
}
