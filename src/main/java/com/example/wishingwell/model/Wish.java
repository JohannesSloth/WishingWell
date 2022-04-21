package com.example.wishingwell.model;

public class Wish {

    private String name;
    private double price;
    private String url;
    private String pictureUrl;
    private String description;


    public Wish(String name, double price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public Wish(String name, double price, String url, String pictureUrl) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}

