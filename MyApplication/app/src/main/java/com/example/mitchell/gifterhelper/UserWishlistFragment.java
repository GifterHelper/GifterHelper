package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.app.AlertDialog;
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
import java.util.ArrayList;

/**
 * Created by Ethan on 4/18/2015.
 */
//Works similar to activities,this is a modified HomeActivity
public class UserWishlistFragment extends Fragment {
    ListView itemView;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //InflateLayout of fragment
        rootView = inflater.inflate(R.layout.user_wishlist_fragment,container,false);
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
                        String item = itemName.getText().toString();
                        Log.d("GifterHelper", "Adding item to user wishlist" + item);
                        //Send pusher to check to check to see if friend e-mail exists
                        //update current;
                        hide_keyboard_from(getActivity().getBaseContext(),getView());
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
