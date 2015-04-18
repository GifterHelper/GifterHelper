package com.example.mitchell.gifterhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserWishlistAdapter extends ArrayAdapter<Item> {
    
    List<Item> Items;
    public UserWishlistAdapter(Context context, int resource) {
        super(context, resource);
    }

    public UserWishlistAdapter(Context context, List<Item> items) {
        super(context, R.layout.item_layout, items);
        this.Items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        //If the layout
        if(view == null){

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_layout,null);
        }
        //Sets the GUI Elements
        TextView name = (TextView) view.findViewById(R.id.wishlist_item_name);
        name.setText(Items.get(position).getName());
        return view;
    }
}
