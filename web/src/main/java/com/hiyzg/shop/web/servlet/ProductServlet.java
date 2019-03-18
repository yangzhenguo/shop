package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.model.Product;
import com.hiyzg.shop.service.ProductService;
import com.hiyzg.shop.service.annotations.AutoSkip;
import com.hiyzg.shop.service.constants.CommonConstant;
import com.hiyzg.shop.service.impl.ProductServiceImpl;
import com.hiyzg.shop.service.util.BeanFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/product")
public class ProductServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService = (ProductService) BeanFactory.SERVICE_BEANS.get("ProductService");


    @AutoSkip(
            success = "/WEB-INF/jsp/product_info.jsp",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    @Override
    public Map<String, Object> index() throws SQLException {
        String pid = this.getRequest().getParameter("pid");
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isBlank(pid)) {
            throw new RuntimeException("产品不存在");
        }
        Optional<Product> productOptional = this.productService.getByPid(pid);
        if (productOptional.isPresent()) {
            result.put(CommonConstant.SUCCESS, true);
            result.put("product", productOptional.get());
            return result;
        } else {
            throw new RuntimeException("产品不存在");
        }
    }
}
