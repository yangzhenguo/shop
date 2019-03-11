package com.hiyzg.shop.service;

import com.hiyzg.shop.model.User;
import com.hiyzg.shop.service.model.UserRequest;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public interface UserService {
    Optional<User> register(UserRequest userRequest) throws SQLException;
}
