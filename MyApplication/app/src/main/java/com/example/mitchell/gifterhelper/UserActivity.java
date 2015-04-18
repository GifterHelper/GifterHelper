package com.example.mitchell.gifterhelper;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserActivity extends ActionBarActivity {
    UserPagerAdapter userPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        userPagerAdapter = new UserPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.UserPager);
        viewPager.setAdapter(userPagerAdapter);
        PagerTabStrip tabStrip = (PagerTabStrip) findViewById(R.id.userprofile_pager_tab_strip);

    }
}
