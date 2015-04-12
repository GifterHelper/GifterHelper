package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        final EditText username = (EditText)findViewById(R.id.UserName);
        final EditText password = (EditText)findViewById(R.id.Password);
        final EditText matchpassword = (EditText)findViewById(R.id.MatchPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().length() == 0){
                    Toast.makeText(RegisterActivity.this,"Please enter username",Toast.LENGTH_SHORT).show();
                }
                if(password.getText().length() == 0 || matchpassword.getText().length() == 0){
                    Toast.makeText(RegisterActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                }
                if(password.getText().toString().equals(matchpassword.getText().toString())){
                    //check to see if username has been used

                    boolean validUserName = true;
                    if(validUserName){
                        //Load main menu

                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    //Clear confirmation password
                    matchpassword.setText("");
                }
            }
        });
    }

}
