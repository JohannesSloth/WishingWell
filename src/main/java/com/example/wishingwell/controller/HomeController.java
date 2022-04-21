package com.example.wishingwell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @GetMapping("/createwishlist")
  public String createwishlist() {
    return "createwishlist";
  }


}
