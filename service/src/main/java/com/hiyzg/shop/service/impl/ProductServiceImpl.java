package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.ProductDao;
import com.hiyzg.shop.dao.impl.ProductDaoImpl;
import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.ProductService;
import org.ehcache.Cache;

import java.sql.SQLException;
import java.util.List;

import static com.hiyzg.shop.util.CacheUtil.CACHE_MANAGER;
import static com.hiyzg.shop.util.CacheUtil.PRODUCT;

/**
 * Created by Sam on 2019/3/11.
 */
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCT_HOT_KEY = "list_hot";
    private static final String PRODUCT_NEW_KEY = "list_new";

    private static Cache<String, Object> categoryCache = CACHE_MANAGER.getCache(PRODUCT, String.class, Object.class);

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> listHot() throws SQLException {
        List<Product> products = (List<Product>)this.categoryCache.get(PRODUCT_HOT_KEY);
        if (products == null) {
            products = this.productDao.selectHotList();
            this.categoryCache.put(PRODUCT_HOT_KEY, products);
        }
        return products;
    }

    @Override
    public List<Product> listNew() throws SQLException {
        List<Product> products = (List<Product>)this.categoryCache.get(PRODUCT_NEW_KEY);
        if (products == null) {
            products = this.productDao.selectNewList();
            this.categoryCache.put(PRODUCT_NEW_KEY, products);
        }
        return products;
    }
}
