package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.model.LoginRequest;
import com.hiyzg.shop.service.model.UserRequest;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Sam on 2019/3/13.
 */
public class UserServiceImplTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void register() throws Exception {
        UserRequest userRequest = new UserRequest();
        Map<String, Object> register = this.userService.register(userRequest);
        assertTrue((Boolean)register.getOrDefault("success", false));
    }

    @Test
    public void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest("abc", "abc", null);
        Map<String, Object> login = this.userService.login(loginRequest);
        assertTrue((Boolean)login.getOrDefault("success", false));
    }

}