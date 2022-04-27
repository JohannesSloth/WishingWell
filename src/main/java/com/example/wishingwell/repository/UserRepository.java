package com.example.wishingwell.repository;


import com.example.wishingwell.model.User;
import com.example.wishingwell.model.Wish;
import com.example.wishingwell.utility.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
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
        wishes.add(wish);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in showing userWishList");
    }

    return wishes;
  }

}
