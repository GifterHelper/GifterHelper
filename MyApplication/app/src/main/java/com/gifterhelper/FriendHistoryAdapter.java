package com.gifterhelper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendHistoryAdapter extends ArrayAdapter<Item> {
    List<Item> Items;
    private List<Item> Items_display = null;
    View view;
    Item item;
    String id;

    public FriendHistoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    public FriendHistoryAdapter(Context context, List<Item> items) {
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
            view = inflater.inflate(R.layout.friend_history_item, null);
        }

        //GUI
        TextView name = (TextView) view.findViewById(R.id.friend_history_itemname);
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
        return view;
    }

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Item getItem(int position) {
        return Items.get(position);
    }
}
