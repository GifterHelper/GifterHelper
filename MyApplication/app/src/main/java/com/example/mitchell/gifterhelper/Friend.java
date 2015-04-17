package com.example.mitchell.gifterhelper;

import java.util.Date;
import java.util.List;

/**
 * Created by Ethan on 4/16/2015.
 */
public class Friend extends Profile{
    public Friend(String name) {
        super(name);
    }

    public Friend(String name, Birthday birthday) {
        super(name, birthday);
    }

    public Friend(String name, Birthday birthday, int imageRes) {
        super(name, birthday, imageRes);
    }
}

