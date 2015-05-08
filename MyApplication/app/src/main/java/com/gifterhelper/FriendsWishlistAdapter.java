package com.gifterhelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendsWishlistAdapter extends ArrayAdapter<Item> {
    List<Item> Items;
    private List<Item> Items_display = null;
    View view;
    Item item;
    String id;

    public FriendsWishlistAdapter(Context context, int resource) {
        super(context, resource);
    }

    public FriendsWishlistAdapter(Context context, List<Item> items) {
        super(context, R.layout.item_layout, items);
        this.Items_display = new ArrayList<Item>();
        this.Items = new ArrayList<Item>();
        if(items.size() != 0){
            this.Items_display.addAll(items);
            this.Items.addAll(items);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        //If the layout
        if (view == null) {

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.friend_wishlist_item, null);
        }

        //GUI
        TextView name = (TextView) view.findViewById(R.id.friend_wishlist_itemname);
        if(Items_display.size() != 0){
            try {
                item = Items_display.get(position).fetchIfNeeded();
                name.setText(item.getName());
                Log.d("GifterHelper", "name " + item.getName());
            } catch (ParseException e) {
                Log.e("GifterHelper", e.getMessage(),e);
            }
        }else{
            Log.d("GifterHelper", "Friend wishlist has no items");
        }

        final Button buyButton = (Button) view.findViewById(R.id.friend_button_wishlist);
        final Button colorButton = (Button) view.findViewById(R.id.friends_box_wishlist);

        //Sets up the default values for the color button and text for buy button
        if(item != null){
            if (item.getPurchased()) {
                buyButton.setText("Cancel");
                colorButton.setBackgroundColor(Color.RED);
            } else if (!item.getPurchased()) {
                buyButton.setText("Will Buy");
                colorButton.setBackgroundColor(Color.GREEN);
            }
        }else{
            Log.d("GifterHelper", "No item in friend wishlist");
        }


        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getPurchased() && item.getBuyerUser().equals(id)) {
                    item.setPurchased(false);
                    item.saveInBackground();
                    buyButton.setText("Will Buy");
                    colorButton.setBackgroundColor(Color.GREEN);
                } else if (!item.getPurchased()) {
                    item.setPurchased(true);
                    item.setBuyerUser(id);
                    item.saveInBackground();
                    buyButton.setText("Cancel");
                    colorButton.setBackgroundColor(Color.RED);
                }

            }
        });
        return view;
    }

    public void filter(String charText){
        Log.d("GifterHelper", "Filtering FriendWishlist");
        charText = charText.toString().toLowerCase();
        Log.d("GifterHelper", "Filtering with " + charText);
        Items_display.clear();
        for(int i = 0; i < Items.size(); i++)
        {
            Item item = Items.get(i);
            Log.d("GifterHelper","Item name " + item.getName());
            if(item.getName().toLowerCase().contains(charText)){
                Log.d("GifterHelper","Constraint equals " + item.getName());
                Items_display.add(item);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Items_display.size();
    }

    @Override
    public Item getItem(int position) {
        return Items_display.get(position);
    }

    public static void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setId(String id) {
        this.id = id;
    }
}

