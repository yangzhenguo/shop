package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.ProductService;
import com.hiyzg.shop.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/index")
public class HomeServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductServiceImpl();

    @Override
    public String index() throws SQLException {
        List<Product> hotProducts = this.productService.listHot();
        List<Product> newProducts = this.productService.listNew();
        this.getRequest().setAttribute("hotProducts", hotProducts);
        this.getRequest().setAttribute("newProducts", newProducts);
        return "/WEB-INF/jsp/index.jsp";
    }
}
