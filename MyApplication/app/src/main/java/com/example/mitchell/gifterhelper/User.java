package com.example.mitchell.gifterhelper;

import java.util.Date;
import java.util.List;

/**
 * Created by Ethan on 4/16/2015.
 */
public class User extends Profile{

    public User(String name) {
        super(name);
    }

    public User(String name, Birthday birthday) {
        super(name, birthday);
    }

    public void addItem(Item item) {
        wishlist.add(item);
    }

    public void removeItem(Item item) {
        this.wishlist.add(item);
    }

    public void moveToHistory(Item item) {
        this.history.add(item);
    }
}

