package com.cs467.capstone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.example.cs467.capstone.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    private Context mContext;
    private Activity mActivity;


    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {

         /* Inflates the given XML resource and adds the preference hierarchy to
        the current preference hierarchy
        Fragment is an operation that runs within a larger Activity class.*/
        setPreferencesFromResource(R.xml.preferences, rootKey);



    }
}
