package com.gifterhelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String id = getIntent().getStringExtra("id");
//        Log.d("GifterHelper", "ID is " + id);
//        ParseQuery<User>parseQuery = new ParseQuery<User>(User.class);
//        parseQuery.whereContains("objectId", id);
//        parseQuery.findInBackground(new FindCallback<User>() {
//            @Override
//            public void done(List<User> users, ParseException e) {
//                if(users.size() > 0){
//                    Log.d("GifterHelper", "More than one of the same OID");
//                }else{
//                    User user = new User();
//                    try {
//                        //Unpins or removed saved user
//                        user.unpinAll();
//                        //Updates saved user
//                        user = users.get(0);
//                        user.pinInBackground();
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        });
        FriendPagerAdapter friendPagerAdapter;
        ViewPager viewPager;
        setContentView(R.layout.friend_main);
        friendPagerAdapter = new FriendPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.FriendPager);
        viewPager.setAdapter(friendPagerAdapter);

    }
}
