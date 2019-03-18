package com.hiyzg.shop.service.util;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Sam on 2019/3/18.
 */
public class BeanFactoryTest {
    @Test
    public void test() {
        Map<String, Object> serviceBeans = BeanFactory.SERVICE_BEANS;
        System.out.println(serviceBeans);
    }
}