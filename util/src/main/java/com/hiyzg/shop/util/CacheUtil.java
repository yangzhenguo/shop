package com.hiyzg.shop.util;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * Created by wangxiaohua on 2019/3/17.
 */
public class CacheUtil {
    public static final String CATEGORY = "category";
    public static final String PRODUCT = "product";

    public static CacheManager CACHE_MANAGER = CacheManagerBuilder
            .newCacheManagerBuilder()
            .withCache(CATEGORY,
                    CacheConfigurationBuilder
                            .newCacheConfigurationBuilder(
                                    String.class,
                                    Object.class,
                                    ResourcePoolsBuilder.heap(100)
                            )
            )
            .withCache(PRODUCT,
                    CacheConfigurationBuilder
                            .newCacheConfigurationBuilder(
                                    String.class,
                                    Object.class,
                                    ResourcePoolsBuilder.heap(100)
                            )
            )
            .build(true);
}
