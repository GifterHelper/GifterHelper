package com.gifterhelper;

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

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 4/12/2015.
 */
public class HomeActivity extends ActionBarActivity {
    public List<Friend> friends;
    private ListView FriendView;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getStringExtra("id");
        Log.d("GifterHelper", "Id is " + id);
        setContentView(R.layout.home_layout);

        FriendView = (ListView) findViewById(R.id.FriendLayout);
        friends = new ArrayList<Friend>();
        ParseQuery<User> query = new ParseQuery<User>(User.class);
        //query.fromLocalDatastore();
        query.getInBackground(id,new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                if(user == null){
                    Log.e("GifterHelper", "Could not get id");
                    Log.e("GifterHelper", e.getMessage(),e);
                }else{
                    List<User> friendUser = user.getFriends();

                    for(User friend : friendUser){
                        friends.add(new Friend(friend));
                    }
                    ArrayAdapter friend_display = new FriendAdapter(HomeActivity.this, friends);
                    FriendView.setAdapter(friend_display);
                }
            }
        });


        //Set spinner strings
        Spinner spinner = (Spinner) findViewById(R.id.HomeSortFriendSpiner);
        ArrayAdapter<CharSequence> spinnerArray = ArrayAdapter.createFromResource(this, R.array.sort_choices, android.R.layout.simple_spinner_item);
        spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArray);

        FriendView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,FriendActivity.class);
                intent.putExtra("friendId", friends.get(position).getId());
                intent.putExtra("userId", getIntent().getStringExtra("id"));
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
            intent.putExtra("id",this.id);
            HomeActivity.this.startActivity(intent);
            return true;
        }

        else if(id == R.id.user_profile){
            Intent intent = new Intent(HomeActivity.this,UserActivity.class);
            intent.putExtra("id",getIntent().getStringExtra("id"));
            HomeActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Reupdate the Friend List from database when reentering the home activity
        ParseQuery<User> query = new ParseQuery<User>(User.class);
        query.getInBackground(id, new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                if (user == null) {
                    Log.e("GifterHelper", "Could not get id");
                } else {
                    List<User> friendUser = user.getFriends();
                    //Clear friend list
                    friends.clear();
                    for (User friend : friendUser) {
                        friends.add(new Friend(friend));
                    }
                    ArrayAdapter friend_display = new FriendAdapter(HomeActivity.this, friends);
                    FriendView.setAdapter(friend_display);
                }
            }
        });
    }
}
