package com.hiyzg.shop.dao.impl;

import com.hiyzg.shop.dao.UserDao;
import com.hiyzg.shop.model.User;
import com.hiyzg.shop.util.UUIDUtil;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 * Created by Sam on 2019/3/11.
 */
public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void getByUsername() throws Exception {
        Optional<User> userOptional = this.userDao.getByUsername("aaa");
        assertTrue(userOptional.isPresent());
    }

    @Test
    public void insert() throws Exception {
        User user = new User();
        user.setUid(UUIDUtil.getUUIDStr());
        Optional<User> userOptional = this.userDao.insert(user);
        assertTrue(userOptional.isPresent());
    }

}