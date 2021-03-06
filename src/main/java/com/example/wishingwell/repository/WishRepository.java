package com.example.wishingwell.repository;

import com.example.wishingwell.model.Wish;
import com.example.wishingwell.utility.ConnectionManager;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class WishRepository {

    public void addWish(Wish wish) {
        String query = "INSERT INTO wish(name, price, url, pictureurl, description, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setDouble(2, wish.getPrice());
            preparedStatement.setString(3, wish.getUrl());
            preparedStatement.setString(4, wish.getPictureUrl());
            preparedStatement.setString(5, wish.getDescription());
            preparedStatement.setInt(6, wish.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
    }

    public void updateWish(Wish wish) {
        String query = "UPDATE wish SET name=?, price=?, url=?, pictureurl=?, description=? WHERE wish_id=?";
        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setDouble(2, wish.getPrice());
            preparedStatement.setString(3, wish.getUrl());
            preparedStatement.setString(4, wish.getPictureUrl());
            preparedStatement.setString(5, wish.getDescription());
            preparedStatement.setInt(6, wish.getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update wish. Contact customer service: " + e);
        }
    }

    public void deleteWish(int id) {
        String query = "DELETE FROM wish WHERE wish_id=?";
        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete wish.");
        }
    }

    public Wish findByID(int id){
        Wish wish = new Wish();
        String query = "SELECT wish_id, name, price, url, pictureurl, description FROM wish where wish_id = " + id;
        try {
            PreparedStatement prepareStatement = ConnectionManager.connectToSql().prepareStatement(query);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()){
                    int idd = resultSet.getInt("wish_id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String url = resultSet.getString("url");
                    String pictureurl = resultSet.getString("pictureurl");
                    String description = resultSet.getString("description");
                    wish = new Wish(name);
                    wish.setId(id);
                    wish.setPrice(price);
                    wish.setUrl(url);
                    wish.setPictureUrl(pictureurl);
                    wish.setDescription(description);
                }
            }
            catch (SQLException sqlException){
                System.out.println("Error in finding item");
                sqlException.printStackTrace();
            }

            return wish;
    }
}
