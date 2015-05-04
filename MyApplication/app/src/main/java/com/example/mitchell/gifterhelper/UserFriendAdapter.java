package com.example.mitchell.gifterhelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/22/2015.
 */
public class UserFriendAdapter extends ArrayAdapter<Friend> {
    ArrayList<Friend> friends;
    ArrayList<Friend> friends_display = null;
    public UserFriendAdapter(Context context, int resource) {
        super(context, resource);
    }

    public UserFriendAdapter(Context context, List<Friend> friends) {
        super(context, R.layout.user_friends_layout, friends);
        this.friends = new ArrayList<>();
        friends_display = new ArrayList<>();
        this.friends.addAll(friends);
        this.friends_display.addAll(friends);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.user_friends_layout,null);
        }
        TextView name = (TextView) view.findViewById(R.id.UserFriendName);
        name.setText(friends_display.get(position).getUserName());
        Button removeFriend = (Button) view.findViewById(R.id.UserRemoveFriend);
        removeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GifterHelper","Removing friend " + friends_display.get(position).getName());
                //Handle Backend
                //We remove the friend from the display list first then we remove it from the
                //stored friend list.
                Friend removedFriend = friends_display.remove(position);
                friends.remove(removedFriend);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    /**
     * Filters friend list with given string input
     * @param charText
     */
    public void filter(String charText){
        Log.d("GifterHelper", "Filtering UserFriendList");
        charText = charText.toString().toLowerCase();
        Log.d("GifterHelper", "Filtering with " + charText);
        friends_display.clear();
        for(int i = 0; i < friends.size(); i++)
        {
            Friend friend = friends.get(i);
            Log.d("GifterHelper","Friend name " + friend.getName());
            if(friend.getName().toLowerCase().contains(charText)){
                Log.d("GifterHelper","Constraint is: " + friend.getName());
                friends_display.add(friend);
            }
        }
        notifyDataSetChanged();
    }
}
