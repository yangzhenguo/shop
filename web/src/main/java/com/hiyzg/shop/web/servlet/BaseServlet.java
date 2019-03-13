package com.hiyzg.shop.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hiyzg.shop.service.annotations.AutoSkip;

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

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
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
                } else if (action.getReturnType() == Map.class) {
                    if (action.isAnnotationPresent(AutoSkip.class)) {
                        AutoSkip autoSkipAnnoation = action.getAnnotation(AutoSkip.class);
                        String dataName = autoSkipAnnoation.value();
                        String successPage = autoSkipAnnoation.success();
                        String failurePage = autoSkipAnnoation.failure();
                        Map map = (Map)result;
                        if (!map.containsKey(dataName)) {
                            req.setAttribute(dataName, result);
//                            throw new RuntimeException(String.format("AutoSkip的Map返回值必须包含%s数据", dataName));
                        }
                        Boolean realSuccess = (Boolean)map.getOrDefault("success", false);
                        if (realSuccess) {
                            if (successPage.startsWith("redirect:")) {
                                resp.sendRedirect(successPage.substring(9));
                            } else {
                                req.getRequestDispatcher(successPage).forward(req, resp);
                            }
                        } else {
                            if (failurePage.startsWith("redirect:")) {
                                resp.sendRedirect(failurePage.substring(9));
                            } else {
                                req.getRequestDispatcher(failurePage).forward(req, resp);
                            }
                        }
                    } else {
                        throw new RuntimeException("AutoSkip目前只支持Map返回值");
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
        }
    }

    protected Object index() {
        return null;
    }

    protected HttpServletRequest getRequest() {
        return this.request;
    }

    protected HttpServletResponse getResponse() {
        return this.response;
    }
}
