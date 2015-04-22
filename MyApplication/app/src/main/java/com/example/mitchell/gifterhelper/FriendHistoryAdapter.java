package com.example.mitchell.gifterhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mitchell on 4/21/2015.
 */
public class FriendHistoryAdapter extends ArrayAdapter<Item> {
    List<Item> Items;
    public FriendHistoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    public FriendHistoryAdapter(Context context, List<Item> items) {
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
            view = inflater.inflate(R.layout.friend_history_item,null);
        }
        //Sets the GUI Elements
        TextView name = (TextView) view.findViewById(R.id.friend_history_itemname);
        name.setText(Items.get(position).getName());
        return view;
    }
}
