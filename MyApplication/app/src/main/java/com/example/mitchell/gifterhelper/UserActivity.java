package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserActivity extends FragmentActivity {
    UserPagerAdapter userPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        //As we are inheriting from actionbar activity
        userPagerAdapter = new UserPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.UserPager);
        viewPager.setAdapter(userPagerAdapter);
        PagerTabStrip tabStrip = (PagerTabStrip) findViewById(R.id.userprofile_pager_tab_strip);
        tabStrip.setPadding(2,2,2,2);
    }
}
