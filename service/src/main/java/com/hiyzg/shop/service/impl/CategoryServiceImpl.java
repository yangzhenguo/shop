package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.CategoryDao;
import com.hiyzg.shop.dao.impl.CategoryDaoImpl;
import com.hiyzg.shop.model.Category;
import com.hiyzg.shop.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sam on 2019/3/11.
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() throws SQLException {
        return this.categoryDao.selectAll();
    }
}
