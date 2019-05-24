package com.cs467.capstone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class PasswordResetActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText userEmail;
    Button userPassword;
    Toolbar toolbar;

    FirebaseAuth mAuth;

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);


        toolbar = findViewById(R.id.toolbar2);
        progressBar = findViewById(R.id.progressBar);
        userEmail = findViewById(R.id.etUserEmail);
        userPassword = findViewById(R.id.btnResetPassword);

        toolbar.setTitle("Password Reset");

        mAuth =FirebaseAuth.getInstance();

        userPassword.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            Log.d(TAG, "Email sent");

                            Toast.makeText(PasswordResetActivity.this, "Check your email for password reset instructions", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(PasswordResetActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();


                        }

                    }
                });
            }
        });





    }
}
