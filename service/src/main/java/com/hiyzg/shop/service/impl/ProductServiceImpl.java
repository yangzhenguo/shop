package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.ProductDao;
import com.hiyzg.shop.dao.util.BeanFactory;
import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.ProductService;
import org.ehcache.Cache;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.hiyzg.shop.util.CacheUtil.CACHE_MANAGER;
import static com.hiyzg.shop.util.CacheUtil.PRODUCT;

/**
 * Created by Sam on 2019/3/11.
 */
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCTS_HOT_KEY = "list_hot";
    private static final String PRODUCTS_NEW_KEY = "list_new";
    private static final String PRODUCT_KEY_PREFIX = "product_";

    private static Cache<String, Object> categoryCache = CACHE_MANAGER.getCache(PRODUCT, String.class, Object.class);

    private ProductDao productDao = (ProductDao) BeanFactory.DAO_BEANS.get("ProductDao");;

    @Override
    public List<Product> listHot() throws SQLException {
        List<Product> products = (List<Product>)this.categoryCache.get(PRODUCTS_HOT_KEY);
        if (products == null) {
            products = this.productDao.selectHotList();
            this.categoryCache.put(PRODUCTS_HOT_KEY, products);
        }
        return products;
    }

    @Override
    public List<Product> listNew() throws SQLException {
        List<Product> products = (List<Product>)this.categoryCache.get(PRODUCTS_NEW_KEY);
        if (products == null) {
            products = this.productDao.selectNewList();
            this.categoryCache.put(PRODUCTS_NEW_KEY, products);
        }
        return products;
    }

    @Override
    public Optional<Product> getByPid(String pid) throws SQLException {
        Product product = (Product)this.categoryCache.get(this.getProductKey(pid));
        if (product == null) {
            Optional<Product> productOptional = this.productDao.getByPid(pid);
            if (productOptional.isPresent()) {
                this.categoryCache.put(this.getProductKey(pid), productOptional.get());
                return productOptional;
            }
        }
        return Optional.ofNullable(product);
    }

    private String getProductKey(String pid) {
        return PRODUCT_KEY_PREFIX + pid;
    }
}
