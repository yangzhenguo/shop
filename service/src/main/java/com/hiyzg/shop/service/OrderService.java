package com.hiyzg.shop.service;

import com.hiyzg.shop.model.Order;

import java.sql.SQLException;

/**
 * Created by Sam on 2019/3/14.
 */
public interface OrderService {
    void add(Order order) throws SQLException;
}
