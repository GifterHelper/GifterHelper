package com.gifterhelper;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Ethan on 4/29/2015.
 */
public class GifterHelper extends Application {
    //Possibly unneeded taken from the Parse example for localdatastore
    public static final String USER_GROUP_NAME = "USER_INFO";
    public static final String USER_WISHLIST = "USER_WISHLIST";

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "IFe87iaHpQRu7vEyrX44YZVxIkwIQRWb6BaQbEL3", "6WX3SBUcMfd12TlpQORRaaMgh62J700W9oKpi8AH");
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Item.class);
    }
}
