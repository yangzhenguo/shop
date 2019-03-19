package com.hiyzg.shop.service;

import com.hiyzg.shop.service.model.Cart;

import java.sql.SQLException;

/**
 * Created by Sam on 2019/3/14.
 */
public interface CartService {
    void add(Cart cart, String pid, int count) throws SQLException;
}
