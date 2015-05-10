package com.gifterhelper;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashe on 4/17/2015.
 */
public class FriendAdapter extends ArrayAdapter<Friend> {

    List<Friend> friends;
    List<Friend> friends_display;
    public FriendAdapter(Context context, int resource) {
        super(context, resource);
    }

    public FriendAdapter(Context context, List<Friend> friends) {
        super(context, R.layout.friend_layout, friends);
        this.friends = friends;
        friends_display = new ArrayList<>();
        this.friends_display.addAll(friends);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.friend_layout,null);
        }
            ImageView profileImg = (ImageView) view.findViewById(R.id.FriendProfile_Home);
            TextView name = (TextView) view.findViewById(R.id.FriendName_Home);
            TextView birthday = (TextView) view.findViewById(R.id.FriendBirthday_Home);
            try{
                //profileImg.setImageResource(friends.get(position).getImageRes());
                friends_display.get(position).fetIfNeeded();
                name.setText(friends_display.get(position).getUserName());
                birthday.setText(friends_display.get(position).getBirthday());
            }catch(Exception e){
                e.printStackTrace();
            }


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
        //We remove all the items in the display list and rebuild from the input
        friends_display.clear();
        for(int i = 0; i < friends.size(); i++)
        {
            Friend friend = friends.get(i);
            Log.d("GifterHelper","Friend name " + friend.getName());
            //If one of the items have the text that we entered we add it to be displayed
            if(friend.getName().toLowerCase().contains(charText)){
                Log.d("GifterHelper","Constraint equals " + friend.getName());
                friends_display.add(friend);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Friend getItem(int position) {
        return friends_display.get(position);
    }

    @Override
    public int getCount() {
        return friends_display.size();
    }
}
