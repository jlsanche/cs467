package com.cs467.capstone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.cs467.capstone.SignUpActivity;
import com.example.cs467.capstone.R;

public class LandingActivity extends AppCompatActivity {
    // TODO perhaps this can be a local variable
    private View mLandingFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mLandingFormView = findViewById(R.id.landing_view);

        Button mLogInButton = (Button) findViewById(R.id.log_in_button);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignup();
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

    }

    /*
     * Go to Login page
     */
    private void goToSignup() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    /**
     * Go to Register page
     */
    private void goToRegister() {

    }

}
