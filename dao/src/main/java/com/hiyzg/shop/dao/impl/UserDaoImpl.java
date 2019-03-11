package com.hiyzg.shop.dao.impl;

import com.hiyzg.shop.dao.UserDao;
import com.hiyzg.shop.model.User;
import com.hiyzg.shop.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner = new QueryRunner(DBCPUtil.getDataSource());

    @Override
    public Optional<User> getByUsername(String username) throws SQLException {
        User user = this.queryRunner.query("select * from user where username = ?", new BeanHandler<User>(User.class), username);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getByUID(String uid) throws SQLException {
        User user = this.queryRunner.query("select * from user where uid = ?", new BeanHandler<User>(User.class), uid);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> insert(User user) throws SQLException {
        this.queryRunner.update(
                "insert into user (uid, username, password, name, email, telephone, birthday, sex, state, code) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUid(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getTelephone(),
                user.getBirthday(),
                user.getSex(),
                user.getState(),
                user.getCode()
        );
        return this.getByUID(user.getUid());
    }
}
