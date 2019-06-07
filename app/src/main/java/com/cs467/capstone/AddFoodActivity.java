package com.cs467.capstone;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;

public class AddFoodActivity extends AppCompatActivity {

    TextView addFood;
    Button submit;
    RadioGroup radio;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        // set up buttons and text boxes
        addFood = (TextView) findViewById(R.id.tvValue);
        submit = (Button) findViewById(R.id.btnSubmit);
        radio = findViewById(R.id.radio);

        addFood.setText(getIntent().getStringExtra("value"));

        final CreateAddFood act = new CreateAddFood();

        // listener for submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radio.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if(radioButton == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please choose a rating", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    String rating = (String) radioButton.getText();
                    String value = addFood.getText().toString();
                    Food newFood = new Food(value, rating);

                    FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();

                    act.createAddNewFood(newFood, mAuth.getCurrentUser().getUid());
                    //Toast toast = Toast.makeText(getApplicationContext(), "Food added!", Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER, 0, 0);
                    //toast.show();

                    //Button returnButton = new Button(getApplicationContext());
                    //returnButton.setText("Return home");
                    //returnButton.setGravity(Gravity.CENTER);
                    //returnButton.setTextColor(0xFF000000);
                    //lp.addRule(RelativeLayout.BELOW, submit.getId());
                    //lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    //layout.addView(returnButton, lp);

                    //returnButton.setOnClickListener(new View.OnClickListener() {
                        //@Override
                        //public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), SearchAPI.class);
                            startActivity(intent);
                        //}
                    //});
                }
            }
        });
    }

    public void checkButton(View v) {
        int radioId = radio.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }
}