package com.hiyzg.shop.service;

import com.hiyzg.shop.service.model.LoginRequest;
import com.hiyzg.shop.service.model.UserRequest;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Sam on 2019/3/11.
 */
public interface UserService {
    Map<String, Object> register(UserRequest userRequest) throws SQLException;

    Map<String,Object> login(LoginRequest loginRequest) throws SQLException;
}
