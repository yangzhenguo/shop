package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.ProductService;
import com.hiyzg.shop.service.annotations.AutoSkip;
import com.hiyzg.shop.service.constants.CommonConstant;
import com.hiyzg.shop.service.impl.ProductServiceImpl;
import com.hiyzg.shop.service.util.BeanFactory;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/index")
public class HomeServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService = (ProductService) BeanFactory.SERVICE_BEANS.get("ProductService");

    @AutoSkip(
            success = "/WEB-INF/jsp/index.jsp",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    @Override
    public Map<String, Object> index() throws SQLException {
        Map<String, Object> result = new HashMap<>();
        List<Product> hotProducts = this.productService.listHot();
        List<Product> newProducts = this.productService.listNew();
        this.getRequest().setAttribute("hotProducts", hotProducts);
        this.getRequest().setAttribute("newProducts", newProducts);
        result.put("hotProducts", hotProducts);
        result.put("newProducts", newProducts);
        result.put(CommonConstant.SUCCESS, true);
        return result;
    }
}
