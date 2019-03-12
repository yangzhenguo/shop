package com.hiyzg.shop.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sam on 2019/3/12.
 */
public class MailUtilTest {
    @Test
    public void sendSimpleMail() throws Exception {
        boolean success = MailUtil.sendSimpleMail("sam@micous.com", "ceshi", "haha");
        assertTrue(success);
    }

}