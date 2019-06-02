package com.cs467.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class DisplayAllFood extends AppCompatActivity {

    Button seeAll;
    Button seeGood;
    Button seeOK;
    Button seeBad;
    Toolbar toolbar;
    //EditText searchBar;
    //Button searchButton;

    protected DatabaseReference db;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_food);

        //searchBar = (EditText) findViewById(R.id.search);
        //searchButton = (Button) findViewById(R.id.btnSearch);
        seeAll = (Button) findViewById(R.id.buttonAll);
        seeGood = (Button) findViewById(R.id.buttonGood);
        seeOK = (Button) findViewById(R.id.buttonOK);
        seeBad = (Button) findViewById(R.id.buttonBad);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        context = getApplicationContext();
        final TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        /*
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String value = searchBar.getText().toString();
                final String valueLower = value.toLowerCase();

                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference();

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // no search results
                        if(dataSnapshot.child("name").getValue() ==  null) {
                            TableRow row2 = new TableRow(context);
                            row2.setLayoutParams(lp);

                            TextView textView00 = new TextView(context);
                            textView00.setText("No result");
                            textView00.setGravity(0);
                            textView00.setPadding(60, 5, 20, 5);
                            row2.addView(textView00, 0);

                            table.addView(row2);
                        }
                        // item is found
                        else {
                            String name = String.valueOf(dataSnapshot.child("name").getValue());
                            String rating = String.valueOf(dataSnapshot.child("rating").getValue());

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

         */

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference();

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

                        int i = 1;
                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            TableRow row = new TableRow(context);
                            row.setLayoutParams(lp);

                            TextView textView0 = new TextView(context);
                            textView0.setText(name);
                            textView0.setGravity(0);
                            textView0.setPadding(60, 5, 20, 5);
                            textView0.setTextColor(0xFF000000);
                            row.addView(textView0, 0);

                            TextView textView1 = new TextView(context);
                            textView1.setText(rating);
                            textView1.setGravity(0);
                            textView1.setPadding(60, 5, 20, 5);
                            textView1.setTextColor(0xFF000000);
                            row.addView(textView1, 1);

                            Button addButton = new Button(context);
                            addButton.setText("delete");
                            addButton.setGravity(Gravity.CENTER);
                            addButton.setTextColor(0xFF000000);
                            row.addView(addButton, 2);

                            table.addView(row);

                            final int final_row = i;

                            addButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TableRow row = (TableRow) table.getChildAt(final_row);
                                    TextView button = (TextView) row.getChildAt(0);
                                    String food_name = (String) button.getText();
                                    buttonClicked(food_name, db);
                                }
                            });

                            i++;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });

        seeGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference();

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

                        int i = 1;
                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("good")) {
                                TableRow row = new TableRow(context);
                                row.setLayoutParams(lp);

                                TextView textView0 = new TextView(context);
                                textView0.setText(name);
                                textView0.setGravity(0);
                                textView0.setPadding(60, 5, 20, 5);
                                textView0.setTextColor(0xFF000000);
                                row.addView(textView0, 0);

                                TextView textView1 = new TextView(context);
                                textView1.setText(rating);
                                textView1.setGravity(0);
                                textView1.setPadding(60, 5, 20, 5);
                                textView1.setTextColor(0xFF000000);
                                row.addView(textView1, 1);

                                Button addButton = new Button(context);
                                addButton.setText("delete");
                                addButton.setGravity(Gravity.CENTER);
                                addButton.setTextColor(0xFF000000);
                                row.addView(addButton, 2);

                                table.addView(row);

                                final int final_row = i;

                                addButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TableRow row = (TableRow) table.getChildAt(final_row);
                                        TextView button = (TextView) row.getChildAt(0);
                                        String food_name = (String) button.getText();
                                        buttonClicked(food_name, db);
                                    }
                                });

                                i++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });

        seeOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference();

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

                        int i = 1;
                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("fair")) {
                                TableRow row = new TableRow(context);
                                row.setLayoutParams(lp);

                                TextView textView0 = new TextView(context);
                                textView0.setText(name);
                                textView0.setGravity(0);
                                textView0.setPadding(60, 5, 20, 5);
                                textView0.setTextColor(0xFF000000);
                                row.addView(textView0, 0);

                                TextView textView1 = new TextView(context);
                                textView1.setText(rating);
                                textView1.setGravity(0);
                                textView1.setPadding(60, 5, 20, 5);
                                textView1.setTextColor(0xFF000000);
                                row.addView(textView1, 1);

                                Button addButton = new Button(context);
                                addButton.setText("delete");
                                addButton.setGravity(Gravity.CENTER);
                                addButton.setTextColor(0xFF000000);
                                row.addView(addButton, 2);

                                table.addView(row);

                                final int final_row = i;

                                addButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TableRow row = (TableRow) table.getChildAt(final_row);
                                        TextView button = (TextView) row.getChildAt(0);
                                        String food_name = (String) button.getText();
                                        buttonClicked(food_name, db);
                                    }
                                });

                                i++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });

        seeBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference();

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

                        int i = 1;
                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("bad")) {
                                TableRow row = new TableRow(context);
                                row.setLayoutParams(lp);

                                TextView textView0 = new TextView(context);
                                textView0.setText(name);
                                textView0.setGravity(0);
                                textView0.setPadding(60, 5, 20, 5);
                                textView0.setTextColor(0xFF000000);
                                row.addView(textView0, 0);

                                TextView textView1 = new TextView(context);
                                textView1.setText(rating);
                                textView1.setGravity(0);
                                textView1.setPadding(60, 5, 20, 5);
                                textView1.setTextColor(0xFF000000);
                                row.addView(textView1, 1);

                                Button addButton = new Button(context);
                                addButton.setText("delete");
                                addButton.setGravity(Gravity.CENTER);
                                addButton.setTextColor(0xFF000000);
                                row.addView(addButton, 2);

                                table.addView(row);

                                final int final_row = i;

                                addButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TableRow row = (TableRow) table.getChildAt(final_row);
                                        TextView button = (TextView) row.getChildAt(0);
                                        String food_name = (String) button.getText();
                                        buttonClicked(food_name, db);
                                    }
                                });

                                i++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_rated_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.home:
                Intent home = new Intent(this, SearchAPI.class);
                startActivity(home);
                break;
            case R.id.chat:
                Toast.makeText( getApplicationContext(),"chat forum", Toast.LENGTH_SHORT).show();
                //Intent chat = new Intent(this, Chat.class);
                //startActivity(chat);
                break;
            case R.id.tips:
                Toast.makeText( getApplicationContext(),"resources", Toast.LENGTH_SHORT).show();
                Intent tips = new Intent(this, Resources.class);
                startActivity(tips);
                break;
            default:

        }
        return true;
    }

    public void buttonClicked(String food_name, DatabaseReference db) {
        db.child(food_name).setValue(null);
        Toast toast = Toast.makeText( getApplicationContext(),"Food deleted!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}