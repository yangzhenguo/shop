package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.ProductDao;
import com.hiyzg.shop.dao.util.BeanFactory;
import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.CartService;
import com.hiyzg.shop.service.model.Cart;
import com.hiyzg.shop.service.model.CartItem;
import org.ehcache.Cache;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.hiyzg.shop.util.CacheUtil.CACHE_MANAGER;
import static com.hiyzg.shop.util.CacheUtil.PRODUCT;

/**
 * Created by Sam on 2019/3/11.
 */
public class CartServiceImpl implements CartService {
    private ProductDao productDao = (ProductDao) BeanFactory.DAO_BEANS.get("ProductDao");;

    @Override
    public void add(Cart cart, String pid, int count) throws SQLException {
        Optional<Product> productOptional = this.productDao.getByPid(pid);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            cart.add(new CartItem(product, count));
        }
    }
}
