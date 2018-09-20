package com.miaoshasha.common.code;

import java.sql.*;

/**
 * 数据库连接
 *
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-20
 * Time：18:11
 * -----------------------------
 */
public class DBConnection {

    private final static String jdbcUrl = "jdbc:mysql://59.110.159.115:3306/mssdb?characterEncoding=utf8";

    private final static String driverClassName = "com.mysql.jdbc.Driver";

    private final static String username = "root";

    private final static String password = "1qaz!QAZ";


    /**
     * 获取jdbc连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }


    public static void close(Connection connection) {
        close(null,null,connection);
    }

    public static void close(ResultSet resultSet, Connection connection) {
        close(resultSet,null,connection);
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
