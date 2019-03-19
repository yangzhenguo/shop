package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.service.UserService;
import com.hiyzg.shop.service.annotations.AutoSkip;
import com.hiyzg.shop.service.constants.CommonConstant;
import com.hiyzg.shop.service.model.LoginRequest;
import com.hiyzg.shop.service.model.UserRequest;
import com.hiyzg.shop.service.util.BeanFactory;
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

    private UserService userService = (UserService) BeanFactory.SERVICE_BEANS.get("UserService");

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
            success = "/WEB-INF/jsp/msg.jsp",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    public Map<String, Object> activeSubmit() throws InvocationTargetException, IllegalAccessException, SQLException {
        String code = this.getRequest().getParameter("code");
        return this.userService.active(code);
    }

    @AutoSkip(
            success = "redirect:/",
            failure = "/WEB-INF/jsp/login.jsp"
    )
    public Map<String, Object> loginSubmit() throws InvocationTargetException, IllegalAccessException, SQLException {
        LoginRequest loginRequest = new LoginRequest();
        BeanUtils.populate(loginRequest, this.getRequest().getParameterMap());
        Map<String, Object> result = this.userService.login(loginRequest);
        if ((Boolean) result.get(CommonConstant.SUCCESS)) {
            this.getRequest().getSession().setAttribute("user", result.get("user"));
        }
        return result;
    }
}
