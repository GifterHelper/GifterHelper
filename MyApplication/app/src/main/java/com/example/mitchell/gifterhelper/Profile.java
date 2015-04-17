package com.example.mitchell.gifterhelper;

import java.util.List;

/**
 * Created by Ashe on 4/17/2015.
 */
public class Profile {
    protected String name;
    protected Birthday birthday;
    protected int imageRes;
    protected List<Item> wishlist;
    protected List<Item> history;

    public Profile(){
        name = null;
        birthday = null;
    }
    public Profile(String name) {
        this.name = name;
    }

    public Profile(String name, Birthday birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Profile(String name, Birthday birthday, int imageRes) {
        this.imageRes = imageRes;
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public List<Item> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Item> wishlist) {
        this.wishlist = wishlist;
    }

    public List<Item> getHistory() {
        return history;
    }

    public void setHistory(List<Item> history) {
        this.history = history;
    }
}
