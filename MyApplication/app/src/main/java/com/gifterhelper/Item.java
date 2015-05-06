package com.gifterhelper;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ethan on 4/16/2015.
 */
@ParseClassName("Item")
public class Item extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public int getPrice() {
        return getInt("price");
    }

    public void setPrice(int price) {
        put("price", price);
    }

    public boolean getPurchased() {
        return getBoolean("purchased");
    }

    public void setPurchased(boolean purchased) {
        put("purchased", purchased);
    }

    public boolean getWillBuy() {
        return getBoolean("getWillBuy");
    }

    public void setWillBuy(boolean willBuy) {
        put("willBuy",willBuy);
    }

    public String getBuyerUser() {
        return getString("BuyerUser");
    }

    public void setBuyerUser(String user) {
        put("BuyerUser",user);
    }
}
