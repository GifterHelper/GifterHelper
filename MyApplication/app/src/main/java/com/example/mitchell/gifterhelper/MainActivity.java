package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;


public class MainActivity extends Activity {

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
                if (password.getText().length() == 0 ) {
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    Log.e("GifterHelper", "Missing password");
                    notfilled = true;
                }
                if (!notfilled) {
                    //Backend check for user/pass
                    User user = new User();
                    user.setId("aa");
                    boolean validPassword = true;
                    if (validPassword) {
                        //Get user id from backend
                        String id = user.getId();
                        Log.d("GifterHelper","Id is : " + id);
                        //Load homepage
                        Log.i("GifterHelper", "Valid Username");
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("id",id);
                        MainActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Password not valid", Toast.LENGTH_SHORT).show();
                        Log.e("GifterHelper", "Password Incorrect");
                    }
                }
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load register page
                Log.i("GifterHelper", "Valid Username");
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
