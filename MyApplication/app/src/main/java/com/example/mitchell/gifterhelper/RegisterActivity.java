package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Ethan on 4/11/2015.
 */
public class RegisterActivity extends Activity {
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
                    //check to see if username has been used

                    boolean validUserName = true;
                    if(validUserName) {
                        //Load login
                        Log.i("GifterHelper", "Valid Username");
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        RegisterActivity.this.startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this,"Username taken",Toast.LENGTH_SHORT).show();
                        Log.e("GifterHelper","Username Taken");
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    //Clear confirmation password
                    Log.e("GifterHelper","Incorrect Passowrd");
                    matchpassword.setText("");
                    Log.d("GifterHelper","Cleared match password edittext field");
                }
            }
        });
    }

}
