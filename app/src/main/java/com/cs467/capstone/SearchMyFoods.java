package com.cs467.capstone;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.*;

public class SearchMyFoods extends AppCompatActivity {

    EditText searchBar;
    Button searchButton;
    protected DatabaseReference db;
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_my_foods);

        searchBar = (EditText) findViewById(R.id.search);
        searchButton = (Button) findViewById(R.id.btnSearch);

        final TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        context = getApplicationContext();
        final TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = searchBar.getText().toString();

                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference("/Ingredients/").child(value);

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // no search results
                        if(dataSnapshot.child("name").getValue() ==  null) {
                            TextView noResult = findViewById(R.id.noResult);
                            noResult.setText("No result");
                        }
                        // item is found
                        else {
                            String name = dataSnapshot.child("name").getValue().toString();
                            String rating = dataSnapshot.child("rating").getValue().toString();

                            TableRow row2 = new TableRow(context);
                            row2.setLayoutParams(lp);

                            TextView textView00 = new TextView(context);
                            textView00.setText(name);
                            textView00.setGravity(0);
                            textView00.setPadding(60, 5, 20, 5);
                            row2.addView(textView00, 0);

                            TextView textView22 = new TextView(context);
                            textView22.setText(rating);
                            textView22.setGravity(0);
                            textView22.setPadding(60, 5, 20, 5);
                            row2.addView(textView22, 1);

                            table.addView(row2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });
    }
}