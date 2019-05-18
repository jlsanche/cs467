package com.cs467.capstone;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.cs467.capstone.R;

public class SettingsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        if(findViewById(R.id.fragment_container) != null) {

            if(savedInstanceState != null) {

                return;


            } else {

                getFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsFragment()).commit();
            }


        }




    }
}
