package com.example.mitchell.gifterhelper;

import com.parse.ParseException;

import java.util.List;

/**
 * Created by Ethan on 4/16/2015.
 */
public class Friend {
    protected User friend;

    public Friend(){
        friend = null;
    }

    public Friend(User friend) {
        this.friend = friend;
    }

    public String getId() {
        return friend.getId();
    }

    public String getName() {
        return friend.getName();
    }

    public String getBirthday() {
       return friend.getBirthday();
    }

    /*public int getImageRes() {
       return friend.getImageRes();
    }*/

    public List<Item> getWishlist() {

        return friend.getWishlist();
    }

    public List<Item> getHistory() {
        return  friend.getHistory();
    }

    public String getUserName() { return friend.getUserName();}

    public void fetIfNeeded() {
        try {
            friend.fetchIfNeeded();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

