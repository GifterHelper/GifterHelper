package com.gifterhelper;

import android.util.Log;

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

    private String id;
    private List<User> friends;
    private List<Item> wishlist;
    private List<Item> history;

    public User(){
        super();
    }

    public void saveDetail(String id, List<User> friends, List<Item> wishlist, List<Item> history){
        this.id = id;
        this.friends = friends;
        this.wishlist = wishlist;
        this.history = history;
    }

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

    public void setFriendList(List<User> friends) { put("friends", friends);}

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

    public void removeFriendByName(String friendUserName){
        List<User> friends = (List<User>)get("friends");
        Log.d("GifterHelper", "Searching friends list for" + friendUserName);
        for(int i = 0; i < friends.size(); i++){
            Log.d("GifterHelper", "Comparing to " + friends.get(i).getUserName());
            if(friends.get(i).getUserName().equals(friendUserName)){
                User removed = friends.remove(i);
                Log.d("GifterHelper", "Removed friend " + removed.getUserName());
                put("friends", friends);
                return;
            }
        }
        Log.d("GifterHelper", "Friend not found");
    }
}

