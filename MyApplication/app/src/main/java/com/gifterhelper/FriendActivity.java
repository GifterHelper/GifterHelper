package com.gifterhelper;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FriendPagerAdapter friendPagerAdapter;
        ViewPager viewPager;
        setContentView(R.layout.friend_main);
        friendPagerAdapter = new FriendPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.FriendPager);
        viewPager.setAdapter(friendPagerAdapter);

    }
}
