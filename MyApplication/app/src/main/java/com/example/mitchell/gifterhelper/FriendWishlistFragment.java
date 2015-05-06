package com.example.mitchell.gifterhelper;

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
/*public class FriendWishlistFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.friends_wishlist,container,false);
        ListView listView = (ListView) mainView.findViewById(R.id.friend_wishlist);
        ListView itemView = (ListView) mainView.findViewById(R.id.UserWishList);
        ArrayList<Item> items = new ArrayList<Item>();
        FriendsWishlistAdapter friendlyAdapter = new FriendsWishlistAdapter(getActivity().getBaseContext(),items);
        listView.setAdapter(friendlyAdapter);
        return mainView;
    }
}
*/
public class FriendWishlistFragment extends Fragment {
    ArrayList<Item> items;
    ListView itemView;
    View rootView;
    FriendsWishlistAdapter wishlistAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        rootView = inflater.inflate(R.layout.user_wishlist_fragment,container,false);
        //Note need to use findViewById from the rootview as that is the view that we are going to be displaying
        itemView = (ListView) rootView.findViewById(R.id.UserWishList);

        String id = getActivity().getIntent().getStringExtra("id");
        items = new ArrayList<Item>();

        ParseQuery<User> userQuery = new ParseQuery<User>(User.class);
        userQuery.getInBackground(id, new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                items = (ArrayList<Item>) user.getWishlist();
                Log.d("GifterHelper", "Wishlist size : " + items.size());
                wishlistAdapter = new FriendsWishlistAdapter(getActivity().getBaseContext(), items);
                itemView.setAdapter(wishlistAdapter);
                itemView.setTextFilterEnabled(true);
            }
        });


        final EditText itemNameSearch = (EditText) rootView.findViewById(R.id.UserWishList_search);
        itemNameSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //wishlistAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button addItem = (Button) rootView.findViewById(R.id.UserWishList_AddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
                final EditText itemName = new EditText(rootView.getContext());
                builder.setTitle("Add Item");
                builder.setView(itemName);
                builder.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String itemNamestr = itemName.getText().toString();
                        Log.d("GifterHelper", "Adding item to user wishlist" + itemNamestr);
                        ParseQuery<User> userQuery = new ParseQuery<User>(User.class);
                        userQuery.getInBackground(getActivity().getIntent().getStringExtra("id"), new GetCallback<User>() {
                            @Override
                            public void done(User user, ParseException e) {
                                Item item = new Item();
                                item.setName(itemNamestr);
                                Log.d("GifterHelper", "before add item to list " + user.getWishlist().size());
                                user.addItem(item);
                                user.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        Log.d("GifterHelper", "added item to list ");
                                    }
                                });
                            }
                        });
                        //update current;
                        hide_keyboard_from(getActivity().getBaseContext(), getView());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                        Log.d("GifterHelper", "Canceling adding item to user wishlist");
                    }
                });
                builder.show();
            }
        });

        return rootView;
    }

    public static void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
