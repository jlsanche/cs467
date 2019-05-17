package com.example.testapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.*;
import java.util.*;
import java.util.Vector;
import org.w3c.dom.Text;


public class DisplayAllFood extends AppCompatActivity {

    Button seeAll;
    Button seeGood;
    Button seeOK;
    Button seeBad;

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

        final TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        context = getApplicationContext();
        final TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableCount = table.getChildCount();
                table.removeViews(1, tableCount - 1);

                db = FirebaseDatabase.getInstance().getReference("/Ingredients/");

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

                db = FirebaseDatabase.getInstance().getReference("/Ingredients/");

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

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

                db = FirebaseDatabase.getInstance().getReference("/Ingredients/");

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

                        for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                            items.add(String.valueOf(dsp.getValue()));

                            String name = String.valueOf(dsp.child("name").getValue());
                            String rating = String.valueOf(dsp.child("rating").getValue());

                            if(rating.equals("okay")) {
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

                db = FirebaseDatabase.getInstance().getReference("/Ingredients/");

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> items = new ArrayList<>();

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
}