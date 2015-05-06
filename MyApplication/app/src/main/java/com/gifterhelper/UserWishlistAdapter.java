package com.gifterhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/18/2015.
 */
public class UserWishlistAdapter extends ArrayAdapter<Item>{
    
    private List<Item> Items;
    private List<Item> Items_display = null;
    View view;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        view = convertView;
        //If the layout
        if(view == null){

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_layout,null);
        }
        //Sets the GUI Elements
        TextView name = (TextView) view.findViewById(R.id.wishlist_item_name);
        try{
            Item item = Items_display.get(position).fetchIfNeeded();
            name.setText(item.getName());
        }catch (ParseException e) {
            e.printStackTrace();
        }


        Button editItem = (Button) view.findViewById(R.id.wishlist_edit_item);
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final EditText itemName = new EditText(view.getContext());
                //Pulls the item from the parse database if we do not have it already
                try{
                    Item item = Items_display.get(position).fetchIfNeeded();
                    itemName.setText(item.getName());
                    builder.setTitle("Edit Item");
                    builder.setView(itemName);
                    builder.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String item = itemName.getText().toString();

                            Items_display.get(position).setName(item);
                            Log.d("GifterHelper", "Editing item in user wishlist" + item);
                            //update current;
                            Items_display.get(position).saveInBackground();
                            //Hide software keyboard
                            hide_keyboard_from(view.getContext(), view);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Do nothing
                            Log.d("GifterHelper", "Canceling editing item to user wishlist");
                        }
                    });
                    builder.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        Button removeButton = (Button) view.findViewById(R.id.wishlist_remove_item);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Item item = Items_display.remove(position);
                    String id = item.getObjectId();
                    //Call to remove item from database
                    ParseObject.createWithoutData("Item",id).deleteEventually();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    /**
     * Filters the displayed text to be limited to what was entered into the search entry
     * @param charText
     */
    public void filter(String charText){
        Log.d("GifterHelper", "Filtering UserWishlist");
        charText = charText.toString().toLowerCase();
        Log.d("GifterHelper", "Filtering with " + charText);
        //We
        Items_display.clear();
        for(int i = 0; i < Items.size(); i++)
        {
            Item item = Items.get(i);
            Log.d("GifterHelper","Item name " + item.getName());
            //If one of the items have the text that we entered we add it to be displayed
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
}
