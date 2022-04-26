package com.example.wishingwell.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection connection;

    public static Connection connectToSql() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        System.getenv("url"),
                        System.getenv("user"),
                        System.getenv("password"));
                System.out.println("SUCCESFULLY CONNECTED TO SERVER");
            } catch (SQLException e) {
                System.out.println("ERROR: " + e);
            }
        }
        return connection;
    }
}