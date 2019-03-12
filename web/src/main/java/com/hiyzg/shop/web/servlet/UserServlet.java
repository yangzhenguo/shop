package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.impl.UserServiceImpl;
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

    public String register() {
        return "/WEB-INF/jsp/register.jsp";
    }

    public String registerSubmit() throws InvocationTargetException, IllegalAccessException, SQLException {
        UserRequest userRequest = new UserRequest();
        BeanUtils.populate(userRequest, this.getRequest().getParameterMap());
        Map<String, Object> result = this.userService.register(userRequest);
        if ((Boolean)result.get("success")) {
            this.getRequest().setAttribute("result", result);
            return "/WEB-INF/jsp/msg.jsp";
        } else {
            this.getRequest().setAttribute("result", result);
            return "/WEB-INF/jsp/msg.jsp";
        }
    }
}
