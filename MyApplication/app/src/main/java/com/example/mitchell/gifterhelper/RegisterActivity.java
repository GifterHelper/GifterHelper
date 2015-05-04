package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by Ethan on 4/11/2015.
 */
public class RegisterActivity extends Activity {

    boolean validUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Button register = (Button)findViewById(R.id.Confirm);
        final EditText username = (EditText)findViewById(R.id.RegisterUserName);
        final EditText password = (EditText)findViewById(R.id.RegisterPassword);
        final EditText matchpassword = (EditText)findViewById(R.id.MatchPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check to see if username and password fields are filled
                boolean notfilled = false;
                if(username.getText().length() == 0){
                    Toast.makeText(RegisterActivity.this,"Please enter username",Toast.LENGTH_SHORT).show();
                    Log.e("GifterHelper","Missing username");
                    notfilled = true;
                }
                if(password.getText().length() == 0 || matchpassword.getText().length() == 0){
                    Toast.makeText(RegisterActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    Log.e("GifterHelper","Missing password");
                    notfilled = true;
                }
                if(!notfilled & password.getText().toString().equals(matchpassword.getText().toString())){
                    //check to see if the email has been used
                    Log.d("GifterHelper", "Query Database with username " + username.getText().toString());
                    ParseQuery<User> query = ParseQuery.getQuery(User.class);
                    query.whereContains("username", username.getText().toString());
                    query.findInBackground(new FindCallback<User>() {

                        @Override
                        public void done(List<User> users, ParseException e){
                            Log.d("GifterHelper", "Retrieved list of users");
                            if (users == null || users.size() == 0) {

                                Log.i("GifterHelper", "Valid Username");
                                //Create new user object
                                User user = new User();
                                user.setUserName(username.getText().toString());
                                user.setPassword(password.getText().toString());
                                user.initFriends();
                                user.initWishlist();
                                user.initHistory();
                                //Save to database
                                user.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("Success")
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        Log.d("GifterHelper", "Saved user to database");
                                                        //Reload App landing page
                                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                        RegisterActivity.this.startActivity(intent);

                                                    }
                                                });
                                        builder.create().show();
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterActivity.this, "Username Taken", Toast.LENGTH_SHORT).show();
                                Log.i("GifterHelper", "Username Taken");
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    //Clear confirmation password
                    Log.e("GifterHelper", "Incorrect Password");
                    matchpassword.setText("");
                    Log.d("GifterHelper", "Cleared match password edittext field");
                }
            }
        });
    }

}
