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
        return "/addwish";
    }

    @PostMapping("/addwish")
    public String addWish(@RequestParam String name, @RequestParam double price, @RequestParam String url, @RequestParam
            String pictureurl, @RequestParam String description) {
        Wish wish = new Wish(name);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        repository.addWish(wish);
        return "redirect:/";
    }

    @GetMapping("/updatewish/{id}")
    public String updateWish(@PathVariable int id) {
        return "/updatewish";
    }

    @PostMapping("/updatewish")
    public String updateWish(@RequestParam String name, @RequestParam double price, @RequestParam String url, @RequestParam String pictureurl, @RequestParam String description) {
        Wish wish = new Wish(name);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        repository.addWish(wish);
        return "redirect:/";
    }

    @GetMapping("/deletewish/{id}")
    public String deleteWish(@PathVariable int id) {
        repository.deleteWish(id);
        return "redirect:/";
    }

}

