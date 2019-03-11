package com.hiyzg.shop.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Sam on 2019/3/11.
 */
public class DBCPUtil {
    private static final Properties properties = new Properties();
    private static DataSource DATA_SOURCE;

    static {
        try {
            properties.load(DBCPUtil.class.getResourceAsStream("/dbcp.properties"));
            DATA_SOURCE = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }
}
