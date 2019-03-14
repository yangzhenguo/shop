package com.hiyzg.shop.service;

import com.hiyzg.shop.model.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sam on 2019/3/14.
 */
public interface CategoryService {
    List<Category> findAll() throws SQLException;
}
