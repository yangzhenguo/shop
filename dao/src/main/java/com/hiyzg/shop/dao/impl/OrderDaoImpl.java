package com.hiyzg.shop.dao.impl;

import com.hiyzg.shop.dao.OrderDao;
import com.hiyzg.shop.model.Order;
import com.hiyzg.shop.model.OrderItem;
import com.hiyzg.shop.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * Created by Sam on 2019/3/11.
 */
public class OrderDaoImpl implements OrderDao {
    private QueryRunner queryRunner = new QueryRunner(DBCPUtil.getDataSource());

    @Override
    public void insert(Order order) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.update(DBCPUtil.getConnection(),"insert into orders (oid, ordertime, total, state, uid) values (?, ?, ?, ?, ?)", order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), order.getUser().getUid());
    }

    @Override
    public void insert(OrderItem orderItem) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.update(DBCPUtil.getConnection(),"insert into orderitem (itemid, count, subtotal, pid, oid) values (?, ?, ?, ?, ?)", orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(), orderItem.getProduct().getPid(), orderItem.getOrder().getOid());
    }
}
