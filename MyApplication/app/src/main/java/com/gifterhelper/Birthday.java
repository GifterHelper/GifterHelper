package com.gifterhelper;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ethan on 5/5/2015.
 */
@ParseClassName("Birthday")
public class Birthday extends ParseObject {
    public void setMonth(int month){
        put("month", month);
    }

    public int getMonth(){
        return getInt("month");
    }

    public void setDay(int day){
        put("day", day);
    }

    public int getDay(){
        return getInt("day");
    }
}
