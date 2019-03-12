package com.hiyzg.shop.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by Sam on 2019/3/12.
 */
public class MD5UtilTest {
    @Test
    public void md5() throws Exception {
        System.out.println(DigestUtils.md5Hex("hello"));
    }

}