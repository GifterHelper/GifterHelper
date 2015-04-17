package com.example.mitchell.gifterhelper;

/**
 * Created by Ethan on 4/16/2015.
 */
public class Item {
    //TODO: Builder?

    private String name;
    private int price;
    private boolean purchased;
    private boolean willBuy;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public boolean getWillBuy() {
        return willBuy;
    }

    public void setWillBuy(boolean willBuy) {
        this.willBuy = willBuy;
    }
}
