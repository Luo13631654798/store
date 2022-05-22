package com.lyc.service.impl;

import com.lyc.domain.Cart;
import com.lyc.domain.Product;
import com.lyc.domain.VO.CartVO;
import com.lyc.mapper.CartMapper;
import com.lyc.mapper.ProductMapper;
import com.lyc.service.CartService;
import com.lyc.service.ex.InsertException;
import com.lyc.service.ex.LowStocksException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount) {
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        if (cart==null){
            Product product = productMapper.findProductById(pid);
            Cart cart1 = new Cart();
            cart1.setPid(pid);
            cart1.setUid(uid);
            cart1.setPrice(product.getPrice());
            cart1.setNum(amount);
            int insert = cartMapper.insert(cart1);
            if (insert!=1){
                throw new InsertException("添加时产生异常");
            }
        }else {
            Cart cart1 = cartMapper.findByUidAndPid(uid, pid);
            int i = cartMapper.updateNumByCid(cart1.getCid(), cart1.getNum() + amount);
            if (i!=1){
                throw new InsertException("添加时产生异常");
            }

        }
    }

    @Override
    public List<CartVO> getByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }

    @Override
    public int updateNum(Integer cid, Integer add) {
        Cart cart = cartMapper.findByCid(cid);
        Integer pid = cart.getPid();
        Product product = productMapper.findProductById(pid);
        Integer num = product.getNum();

        if(add==1){
            if (num<=cart.getNum()){
                throw new LowStocksException("库存不足！");
            }
            return cartMapper.updateNumByCid(cid,cart.getNum()+1);
        }
        return cartMapper.updateNumByCid(cid,cart.getNum()-1);
    }

    @Override
    public List<CartVO> getVOByCid(Integer[] cids) {
        List<CartVO> cartVOList = cartMapper.findVOByCid(cids);
        return cartVOList;
    }

    @Override
    public int deleteByCid(Integer cid) {
        return cartMapper.deleteByCid(cid);
    }
}
