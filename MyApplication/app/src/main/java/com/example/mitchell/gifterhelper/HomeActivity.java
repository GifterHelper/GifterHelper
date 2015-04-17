package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/12/2015.
 */
public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_layout);
        ListView FriendView = (ListView) findViewById(R.id.FriendLayout);
        List<Friend> friends = new ArrayList<Friend>();
        friends.add(new Friend("Jeff", new Birthday("March", 5),R.drawable.profile_default));
        friends.add(new Friend("Amy", new Birthday("September", 17),R.drawable.profile_default));
        friends.add(new Friend("Susan", new Birthday("April", 19),R.drawable.profile_default));
        friends.add(new Friend("Rachel", new Birthday("December", 20),R.drawable.profile_default));
        friends.add(new Friend("Arnal", new Birthday("June", 11),R.drawable.profile_default));
        friends.add(new Friend("Win", new Birthday("January", 23),R.drawable.profile_default));

        ArrayAdapter friend_display = new FriendAdapter(HomeActivity.this, friends);
        FriendView.setAdapter(friend_display);
    }
}
