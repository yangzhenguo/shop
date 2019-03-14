package com.hiyzg.shop.dao;

import com.hiyzg.shop.model.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public interface CategoryDao {
    Optional<Category> getByCname(String cname) throws SQLException;

    Optional<Category> getByCid(String cid) throws SQLException;

    List<Category> selectAll() throws SQLException;
}
