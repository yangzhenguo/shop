package com.hiyzg.shop.web.tags;

import com.hiyzg.shop.model.Category;
import com.hiyzg.shop.service.CategoryService;
import com.hiyzg.shop.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 2019/3/14.
 */
public class CategoryTag extends SimpleTagSupport {
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext)this.getJspContext();
        List<Category> categories;
        String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
        try {
            categories = this.categoryService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            categories = new ArrayList<>();
        }
        JspWriter out = pageContext.getOut();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            String liTag = String.format(
                    "<li%s><a href=\"%s\">%s</a></li>",
                    i == 0 ? " class=\"active\"" : "",
                    contextPath + (category.getCurl() == null ? "" : category.getCurl()),
                    category.getCname()
            );
            out.write(liTag);
        }
    }


}
