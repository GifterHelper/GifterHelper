package com.gifterhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;


/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendWishlistFragment extends Fragment {
    ArrayList<Item> items;
    ListView itemView;
    View rootView;
    FriendsWishlistAdapter wishlistAdapter;
    String friendId;
    String userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        rootView = inflater.inflate(R.layout.friends_wishlist,container,false);
        //Note need to use findViewById from the rootview as that is the view that we are going to be displaying
        itemView = (ListView) rootView.findViewById(R.id.friend_wishlist);

        friendId = getActivity().getIntent().getStringExtra("friendId");
        userId = getActivity().getIntent().getStringExtra("userId");
        items = new ArrayList<Item>();

        ParseQuery<User> userQuery = new ParseQuery<User>(User.class);
        userQuery.getInBackground(friendId, new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                items = (ArrayList<Item>) user.getWishlist();
                for(int i = 0; i < items.size(); i++){
                    try {
                        items.get(i).fetchIfNeeded();
                        Log.d("GifterHelper", "Item " + i + " in wishlist is " + items.get(i).getName());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
                Log.d("GifterHelper", "Wishlist size : " + items.size());
                wishlistAdapter = new FriendsWishlistAdapter(getActivity().getBaseContext(), items);
                wishlistAdapter.setId(userId);
                itemView.setAdapter(wishlistAdapter);
                itemView.setTextFilterEnabled(true);
            }
        });


        final EditText itemNameSearch = (EditText) rootView.findViewById(R.id.friend_wishlist_search);
        itemNameSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!= null && wishlistAdapter != null){
                    wishlistAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        return rootView;
    }

    public static void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
