package com.example.mitchell.gifterhelper;

import com.parse.ParseObject;
import  com.parse.ParseFile;
import com.parse.ParseClassName;

import java.util.Date;
import java.util.List;

/**
 * Created by Ethan on 4/16/2015.
 */
@ParseClassName("User")
public class User extends ParseObject implements Profile  {

    protected String name;
    protected int imageRes;
    protected List<Item> wishlist;
    protected List<Item> history;
    protected String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return getString("name");
    }

    @Override
    public void setName(String name) {
        put("name", name);
    }

    @Override
    public String getBirthday() {
        return getString("birthday");
    }

    @Override
    public void setBirthday(String birthday) {
        put("birthday", birthday);
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
        return (List<Item>)get("wishlist");
    }

    @Override
    public void setWishlist(List<Item> wishlist) {
        put("wishlist", wishlist);
    }

    @Override
    public List<Item> getHistory() {
        return (List<Item>)get("history");
    }

    @Override
    public void setHistory(List<Item> history) {
        put("history", history);
    }

    public String getUserName() {
        return getString("username");
    }

    public void setUserName(String username) {
        put("username", username);
    }

    public void addItem(Item item) {
        wishlist.add(item);
        put("wishlist", wishlist);
    }

    public void removeItem(Item item) {
        this.wishlist.remove(item);
        put("wishlist", wishlist);
    }

    public void moveToHistory(Item item) {
        this.history.add(item);
        put("history", history);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }
}

