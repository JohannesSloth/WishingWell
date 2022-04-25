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
                        System.getenv("spring.datasource.url"),
                        System.getenv("spring.datasource.username"),
                        System.getenv("spring.datasource.password"));
                System.out.println("SUCCESFULLY CONNECTED TO SERVER");
            } catch (SQLException e) {
                System.out.println("ERROR: " + e);
            }
        }
        return connection;
    }
}