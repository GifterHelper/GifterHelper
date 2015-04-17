package com.example.mitchell.gifterhelper;

/**
 * Created by Ethan on 4/17/2015.
 */
public class Birthday {
    private int day;
    private String month;

    public Birthday(String month, int day) {
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return month + " " + day;
    }
}
