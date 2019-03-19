package com.hiyzg.shop.dao;

import com.hiyzg.shop.model.Order;
import com.hiyzg.shop.model.OrderItem;

import java.sql.SQLException;

/**
 * Created by Sam on 2019/3/11.
 */
public interface OrderDao {
    void insert(Order order) throws SQLException;

    void insert(OrderItem orderItem) throws SQLException;
}
