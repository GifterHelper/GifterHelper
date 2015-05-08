package com.gifterhelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/22/2015.
 */
public class UserFriendAdapter extends ArrayAdapter<Friend> {
    ArrayList<Friend> friends;
    ArrayList<Friend> friends_display = null;
    String id;

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
                //Handle Backend
                //We remove the friend from the display list first then we remove it from the
                //stored friend list.
                final Friend removedFriend = friends_display.remove(position);
                boolean removed = friends.remove(removedFriend);
                ParseQuery<User> userParseQuery = new ParseQuery<User>(User.class);
                userParseQuery.getInBackground(id, new GetCallback<User>() {
                    @Override
                    public void done(User user, ParseException e) {
                        List<User> friends = user.getFriends();
                        user.removeFriendByName(removedFriend.getUserName());
                        user.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){

                                }
                                Log.d("GifterHelper", "updated friend");
                            }
                        });
                    }
                });
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
            Log.d("GifterHelper","Friend name " + friend.getUserName());
            if(friend.getUserName().toLowerCase().contains(charText)){
                Log.d("GifterHelper","Constraint is: " + friend.getName());
                friends_display.add(friend);
            }
        }
        notifyDataSetChanged();
    }
    //Need get count to render the correct number
    @Override
    public int getCount() {
        return friends_display.size();
    }

    @Override
    public Friend getItem(int position) {
        return friends_display.get(position);
    }

    public void setId(String id){
        this.id = id;
    }

    /**
     * Update the display with the newly added friend
     * @param object
     */
    @Override
    public void add(Friend object) {
        friends.add(object);
        friends_display.add(object);
        notifyDataSetChanged();
    }
}
