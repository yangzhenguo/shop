package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.model.Order;
import com.hiyzg.shop.model.OrderItem;
import com.hiyzg.shop.model.User;
import com.hiyzg.shop.service.CartService;
import com.hiyzg.shop.service.OrderService;
import com.hiyzg.shop.service.annotations.AutoSkip;
import com.hiyzg.shop.service.constants.CommonConstant;
import com.hiyzg.shop.service.model.Cart;
import com.hiyzg.shop.service.model.CartItem;
import com.hiyzg.shop.service.util.BeanFactory;
import com.hiyzg.shop.util.UUIDUtil;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/order")
public class OrderServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService = (OrderService) BeanFactory.SERVICE_BEANS.get("OrderService");

    @Override
    public String index() throws SQLException {
        return "/WEB-INF/jsp/cart.jsp";
    }

    @AutoSkip(
            success = "/WEB-INF/jsp/order_info.jsp",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    public Map<String, Object> add() throws SQLException {
        Cart cart = (Cart)this.getRequest().getSession().getAttribute("cart");
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        if (cart == null) {
            throw new RuntimeException("没有购物车");
        }
        Order order = new Order();
        order.setUser(user);
        order.setOid(UUIDUtil.getUUIDStr());
        order.setState(0);
        order.setTotal(cart.getTotal());
        order.setOrderItems(cart.getItems().stream().map(item -> new OrderItem(UUIDUtil.getUUIDStr(), item.getCount(), item.getSubTotal(), item.getProduct(), order)).collect(Collectors.toList()));
        this.orderService.add(order);
        this.getRequest().getSession().removeAttribute("cart");
        Map<String, Object> result = new HashMap<>();
        result.put(CommonConstant.SUCCESS, true);
        return result;
    }
}
