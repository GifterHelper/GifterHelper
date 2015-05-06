package com.example.mitchell.gifterhelper;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseClassName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/16/2015.
 */
@ParseClassName("User")
public class User extends ParseObject implements Profile {

    @Override
    public String getId() {
        return this.getObjectId();
    }

    @Override
    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    @Override
    public String getBirthday() {
        return getString("birthday");
    }

    public void setBirthday(String birthday) {
        put("birthday", birthday);
    }

    public void setDefaultSettings(){
        put("publicAdd", true);
        put("publicHistory", true);
    }
    public void setPublicAdd(Boolean value){
        put("publicAdd", value);
    }

    public boolean getPublicAdd(){
        return getBoolean("publicAdd");
    }

    public void setShareHistory(Boolean value){
        put("publicHistory", value);
    }

    public boolean getShareHistory(){
        return getBoolean("publicHistory");
    }

    public ParseObject getImageRes() {
        return (ParseObject)get("ProfileImage");
    }

    /*
    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }*/

    public void initWishlist(){
        List<Item> wishlist = new ArrayList<Item>();
        put("wishlist",wishlist);
    }

    @Override
    public List<Item> getWishlist() {
        return (List<Item>)get("wishlist");
    }

    public void setWishlist(List<Item> wishlist) {
        put("wishlist", wishlist);
    }

    @Override
    public List<Item> getHistory() {
        return (List<Item>)get("history");
    }

    public void initFriends(){
        List<User> friends = new ArrayList<User>();
        put("friends",friends);
    }

    @Override
    public List<User> getFriends() {
        return (List<User>)get("friends");
    }

    public void initHistory(){
        List<Item> history = new ArrayList<Item>();
        put("history",history);
    }

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
        List<Item>wishlist = (List<Item>)get("wishlist");
        wishlist.add(item);
        put("wishlist", wishlist);
    }

    public void removeItem(Item item) {
        List<Item>wishlist = (List<Item>)get("wishlist");
        wishlist.remove(item);
        put("wishlist", wishlist);
    }

    public void moveToHistory(Item item) {
        List<Item>history = (List<Item>)get("history");
        history.add(item);
        put("history", history);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public void addFriend(User friend){
        List<User> friends = (List<User>)get("friends");
        friends.add(friend);
        put("friends", friends);
    }
}

