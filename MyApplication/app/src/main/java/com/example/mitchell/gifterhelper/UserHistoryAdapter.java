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
public class UserHistoryAdapter extends ArrayAdapter<Item> {
    //List of items to display
    List<Item> items;
    public UserHistoryAdapter(Context context, int resource) {
        super(context, resource);
    }
    //We use the item_history layout for each individual item to display
    public UserHistoryAdapter(Context context, List<Item> items) {
        super(context, R.layout.item_history, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            //We inflate the item_history layout to the view
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_history,null);
        }
        //We set the item name
        TextView textView = (TextView) view.findViewById(R.id.hisotry_item_name);
        textView.setText(items.get(position).getName());
        return view;
    }
}
