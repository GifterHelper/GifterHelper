package com.example.mitchell.gifterhelper;

import java.util.List;

/**
 * Created by Ashe on 4/17/2015.
 */
public interface Profile {

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getBirthday();

    public void setBirthday(String birthday);

    public int getImageRes();

    public void setImageRes(int imageRes);

    public List<Item> getWishlist();

    public void setWishlist(List<Item> wishlist);

    public List<Item> getHistory();

    public void setHistory(List<Item> history);

}
