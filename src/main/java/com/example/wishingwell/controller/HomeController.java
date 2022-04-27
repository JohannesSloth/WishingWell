package com.example.wishingwell.controller;

import com.example.wishingwell.model.User;
import com.example.wishingwell.model.Wish;
import com.example.wishingwell.repository.UserRepository;
import com.example.wishingwell.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    UserRepository userRepository = new UserRepository();
    WishRepository repository;

    public HomeController(WishRepository wishRepository) {
        this.repository = wishRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userlist", userRepository.showUsers());
        return "index";
    }

    @GetMapping("/adduser")
    public String adduser() {
        return "adduser";
    }

    @PostMapping("/adduser")
    public String adduser(@RequestParam("name") String name){
        User user = new User(name);
        userRepository.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/addwish")
    public String addwish() {
        return "addwish";
    }

    @PostMapping("/addwish/{userId}")
    public String addwish(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("url") String url, @RequestParam("pictureurl")
            String pictureurl, @RequestParam("description") String description, @PathVariable ("userId") int id) {
        Wish wish = new Wish(name);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        wish.setUserId(id);
        repository.addWish(wish);
        return "redirect:/";
    }

    @GetMapping("/wishlist/{id}")
    public String wishlist(@PathVariable("id") int id, Model model){
    model.addAttribute("showWishlist", userRepository.showWishlist(id));
    model.addAttribute("userId", id);
    return "wishlist";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteuser(@PathVariable("id") int id){
        userRepository.deleteUser(id);
        return "redirect:/";
    }


    @GetMapping("/update/{id}")
    public String updateWish(@PathVariable("id") int id, Model model) {
        model.addAttribute("wish", repository.findByID(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateWish(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("price") double price,
                             @RequestParam("url") String url, @RequestParam("pictureurl") String pictureurl,
                             @RequestParam("description") String description) {
        Wish wish = new Wish(name);
        wish.setId(id);
        wish.setPrice(price);
        wish.setUrl(url);
        wish.setPictureUrl(pictureurl);
        wish.setDescription(description);
        //Vi skal have en update wish metode i stedet for at lave en ny her.
        repository.updateWish(wish);
        return "redirect:/";
    }

    @GetMapping("/deletewish/{id}")
    public String deletewish(@PathVariable("id") int id) {
        repository.deleteWish(id);
        return "redirect:/";
    }



}

