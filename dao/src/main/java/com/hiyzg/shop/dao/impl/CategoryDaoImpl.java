package com.hiyzg.shop.dao.impl;

import com.hiyzg.shop.dao.CategoryDao;
import com.hiyzg.shop.model.Category;
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
public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner queryRunner = new QueryRunner(DBCPUtil.getDataSource());

    @Override
    public Optional<Category> getByCname(String cname) throws SQLException {
        Category category = this.queryRunner.query("select * from category where cname = ?", new BeanHandler<Category>(Category.class), cname);
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> getByCid(String cid) throws SQLException {
        Category category = this.queryRunner.query("select * from category where cid = ?", new BeanHandler<Category>(Category.class), cid);
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> selectAll() throws SQLException {
        return this.queryRunner.query("select * from category", new BeanListHandler<Category>(Category.class));
    }
}
