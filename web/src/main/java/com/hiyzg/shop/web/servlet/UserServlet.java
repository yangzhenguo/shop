package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.annotations.AutoSkip;
import com.hiyzg.shop.service.impl.UserServiceImpl;
import com.hiyzg.shop.service.model.LoginRequest;
import com.hiyzg.shop.service.model.UserRequest;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    public String login() {
        return "/WEB-INF/jsp/login.jsp";
    }

    public String register() {
        return "/WEB-INF/jsp/register.jsp";
    }


    @AutoSkip(
            success = "/WEB-INF/jsp/msg.jsp",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    public Map<String, Object> registerSubmit() throws InvocationTargetException, IllegalAccessException, SQLException {
        UserRequest userRequest = new UserRequest();
        BeanUtils.populate(userRequest, this.getRequest().getParameterMap());
        return this.userService.register(userRequest);
    }

    @AutoSkip(
            success = "redirect:/",
            failure = "/WEB-INF/jsp/login.jsp"
    )
    public Map<String, Object> loginSubmit() throws InvocationTargetException, IllegalAccessException, SQLException {
        LoginRequest loginRequest = new LoginRequest();
        BeanUtils.populate(loginRequest, this.getRequest().getParameterMap());
        return this.userService.login(loginRequest);
    }
}
