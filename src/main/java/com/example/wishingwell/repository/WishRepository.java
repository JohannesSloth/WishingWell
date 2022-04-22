package com.example.wishingwell.repository;

import com.example.wishingwell.model.Wish;
import com.example.wishingwell.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class WishRepository {

    public ArrayList<Wish> getall() {
        ArrayList<Wish> wishes = new ArrayList<>();
        String query = "SELECT * FROM wish";
        try {
            PreparedStatement ps = ConnectionManager.connectToSql().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String url = rs.getString("url");
                String pictureurl = rs.getString("pictureurl");
                String description = rs.getString("description");
                Wish wish = new Wish(name);
                wish.setPrice(price);
                wish.setUrl(url);
                wish.setPictureUrl(pictureurl);
                wish.setDescription(description);
                wishes.add(wish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error");
        }
        return wishes;
    }

    public void addWish(Wish wish) {
        String query = "INSERT INTO wish(name, price, url, pictureurl, description) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setDouble(2, wish.getPrice());
            preparedStatement.setString(3, wish.getUrl());
            preparedStatement.setString(4, wish.getPictureUrl());
            preparedStatement.setString(5, wish.getDescription());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
    }
}
