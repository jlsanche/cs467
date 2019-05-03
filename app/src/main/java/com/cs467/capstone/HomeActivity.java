package com.cs467.capstone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.cs467.capstone.LoginActivity;
import com.example.cs467.capstone.R;


public class HomeActivity extends AppCompatActivity {
    // TODO Welcome User? And Search Bar?
    // TODO perhaps this can be a local variable
    private View mHomeFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomeFormView = findViewById(R.id.home_view);

        /*Button mLogInButton = (Button) findViewById(R.id.log_in_button);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        }); */

        //MyRatedFoods
        Button mMyRatedFoodsButton = (Button) findViewById(R.id.myratedfoods_button);
        mMyRatedFoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                goToMyRatedFoods();
            }
        });

        //chat
        Button mChatButton = (Button) findViewById(R.id.chat_button);
        mChatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goToChat();
            }
        });

        //advice
        Button mAdviceButton = (Button) findViewById(R.id.advice_button);
        mAdviceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goToAdvice();
            }
        });

        //settings
        Button mSettingsButton = (Button) findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goToSettings();
            }
        });

    }

    /*
     * EXAMPLE: Go to Login page

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
     */


    /*
     * Go to Food Search
     */

    //TODO SEARCH BAR?

    /*
     * Go to My Rated Foods
     */

    private void goToMyRatedFoods() {
        //Intent intent = new Intent(this, MyRatedFoodsActivity.class);
        //startActivity(intent);
    }

    /*
     * Go to Chat/Forum
     */

    private void goToChat() {
        //Intent intent = new Intent(this, ChatActivity.class);
        //startActivity(intent);
    }

    /*
     * Go to Tips/Adivce
     */
    private void goToAdvice() {
        //Intent intent = new Intent(this, AdviceActivity.class);
        //startActivity(intent);
    }

    /**
     * Go to Profile/Settings
     */
    private void goToSettings() {
        //Intent intent - new Intent(this, SettingsActivity.class);
        //startActivity(intent);
    }

}