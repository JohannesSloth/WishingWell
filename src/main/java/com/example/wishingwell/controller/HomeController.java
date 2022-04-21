package com.example.wishingwell.controller;

import com.example.wishingwell.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

  WishRepository repository;

  @GetMapping("/")
  public String index(){
    return "index";
  }


  @GetMapping("/addwish")
  public String addwish(HttpSession httpSession){
    httpSession.setAttribute("wishlist",repository.getall());
    return "/addwish";
  }

  @PostMapping("/addwish")
    public String addWish(@RequestParam String name, @RequestParam double price, @RequestParam String url, @RequestParam
                          String pictureurl, @RequestParam String description){
      repository.addWish(new Wish (name,price,url,pictureurl,description));
      return "redirect:/";
    }
  }

}
