package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserFriendsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        return inflater.inflate(R.layout.user_friends_fragment,container,false);
    }
}
