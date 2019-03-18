package com.hiyzg.shop.service;

import com.hiyzg.shop.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/14.
 */
public interface ProductService {
    List<Product> listHot() throws SQLException;

    List<Product> listNew() throws SQLException;

    Optional<Product> getByPid(String pid) throws SQLException;
}
