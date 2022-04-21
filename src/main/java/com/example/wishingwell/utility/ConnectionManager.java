package com.example.wishingwell.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static Connection connection = null;

    public static Connection connectToSql() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        System.getenv("spring.datasource.url"),
                        System.getenv("spring.datasource.username"),
                        System.getenv("spring.datasource.password"));
                System.out.println("SUCCESFULLY CONNECTED TO SERVER");
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
            }
        }
        return connection;
    }
}