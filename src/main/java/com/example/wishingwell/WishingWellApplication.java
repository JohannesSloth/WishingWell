package com.example.wishingwell;

import com.example.wishingwell.utility.ConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WishingWellApplication {

    public static void main(String[] args) {
        ConnectionManager.connectToSql();
        SpringApplication.run(WishingWellApplication.class, args);
    }

}
