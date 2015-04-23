package com.example.mitchell.gifterhelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserWishlistAdapter extends ArrayAdapter<Item>{
    
    private List<Item> Items;
    private List<Item> Items_display = null;

    public UserWishlistAdapter(Context context, int resource) {
        super(context, resource);
    }

    public UserWishlistAdapter(Context context, List<Item> items) {
        super(context, R.layout.item_layout, items);
        this.Items_display = new ArrayList<Item>();
        this.Items_display.addAll(items);
        this.Items = new ArrayList<Item>();
        this.Items.addAll(items);
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
        name.setText(Items_display.get(position).getName());
        return view;
    }


    public void filter(String charText){
        Log.d("GifterHelper", "Filtering UserWishlist");
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
}
