package com.example.wishingwell.repository;


import com.example.wishingwell.model.User;
import com.example.wishingwell.model.Wish;
import com.example.wishingwell.utility.ConnectionManager;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UserRepository {

  public void addUser(User user){
    String query = "INSERT INTO user(username) VALUES (?)";
    try {
      PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
      preparedStatement.setString(1,user.getUsername());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in user creation");
    }
  }

  public void deleteUser(int id) {
    String query = "DELETE FROM user WHERE user_id = ?";
    try {
      PreparedStatement ps = ConnectionManager.connectToSql().prepareStatement(query);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Error in user deletion");
    }
    String wishDeleteQuery = "DELETE FROM wish WHERE user_id = ?";
    try {
      PreparedStatement ps2 = ConnectionManager.connectToSql().prepareStatement(wishDeleteQuery);
      ps2.setInt(1, id);
      ps2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Error in deletion of wishes related to user");
    }
  }

  public ArrayList<User> showUsers(){
    ArrayList<User> users = new ArrayList<>();
    String query = "SELECT * FROM user";
    try {
      PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        String username = resultSet.getString("username");
        int user_id = resultSet.getInt("user_id");
        User user = new User(username);
        user.setUser_id(user_id);
        users.add(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in user creation");
    }
    return users;
  }

  public ArrayList<Wish> showWishlist(int user_id){
    String query = "SELECT name, price, url, pictureurl, description FROM wish WHERE user_id = ?";
    ArrayList<Wish> wishes = new ArrayList<>();
    try {
      PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
      preparedStatement.setInt(1,user_id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        int id = resultSet.getInt("wish_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        String url = resultSet.getString("url");
        String pictureurl = resultSet.getString("pictureurl");
        String description = resultSet.getString("description");
        Wish wish = new Wish(name);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        wish.setId(id);
        wishes.add(wish);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in showing userWishList");
    }

    return wishes;
  }

}
