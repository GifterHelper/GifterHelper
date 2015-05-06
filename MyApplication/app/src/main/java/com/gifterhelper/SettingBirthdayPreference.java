package com.gifterhelper;

import android.content.Context;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Ethan on 5/5/2015.
 */
public class SettingBirthdayPreference extends DialogPreference {
    private ListView month;
    private ListView day;
    private Context context;

    public SettingBirthdayPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        //month = attrs.getAttributeValue(androidns)
    }

    @Override
    protected View onCreateDialogView() {
        return super.onCreateDialogView();
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if(positiveResult){

        }
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
    }
}
