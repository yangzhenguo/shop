package com.hiyzg.shop.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sam on 2019/3/11.
 */
public class DBCPUtilTest {
    @Test
    public void getDataSource() throws Exception {
        assertNotNull(DBCPUtil.getDataSource());
    }

}