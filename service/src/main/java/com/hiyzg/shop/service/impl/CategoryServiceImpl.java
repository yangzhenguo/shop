package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.CategoryDao;
import com.hiyzg.shop.dao.impl.CategoryDaoImpl;
import com.hiyzg.shop.model.Category;
import com.hiyzg.shop.service.CategoryService;
import org.ehcache.Cache;

import java.sql.SQLException;
import java.util.List;

import static com.hiyzg.shop.util.CacheUtil.CACHE_MANAGER;
import static com.hiyzg.shop.util.CacheUtil.CATEGORY;

/**
 * Created by Sam on 2019/3/11.
 */
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORY_KEY = "list";
    private static Cache<String, Object> categoryCache = CACHE_MANAGER.getCache(CATEGORY, String.class, Object.class);

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = (List<Category>)this.categoryCache.get(CATEGORY_KEY);
        if (categories == null) {
            categories = this.categoryDao.selectAll();
            this.categoryCache.put(CATEGORY_KEY, categories);
        }
        return categories;
    }
}
