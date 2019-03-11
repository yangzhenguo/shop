package com.hiyzg.shop.util;

import java.util.UUID;

/**
 * Created by Sam on 2019/3/11.
 */
public class UUIDUtil {
    public static String getUUIDStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
