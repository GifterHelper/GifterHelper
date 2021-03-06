package com.gifterhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.loginButton);
        TextView register = (TextView) findViewById(R.id.registerText);
        final EditText username = (EditText) findViewById(R.id.UsernameLogin);
        final EditText password = (EditText) findViewById(R.id.PasswordLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean notfilled = false;
                if (username.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    Log.e("GifterHelper", "Missing username");
                    notfilled = true;
                }
                if (password.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    Log.e("GifterHelper", "Missing password");
                    notfilled = true;
                }
                if (!notfilled) {
                    //We first check if we have the user in storage
                    ParseQuery<User> userParseQuery = new ParseQuery<User>(User.class);
                    userParseQuery.whereContains("username", username.getText().toString());
                    Log.d("GifterHelper", "Query for user " + username.getText().toString() + "locally");
                    userParseQuery.fromLocalDatastore();
                    userParseQuery.findInBackground(new FindCallback<User>() {
                        @Override
                        public void done(List<User> users, ParseException e) {
                            if (users.size() > 1) {
                                Toast.makeText(MainActivity.this, "Username/Password not valid", Toast.LENGTH_SHORT).show();
                                Log.d("GifterHelper", "Retrieved more than one user");
                            } else if (users.size() == 1) {
                                User user = users.get(0);
                                id = user.getId();
                                Toast.makeText(MainActivity.this, "Username/Password valid", Toast.LENGTH_SHORT).show();
                                Log.d("GifterHelper", "Loading from local datastore");
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("id", id);
                                MainActivity.this.startActivity(intent);
                            } else {
                                BackgroundQuery(username.getText().toString(),password.getText().toString());
                            }
                        }
                    });

                }
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load register page
                Log.i("GifterHelper", "Registering");
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    void BackgroundQuery(String username, String password){
        //Backend check for user/pass
        final String pass = password;
        ParseQuery<User> remoteUserParseQuery = new ParseQuery<User>(User.class);
        remoteUserParseQuery.whereContains("username", username);
        Log.d("GifterHelper", "Query for user " + username);
        remoteUserParseQuery.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (users.size() > 1) {
                    Toast.makeText(MainActivity.this, "Username/Password not valid", Toast.LENGTH_SHORT).show();
                    Log.d("GifterHelper", "Retrieved more than one user");
                } else if (users.size() == 0) {
                    Toast.makeText(MainActivity.this, "Username/Password not valid", Toast.LENGTH_SHORT).show();
                    Log.d("GifterHelper", "Invalid username");
                } else {
                    if (pass.equals(users.get(0).getPassword())) {
                        //Get user id from backend
                        User user = users.get(0);
                        id = user.getId();
                        //String userName = users.get(0).getUserName();
                        //String userPass = users.get(0).getPassword();
                                                /*Log.d("GifterHelper", "Id is : " + id);
                                                Log.d("GifterHelper", "Name is : " + userName);
                                                Log.d("GifterHelper", "Password is : " + userPass);*/
                        //Load homepage
                        Log.i("GifterHelper", "Valid Username & Password login");
                        //We create a new user to store the values that is necessary
                        //TODO find another way of storing friends, possibly new parse object
                        User userSave = new User();
                        userSave.saveDetail(user.getId(),user.getFriends(),user.getWishlist(),user.getHistory());
                        //user.saveInBackground();
                        user.pinInBackground();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("id", id);
                        MainActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Username/Password not valid", Toast.LENGTH_SHORT).show();
                        Log.e("GifterHelper", "Password Incorrect");
                    }

                }
            }
        });

    }
}
