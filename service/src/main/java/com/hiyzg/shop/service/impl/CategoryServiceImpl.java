package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.CategoryDao;
import com.hiyzg.shop.dao.impl.CategoryDaoImpl;
import com.hiyzg.shop.model.Category;
import com.hiyzg.shop.service.CategoryService;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.sql.SQLException;
import java.util.List;

import static com.hiyzg.shop.util.CacheUtil.CACHE_MANAGER;

/**
 * Created by Sam on 2019/3/11.
 */
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORY_KEY = "list";
    private static Cache<String, Object> categoryCache = CACHE_MANAGER.createCache("category",
            CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(
                            String.class,
                            Object.class,
                            ResourcePoolsBuilder.heap(100)
                    )
    );

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = (List<Category>)this.categoryCache.get("list");
        if (categories == null) {
            categories = this.categoryDao.selectAll();
            this.categoryCache.put(CATEGORY_KEY, categories);
        }
        return categories;
    }
}
