package com.example.mitchell.gifterhelper;

import java.util.List;

/**
 * Created by Ethan on 4/17/2015.
 */
public interface Profile {

    public String getId();

    public String getName();

    public String getBirthday();

    public List<Item> getWishlist();

    public List<Item> getHistory();

    public List<User> getFriends();

}
