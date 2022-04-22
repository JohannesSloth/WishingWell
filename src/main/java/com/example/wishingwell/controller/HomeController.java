package com.example.wishingwell.controller;

import com.example.wishingwell.model.Wish;
import com.example.wishingwell.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    WishRepository repository;

    public HomeController(WishRepository wishRepository) {
        this.repository = wishRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("wishlist", repository.getall());
        return "index";
    }


    @GetMapping("/addwish")
    public String addwish() {
        return "addwish";
    }

    @PostMapping("/addwish")
    public String addwish(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("url") String url, @RequestParam("pictureurl")
            String pictureurl, @RequestParam("description") String description) {
        Wish wish = new Wish(name);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        repository.addWish(wish);
        return "/";
    }

    @GetMapping("/update/{id}")
    public String updateWish(@PathVariable("id") int id, Model model) {
        model.addAttribute("wish", repository.findByID(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateWish(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("url") String url, @RequestParam("pictureurl") String pictureurl, @RequestParam("description") String description) {
        Wish wish = new Wish(name);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        repository.addWish(wish);
        return "redirect:/";
    }

    @GetMapping("/deletewish/{id}")
    public String deletewish(@PathVariable("id") int id) {
        repository.deleteWish(id);
        return "redirect:/";
    }



}

