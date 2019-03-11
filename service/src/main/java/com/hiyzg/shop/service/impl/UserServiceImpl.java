package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.UserDao;
import com.hiyzg.shop.dao.impl.UserDaoImpl;
import com.hiyzg.shop.model.User;
import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.model.UserRequest;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<User> register(UserRequest userRequest) throws SQLException {
        Optional<User> existUserOptional = this.userDao.getByUsername(userRequest.getUsername());

        Optional<User> userOptional = this.userDao.insert(userRequest);
        return userOptional;
    }
}
