package com.hiyzg.shop.web.servlet;

import com.hiyzg.shop.service.CartService;
import com.hiyzg.shop.service.annotations.AutoSkip;
import com.hiyzg.shop.service.constants.CommonConstant;
import com.hiyzg.shop.service.model.Cart;
import com.hiyzg.shop.service.util.BeanFactory;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sam on 2019/3/8.
 */
@WebServlet("/cart")
public class CartServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private CartService cartService = (CartService) BeanFactory.SERVICE_BEANS.get("CartService");

    @Override
    public String index() throws SQLException {
        return "/WEB-INF/jsp/cart.jsp";
    }

    @AutoSkip(
            success = "redirect:/cart",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    public Map<String, Object> add() throws SQLException {
        String pid = this.getRequest().getParameter("pid");
        String count = this.getRequest().getParameter("count");
        Cart cart = (Cart)this.getRequest().getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            this.getRequest().getSession().setAttribute("cart", cart);
        }
        this.cartService.add(cart, pid, Integer.parseInt(count));
        Map<String, Object> result = new HashMap<>();
        result.put(CommonConstant.SUCCESS, true);
        return result;
    }

    @AutoSkip(
            success = "redirect:/cart",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    public Map<String, Object> delete() {
        String pid = this.getRequest().getParameter("pid");
        Cart cart = (Cart)this.getRequest().getSession().getAttribute("cart");
        Map<String, Object> result = new HashMap<>();
        boolean success = false;
        if (cart != null) {
            success = cart.delete(pid);
        }
        result.put(CommonConstant.SUCCESS, success);
        result.put(CommonConstant.MESSAGE, success ? "删除成功" : "删除失败");
        return result;
    }

    @AutoSkip(
            success = "redirect:/cart",
            failure = "/WEB-INF/jsp/msg.jsp"
    )
    public Map<String, Object> clear() {
        Cart cart = (Cart)this.getRequest().getSession().getAttribute("cart");
        Map<String, Object> result = new HashMap<>();
        if (cart != null) {
            cart.clear();
        }
        result.put(CommonConstant.SUCCESS, true);
        return result;
    }
}
