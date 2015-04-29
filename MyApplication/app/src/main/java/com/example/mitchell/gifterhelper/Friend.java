package com.example.mitchell.gifterhelper;

import java.util.Date;
import java.util.List;

/**
 * Created by Ethan on 4/16/2015.
 */
public class Friend implements Profile{
    protected String name;
    protected String birthday;
    protected int imageRes;
    protected List<Item> wishlist;
    protected List<Item> history;
    protected String id;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Friend(){
        name = null;
        birthday = null;
    }
    public Friend(String name) {
        this.name = name;
    }

    public Friend(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Friend(String name, String birthday, int imageRes){
        this.name = name;
        this.birthday = birthday;
        this.imageRes = imageRes;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }

    @Override
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public int getImageRes() {
        return imageRes;
    }

    @Override
    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public List<Item> getWishlist() {
        return wishlist;
    }

    @Override
    public void setWishlist(List<Item> wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public List<Item> getHistory() {
        return  history;
    }

    @Override
    public void setHistory(List<Item> history) {
        this.history = history;
    }
}

