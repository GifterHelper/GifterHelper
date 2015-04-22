package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
//Works similar to activities,this is a modified HomeActivity
public class UserWishlistFragment extends Fragment {
    ListView itemView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        View rootView = inflater.inflate(R.layout.user_wishlist_fragment,container,false);
        //Note need to use findViewById from the rootview as that is the view that we are going to be displaying
        itemView = (ListView) rootView.findViewById(R.id.UserWishList);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Xbox One"));
        items.add(new Item("Trading Cards"));
        items.add(new Item("Printer Paper"));

        final UserWishlistAdapter wishlistAdapter = new UserWishlistAdapter(getActivity().getBaseContext(),items);
        itemView.setAdapter(wishlistAdapter);
        itemView.setTextFilterEnabled(true);
        final EditText itemNameSearch = (EditText) rootView.findViewById(R.id.UserWishList_search);
        itemNameSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wishlistAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return rootView;
    }
}
