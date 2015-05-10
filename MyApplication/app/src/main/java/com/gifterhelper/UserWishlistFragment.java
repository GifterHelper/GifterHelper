package com.gifterhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
//Works similar to activities,this is a modified HomeActivity
public class UserWishlistFragment extends Fragment {
    ArrayList<Item> items;
    ListView itemView;
    View rootView;
    Item item;
    UserWishlistAdapter wishlistAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        rootView = inflater.inflate(R.layout.user_wishlist_fragment,container,false);
        String id = getActivity().getIntent().getStringExtra("id");
        //TODO: Set up local storage, only update database to the addition of items when navigating away
        ParseQuery<User> userQuery = new ParseQuery<User>(User.class);
        userQuery.getInBackground(id, new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {

                //Note need to use findViewById from the rootview as that is the view that we are going to be displaying
                itemView = (ListView) rootView.findViewById(R.id.UserWishList);
                //Gets the user id from the extra
                items = new ArrayList<Item>();
                //Once we have the user's data we set the wishlist and display the info
                items = (ArrayList<Item>) user.getWishlist();
                Log.d("GifterHelper", "Wishlist size : " + items.size());
                wishlistAdapter = new UserWishlistAdapter(getActivity().getBaseContext(), items);
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
                if(wishlistAdapter == null || s == null){
                    //wishlistadapter was not init?
                }else{
                    wishlistAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button addItem = (Button) rootView.findViewById(R.id.UserWishList_AddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uses the view that the fragment is called in
                AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
                final EditText itemName = new EditText(rootView.getContext());
                builder.setTitle("Add Item");
                builder.setView(itemName);
                builder.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String itemNamestr = itemName.getText().toString();
                        Log.d("GifterHelper", "Adding item to user wishlist" + itemNamestr);
                        //TODO: Hold off on adding items until we navigate away from the fragment
                        ParseQuery<User> userQuery = new ParseQuery<User>(User.class);
                        userQuery.fromLocalDatastore();
                        userQuery.getInBackground(getActivity().getIntent().getStringExtra("id"), new GetCallback<User>() {
                            @Override
                            public void done(User user, ParseException e) {
                                item = new Item();
                                item.setName(itemNamestr);
                                //We have a creator id so that we can easily remove the item from the user
                                item.setCreatorUser(getActivity().getIntent().getStringExtra("id"));
                                Log.d("GifterHelper", "before add item to list " + user.getWishlist().size());
                                user.addItem(item);
                                user.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        Log.d("GifterHelper", "added item to list ");
                                        wishlistAdapter.add(item);
                                    }
                                });
                            }
                        });
                        //Hide software keyboard after exit from window
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
