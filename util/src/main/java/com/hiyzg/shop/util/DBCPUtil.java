package com.hiyzg.shop.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Sam on 2019/3/11.
 */
public class DBCPUtil {
    private static final Properties properties = new Properties();
    private static DataSource DATA_SOURCE;
    private static ThreadLocal<Connection> conn = new ThreadLocal<>();

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

    public static Connection getConnection() {
        try {
            Connection connection = conn.get();
            if (connection == null) {
                connection = DATA_SOURCE.getConnection();
                conn.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void close() {
        try {
            Connection connection = conn.get();
            if (connection != null) {
                connection.close();
                conn.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
