package com.cs467.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.*;

import java.util.ArrayList;


public class DisplayAllFood extends AppCompatActivity {

    Button seeAll;
    Button seeGood;
    Button seeOK;
    Button seeBad;
    Toolbar toolbar;

    protected DatabaseReference db;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_food);

        seeAll = (Button) findViewById(R.id.buttonAll);
        seeGood = (Button) findViewById(R.id.buttonGood);
        seeOK = (Button) findViewById(R.id.buttonOK);
        seeBad = (Button) findViewById(R.id.buttonBad);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        context = getApplicationContext();
        final TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

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
                            table.addView(row);
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

                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("Good")) {
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
                                table.addView(row);
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

                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("Okay")) {
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
                                table.addView(row);
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

                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("Bad")) {
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
                                table.addView(row);
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
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                //Toast.makeText( getApplicationContext(),"settings", Toast.LENGTH_SHORT).show();
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.profile:
                //Toast.makeText( getApplicationContext(),"profile", Toast.LENGTH_SHORT).show();
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

        //return super.onOptionsItemSelected(item);
        return true;
    }
}