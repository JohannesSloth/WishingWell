package com.example.wishingwell.model;

import java.util.ArrayList;

public class WishList {
    private ArrayList<Wish> wishList = new ArrayList();

    public WishList(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }

    public ArrayList<Wish> getWishList() {
        return wishList;
    }

    public void addWish(Wish wish) {
        wishList.add(wish);
    }
}
