package com.gifterhelper;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ashe on 4/17/2015.
 */
public class FriendAdapter extends ArrayAdapter<Friend> {

    List<Friend> friends;
    public FriendAdapter(Context context, int resource) {
        super(context, resource);
    }

    public FriendAdapter(Context context, List<Friend> friends) {
        super(context, R.layout.friend_layout, friends);
        this.friends = friends;
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
                friends.get(position).fetIfNeeded();
                name.setText(friends.get(position).getUserName());
                birthday.setText(friends.get(position).getBirthday());
            }catch(Exception e){
                e.printStackTrace();
            }


        return view;
    }
}
