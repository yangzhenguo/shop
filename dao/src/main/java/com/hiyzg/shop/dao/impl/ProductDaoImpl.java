package com.hiyzg.shop.dao.impl;

import com.hiyzg.shop.dao.ProductDao;
import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public class ProductDaoImpl implements ProductDao {
    private QueryRunner queryRunner = new QueryRunner(DBCPUtil.getDataSource());

    @Override
    public Optional<Product> getByPid(String pid) throws SQLException {
        Product product = this.queryRunner.query("select * from product where pid = ?", new BeanHandler<>(Product.class), pid);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> selectHotList() throws SQLException {
        return this.queryRunner.query("select * from product where is_hot = 1 order by pdate desc limit 9", new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> selectNewList() throws SQLException {
        return this.queryRunner.query("select * from product order by pdate desc limit 9", new BeanListHandler<>(Product.class));
    }
}
