package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.model.User;
import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.model.UserRequest;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    public String register() {
        return "/WEB-INF/jsp/register.jsp";
    }

    public String registSubmit() throws InvocationTargetException, IllegalAccessException {
        UserRequest userRequest = new UserRequest();
        BeanUtils.populate(userRequest, this.getRequest().getParameterMap());
        Optional<User> userOptional = this.userService.register(userRequest);
    }
}
