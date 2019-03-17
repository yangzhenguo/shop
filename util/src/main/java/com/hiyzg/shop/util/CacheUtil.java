package com.hiyzg.shop.util;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

/**
 * Created by wangxiaohua on 2019/3/17.
 */
public class CacheUtil {
    public static CacheManager CACHE_MANAGER = CacheManagerBuilder
            .newCacheManagerBuilder()
            .build(true);
}
