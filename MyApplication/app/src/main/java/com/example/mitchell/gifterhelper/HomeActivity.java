package com.example.mitchell.gifterhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/12/2015.
 */
public class HomeActivity extends ActionBarActivity {
    public List<Friend> friends;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getStringExtra("id");
        Log.d("GifterHelper", "Id is " + id);
        setContentView(R.layout.home_layout);

        ListView FriendView = (ListView) findViewById(R.id.FriendLayout);
        friends = new ArrayList<Friend>();
        friends.add(new Friend("Jeff", new Birthday("March", 5),R.drawable.profile_default));
        friends.add(new Friend("Amy", new Birthday("September", 17),R.drawable.profile_default));
        friends.add(new Friend("Susan", new Birthday("April", 19),R.drawable.profile_default));
        friends.add(new Friend("Rachel", new Birthday("December", 20),R.drawable.profile_default));
        friends.add(new Friend("Arnal", new Birthday("June", 11),R.drawable.profile_default));
        friends.add(new Friend("Win", new Birthday("January", 23),R.drawable.profile_default));

        ArrayAdapter friend_display = new FriendAdapter(HomeActivity.this, friends);
        FriendView.setAdapter(friend_display);

        //Set spinner strings
        Spinner spinner = (Spinner) findViewById(R.id.HomeSortFriendSpiner);
        ArrayAdapter<CharSequence> spinnerArray = ArrayAdapter.createFromResource(this, R.array.sort_choices, android.R.layout.simple_spinner_item);
        spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArray);

        FriendView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,FriendActivity.class);
                intent.putExtra("id", friends.get(position).getId());
                HomeActivity.this.startActivity(intent);
            }
        });

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
            Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
            HomeActivity.this.startActivity(intent);
            return true;
        }

        else if(id == R.id.user_profile){
            Intent intent = new Intent(HomeActivity.this,UserActivity.class);
            intent.putExtra("id",this.id);
            HomeActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
