package com.example.mitchell.gifterhelper;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Ethan on 4/29/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "IFe87iaHpQRu7vEyrX44YZVxIkwIQRWb6BaQbEL3", "6WX3SBUcMfd12TlpQORRaaMgh62J700W9oKpi8AH");
        ParseObject.registerSubclass(User.class);
        //ParseObject.registerSubclass(Friend.class);
    }
}
