package com.example.mitchell.gifterhelper;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Ethan on 4/22/2015.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
