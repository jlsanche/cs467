package com.cs467.capstone;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.example.cs467.capstone.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        addPreferencesFromResource(R.xml.preferences);


    }
}
