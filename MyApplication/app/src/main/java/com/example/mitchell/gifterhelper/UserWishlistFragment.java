package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
//Works similar to activities,this is a modified HomeActivity
public class UserWishlistFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        View rootView = inflater.inflate(R.layout.user_wishlist_fragment,container,false);
        //Note need to use findViewById from the rootview as that is the view that we are going to be displaying
        ListView itemView = (ListView) rootView.findViewById(R.id.UserWishList);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Xbox One"));
        items.add(new Item("Trading Cards"));
        items.add(new Item("Printer Paper"));

        UserWishlistAdapter wishlistAdapter = new UserWishlistAdapter(getActivity().getBaseContext(),items);
        itemView.setAdapter(wishlistAdapter);
        return rootView;
    }
}
