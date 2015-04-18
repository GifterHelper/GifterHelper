package com.example.mitchell.gifterhelper;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/12/2015.
 */
public class HomeActivity extends ActionBarActivity {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        else if(id == R.id.user_profile){
            Intent intent = new Intent(HomeActivity.this,UserActivity.class);
            HomeActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
