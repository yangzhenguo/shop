package com.hiyzg.shop.service.impl;

import com.hiyzg.shop.dao.UserDao;
import com.hiyzg.shop.dao.impl.UserDaoImpl;
import com.hiyzg.shop.model.User;
import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.model.UserRequest;
import com.hiyzg.shop.util.UUIDUtil;
import org.apache.commons.codec.digest.DigestUtils;

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
        userRequest.setPassword(DigestUtils.md5Hex(userRequest.getPassword()));
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
}
