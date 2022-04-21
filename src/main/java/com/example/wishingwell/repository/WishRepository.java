package com.example.wishingwell.repository;

import com.example.wishingwell.model.Wish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class WishRepository {

  public ArrayList<Wish> getall(){
    ArrayList<Wish> wishes = new ArrayList<>();

    return wishes;
  }

}
