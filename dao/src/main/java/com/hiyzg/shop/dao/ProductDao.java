package com.hiyzg.shop.dao;

import com.hiyzg.shop.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public interface ProductDao {
    Optional<Product> getByPid(String pid) throws SQLException;

    List<Product> selectHotList() throws SQLException;

    List<Product> selectNewList() throws SQLException;
}
