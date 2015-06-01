package com.gifterhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

/**
 * Created by Ethan on 4/22/2015.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    User localUser;
    public static final String KEY_PREF_EMAIL = "pref_key_user_email";
    public static final String KEY_PREF_NAME = "pref_key_user_name";
    public static final String KEY_PREF_PASS = "pref_key_user_password";
    public static final String KEY_PREF_PUBLIC_HISTORY = "pref_key_user_history_public";
    public static final String KEY_PREF_PUBLIC_ADD = "pref_key_user_add_public";
    public static final String KEY_PREF_BDAY = "pref_key_user_birthday";

    EditTextPreference emailPref;
    EditTextPreference namePref;
    EditTextPreference bdayPref;
    CheckBoxPreference publicAdd;
    CheckBoxPreference publicHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        String id = getActivity().getIntent().getStringExtra("id");
        Log.d("GifterHelper", "Passed id is : " + id);

        //Retrieve User
        ParseQuery<User> parseQuery = new ParseQuery<User>(User.class);
        parseQuery.fromLocalDatastore();
        parseQuery.getInBackground(id, new GetCallback<User>() {
            @Override
            public void done(User user, ParseException e) {
                if(user != null){
                    localUser = user;
                    //Set display values for the user settings
                    emailPref = (EditTextPreference) findPreference(KEY_PREF_EMAIL);
                    emailPref.setSummary(localUser.getUserName());

                    publicAdd = (CheckBoxPreference) findPreference(KEY_PREF_PUBLIC_ADD);
                    publicAdd.setChecked(localUser.getPublicAdd());

                    publicHistory = (CheckBoxPreference) findPreference(KEY_PREF_PUBLIC_HISTORY);
                    publicHistory.setChecked(localUser.getShareHistory());

                    namePref = (EditTextPreference) findPreference(KEY_PREF_NAME);

                    if(localUser.getName() != null){
                        namePref.setSummary(localUser.getName());
                    }else{
                        namePref.setSummary("No set Display Name");
                    }

                    bdayPref = (EditTextPreference) findPreference(KEY_PREF_BDAY);
                    if(localUser.getBirthday() != null){
                        bdayPref.setSummary(localUser.getBirthday());
                    }else{
                        bdayPref.setSummary("No Birthday Set");
                    }

                }else{
                    Log.d("GifterHelper", "Invalid user id");
                }
            }
        });
    }




    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(KEY_PREF_EMAIL)){
            emailPref = (EditTextPreference) findPreference(key);
            final String email = emailPref.getText().toString();
            emailPref.setText("");
            localUser.setUserName(email);
            localUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("GifterHelper", "changed email to " + email);

                }
            });
        }
        else if(key.equals(KEY_PREF_NAME)){
            namePref = (EditTextPreference) findPreference(key);
            final String name = namePref.getText().toString();
            emailPref.setText("");
            localUser.setName(name);
            localUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("GifterHelper", "Changed name to " + name);
                }
            });
        }
        else if(key.equals(KEY_PREF_PASS)){
            EditTextPreference passPref = (EditTextPreference) findPreference(key);
            final String pass = passPref.getText().toString();
            localUser.setPassword(pass);
            localUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("GifterHelper", "Updated password");
                }
            });
        }
        else if(key.equals(KEY_PREF_PUBLIC_ADD)){
            final boolean add = publicAdd.isChecked();
            localUser.setPublicAdd(add);
            localUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("GifterHelper", "Updated public add to " + add);
                }
            });
        }
        else if(key.equals(KEY_PREF_PUBLIC_HISTORY)){
            final boolean history = publicHistory.isChecked();
            localUser.setShareHistory(history);
            localUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("GifterHelper", "Updated sharing history to " + history);
                }
            });
        }
        else if(key.equals(KEY_PREF_BDAY)){
            Log.d("GifterHelper", "called bday");
            bdayPref = (EditTextPreference) findPreference(key);
            final String bday = bdayPref.getText().toString();
            emailPref.setText("");
            localUser.setBirthday(bday);
            localUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("GifterHelper", "Changed bday to " + bday);
                }
            });
        }
    }


    //Overwriting to handle onResume lifecycle
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    //Overwriting to handle onPause lifecycle
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
