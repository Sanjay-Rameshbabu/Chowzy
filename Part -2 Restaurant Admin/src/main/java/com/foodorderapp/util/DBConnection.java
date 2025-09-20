package com.foodorderapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodorderapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    private static Connection connection = null;

    private DBConnection() {}

    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
        	
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void rollbackConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
        }
    }
}

