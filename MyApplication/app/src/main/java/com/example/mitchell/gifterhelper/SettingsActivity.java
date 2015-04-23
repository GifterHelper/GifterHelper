package com.example.mitchell.gifterhelper;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Ethan on 4/22/2015.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,new SettingsFragment())
                .commit();
    }
}
