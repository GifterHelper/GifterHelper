package com.gifterhelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendPagerAdapter extends FragmentStatePagerAdapter{

    public FriendPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    final int tabCount = 2;
    private String tabTitles[] = new String[]{"Wishlist","History"};

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                Fragment tab1 = new FriendWishlistFragment();
                return tab1;
            case 1:
                Fragment tab2 = new FriendHistoryFragment();
                return tab2;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
