package com.hiyzg.shop.web.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sam on 2019/3/11.
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final ThreadLocal<HttpServletRequest> HTTP_SERVLET_REQUEST_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> HTTP_SERVLET_RESPONSE_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HTTP_SERVLET_REQUEST_THREAD_LOCAL.set(req);
        HTTP_SERVLET_RESPONSE_THREAD_LOCAL.set(resp);
        try {
            Map<String, String[]> parameterMap = req.getParameterMap();

            String[] methods = parameterMap.getOrDefault("method", new String[]{"index"});
            String method = methods[0];

            Class<? extends BaseServlet> clazz = this.getClass();
            Method action = clazz.getMethod(method);
            if (action == null) {
                req.setAttribute("error", new RuntimeException("No such method"));
                req.getRequestDispatcher("/WEB-INF/errors/500.jsp").forward(req, resp);
            } else {
                Object result = action.invoke(this);
                if (result == null) {
                    throw new RuntimeException("method return null");
                }
                if (result.getClass() == String.class) {
                    String resultStr = result.toString();
                    if (resultStr.startsWith("redirect:")) {
                        resp.sendRedirect(resultStr.substring(9));
                    } else {
                        req.getRequestDispatcher(resultStr).forward(req, resp);
                    }
                } else {
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json");
                    resp.getWriter().write(JSON.toJSONString(result));
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            req.getRequestDispatcher("/WEB-INF/errors/400.jsp").forward(req, resp);
        } catch (IllegalAccessException | RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            req.getRequestDispatcher("/WEB-INF/errors/500.jsp").forward(req, resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getTargetException());
            req.getRequestDispatcher("/WEB-INF/errors/500.jsp").forward(req, resp);
        } finally {
            HTTP_SERVLET_REQUEST_THREAD_LOCAL.remove();
            HTTP_SERVLET_RESPONSE_THREAD_LOCAL.remove();
        }
    }

    protected Object index() {
        return null;
    }

    protected HttpServletRequest getRequest() {
        return HTTP_SERVLET_REQUEST_THREAD_LOCAL.get();
    }

    protected HttpServletResponse getResponse() {
        return HTTP_SERVLET_RESPONSE_THREAD_LOCAL.get();
    }
}
