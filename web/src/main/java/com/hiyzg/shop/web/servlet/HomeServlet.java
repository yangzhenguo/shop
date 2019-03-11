package com.hiyzg.shop.web.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/index")
public class HomeServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public String index() {
        return "/WEB-INF/jsp/index.jsp";
    }
}
