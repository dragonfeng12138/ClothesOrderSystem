package com.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Dragon
 * @create 2021-06-26 20:22
 */
public class MyUtils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
