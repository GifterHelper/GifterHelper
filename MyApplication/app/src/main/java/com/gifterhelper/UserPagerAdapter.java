package com.gifterhelper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Ethan on 4/18/2015.
 */
//DEFAULT SET UP
public class UserPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Wishlist","History","Friends"};

    public UserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Statically set count as we only have 3 different pages
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("GifterHelper", "Position " + position);
        switch(position)
        {
            case 0:
                Fragment fragment_tab1 = new UserWishlistFragment();
                return fragment_tab1;

            case 1:
                Fragment fragment_tab2 = new UserHistoryFragment();
                return fragment_tab2;

            case 2:
                Fragment fragment_tab3 = new UserFriendsFragment();
                return fragment_tab3;
        }
        return null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
