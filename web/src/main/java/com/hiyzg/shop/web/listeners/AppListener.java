package com.hiyzg.shop.web.listeners;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

/**
 * Created by Sam on 2019/3/12.
 */
@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 注册日期转换器
        ConvertUtils.register(new DateConverter(null), Date.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
