package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendHistoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.friends_wishlist,container,false);
        ListView listView = (ListView) mainView.findViewById(R.id.friend_wishlist);
        ListView itemView = (ListView) mainView.findViewById(R.id.UserWishList);
        ArrayList<Item> items = new ArrayList<Item>();
        FriendHistoryAdapter friendlyAdapter = new FriendHistoryAdapter(getActivity().getBaseContext(),items);
        listView.setAdapter(friendlyAdapter);
        return mainView;
    }
}