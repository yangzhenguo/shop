package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.OrderDao;
import com.hiyzg.shop.dao.util.BeanFactory;
import com.hiyzg.shop.model.Order;
import com.hiyzg.shop.model.OrderItem;
import com.hiyzg.shop.service.OrderService;
import com.hiyzg.shop.util.DBCPUtil;

import java.sql.SQLException;

/**
 * Created by Sam on 2019/3/11.
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = (OrderDao) BeanFactory.DAO_BEANS.get("OrderDao");;

    @Override
    public void add(Order order) throws SQLException {
        try {
            DBCPUtil.getConnection().setAutoCommit(false);
            this.orderDao.insert(order);
            for (OrderItem orderItem :
                    order.getOrderItems()) {
                this.orderDao.insert(orderItem);
            }
            DBCPUtil.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DBCPUtil.getConnection().rollback();
            DBCPUtil.close();
            throw e;
        }
    }
}
