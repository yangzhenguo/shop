package com.hiyzg.shop.dao;

import com.hiyzg.shop.model.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public interface UserDao {
    Optional<User> getByUsername(String username) throws SQLException;

    Optional<User> getByUID(String uid) throws SQLException;

    Optional<User> insert(User user) throws SQLException;
}
