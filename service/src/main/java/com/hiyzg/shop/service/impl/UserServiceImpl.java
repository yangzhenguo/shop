package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.UserDao;
import com.hiyzg.shop.dao.impl.UserDaoImpl;
import com.hiyzg.shop.model.User;
import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.constants.CommonConstant;
import com.hiyzg.shop.service.constants.UserConstant;
import com.hiyzg.shop.service.model.LoginRequest;
import com.hiyzg.shop.service.model.UserRequest;
import com.hiyzg.shop.util.UUIDUtil;
import lombok.NonNull;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/11.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Map<String, Object> register(UserRequest userRequest) throws SQLException {
        HashMap<String, Object> result = new HashMap<>();
        Optional<User> existUserOptional = this.userDao.getByUsername(userRequest.getUsername());
        if (existUserOptional.isPresent()) {
            result.put("success", false);
            result.put("message", "用户已经存在, 请登录");
            return result;
        }
        userRequest.setUid(UUIDUtil.getUUIDStr());  // 这测试设置uuid为主键
        userRequest.setState(0);    // 未激活, 无法登录
        userRequest.setCode(UUIDUtil.getUUIDStr());
        if (StringUtils.isNotEmpty(userRequest.getPassword())) {
            userRequest.setPassword(DigestUtils.md5Hex(userRequest.getPassword()));
        }
        Optional<User> userOptional = this.userDao.insert(userRequest);
        if (userOptional.isPresent()) {
            result.put("success", true);
            result.put("message", "用户注册成功");
            result.put("data", userOptional.get());
        } else {
            result.put("success", false);
            result.put("message", "用户注册失败，请稍后重试");
        }
        return result;
    }

    @Override
    public Map<String, Object> login(LoginRequest loginRequest) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        Optional<User> userOptional = this.userDao.getByUsername(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 用户没有激活
            if (user.getState() != UserConstant.USER_IS_ACTIVE) {
                result.put("success", false);
                result.put("message", "用户未激活，请先激活");
                return result;
            } else if (user.getPassword().equals(DigestUtils.md5Hex(loginRequest.getPassword()))) {
                result.put("success", true);
                result.put("message", "登录成功");
                return result;
            }
        }
        result.put("success", false);
        result.put("message", "用户名或密码错误");
        return result;
    }

    @Override
    public Map<String, Object> active(String code) throws SQLException {
        if (StringUtils.isBlank(code)) {
            throw new RuntimeException("激活码不能为空");
        }
        Map<String, Object> result = new HashMap<>();
        Optional<User> codeOptional = this.userDao.getByCode(code);
        if (codeOptional.isPresent()) {
            User user = codeOptional.get();
            if (user.getState() == UserConstant.USER_IS_ACTIVE) {
                result.put(CommonConstant.SUCCESS, false);
                result.put(CommonConstant.MESSAGE, "用户已经激活成功，请直接登录");
            } else {
                boolean success = this.userDao.updateStateByCode(code, UserConstant.USER_IS_ACTIVE);
                result.put(CommonConstant.SUCCESS, success);
                result.put(CommonConstant.MESSAGE, success ? "激活成功" : "激活失败");
            }
        } else {
            result.put(CommonConstant.SUCCESS, false);
            result.put(CommonConstant.MESSAGE, "激活码错误，请联系管理员");
        }
        return result;
    }
}
