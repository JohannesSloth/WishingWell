package com.example.wishingwell.model;

public class User {

  private String username;
  private int user_id;

  public User(String username){
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  public int getUser_id() {
    return user_id;
  }
}