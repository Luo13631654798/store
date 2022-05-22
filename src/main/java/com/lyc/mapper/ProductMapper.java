package com.lyc.mapper;

import com.lyc.domain.Product;
import com.lyc.domain.ProductCategory;
import com.lyc.domain.VO.ProductStopNumVO;
import com.lyc.domain.VO.SalesExceptionVO;
import com.lyc.domain.VO.UsernameProductDateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 通过销售员id查询负责的商品种类
     * @param id 销售员id
     * @return 商品类别列表
     */
    public List<ProductCategory> getProductCategoryBySellerId(Integer id);

    /**
     * 删除销售员对于某类商品的管理权
     * @param id 商品种类id
     * @return 受影响的行数
     */
    public int deleteProductCategorySeller(Integer id);

    /**
     * 查询没有管理员的商品类别
     * @return 管理员列表
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
    public List<Product> findProductByKeyWord(String keyWord,int pageSize,int offset);

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
     * 根据分类id查询类名
     * @param cid 类id
     */
    public String findCategoryName(Integer cid);

    /**
     * 查询某类商品被用户浏览的记录
     * @param cid 商品类别id
     * @return
     */
    public List<UsernameProductDateVO> findUserStopLogByCategoryId(Integer cid);



    /**
     * 查询某类商品被用户g购买的记录
     * @param cid 商品类别id
     * @return
     */
    public List<UsernameProductDateVO> findOrderItemLogByCategoryId(Integer cid);

    /**
     * 查询所有商品id
     * @return 商品id列表
     */
    public List<Integer> findAllId();

    /**
     * 查询用户停留过的商品列表
     * @param uid
     * @return
     */
    public List<Integer> findPidByUid(int uid);

    /**
     * 更新某商品分数
     * @param pid
     */
    public void updateProductScore(Integer pid,Double num);

    /**
     * 查找热门商品id
     * @return
     */
    public List<Integer> findHotProduct();

    /**
     * 插入销售异常
     * @param uid
     * @param pid
     * @param exception
     * @param time
     */
    public void insertExceptionLog(Integer uid, Integer pid, String exception, Date time);

    /**
     * 查询所有销售异常
     * @return
     */
    public List<SalesExceptionVO> findAllException();

    /**
     * 查询商品7天内被用户浏览的次数
     * @return
     */
    public List<ProductStopNumVO> findProductStopNum();

    /**
     * 查询商品7天内被用户购买的数量
     * @return
     */
    public List<ProductStopNumVO> findProductBoughtNum();
}
