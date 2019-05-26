package com.cs467.capstone;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class SearchAPI extends AppCompatActivity {

    EditText searchBar;
    Button searchButton;
    TextView responseResult;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_api);

        final Context context = getApplicationContext();

        searchBar = (EditText) findViewById(R.id.search);
        searchButton = (Button) findViewById(R.id.btnSearch);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        final TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableCount = table.getChildCount();
                table.removeViews(0, tableCount);

                String value = searchBar.getText().toString();
                String space = " ";
                String newSpace = "%20";

                RequestQueue myQueue = Volley.newRequestQueue(context);
                String urlString = value.replaceAll(" ", "%20");
                final String url = "https://api.edamam.com/api/food-database/parser?ingr=" + urlString + "&app_id=4915a2bc&app_key=edc6cb09de04b49c238560207f6883ef";

                StringRequest MyStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson g = new Gson();
                        FoodObj j = g.fromJson(response, FoodObj.class);

                        for(int i = 0; i < j.hints.length; i++) {
                            String name = (j.hints[i].food.label);

                            TableRow row = new TableRow(context);
                            row.setLayoutParams(lp);

                            TextView textView0 = new TextView(context);
                            textView0.setText(name);
                            textView0.setGravity(0);
                            textView0.setPadding(60, 5, 20, 5);
                            textView0.setTextColor(0xFF000000);
                            row.addView(textView0, 0);

                            Button addButton = new Button(context);
                            addButton.setText("add");
                            addButton.setGravity(Gravity.CENTER);
                            addButton.setTextColor(0xFF000000);
                            row.addView(addButton, 1);

                            table.addView(row);

                            final int final_row = i;

                            addButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TableRow row = (TableRow) table.getChildAt(final_row);
                                    TextView button = (TextView) row.getChildAt(0);
                                    String food_name = (String) button.getText();
                                    buttonClicked(food_name);
                                }
                            });
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseResult.setText(url);
                    }
                });
                myQueue.add(MyStringRequest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        //return super.onCreateOptionsMenu(menu_home);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:

                Intent settings = new Intent(SearchAPI.this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.profile:

                Intent profile = new Intent(SearchAPI.this, ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.food:
                Intent rated_food = new Intent(this, DisplayAllFood.class);
                startActivity(rated_food);
                break;
            case R.id.chat:
                Toast.makeText( getApplicationContext(),"chat forum", Toast.LENGTH_SHORT).show();
                Intent chat = new Intent(this, LatestMessagesActivity.class);
                startActivity(chat);
                break;
            case R.id.tips:
                Toast.makeText( getApplicationContext(),"tips", Toast.LENGTH_SHORT).show();
                //Intent tips = new Intent(this, Profile.class);
                //startActivity(tips);


                break;
            default:

        }

        //return super.onOptionsItemSelected(item);
        return true;
    }

    public void buttonClicked(String food) {
       Intent intent = new Intent(this, AddFoodActivity.class);
       intent.putExtra("value", food);
       startActivity(intent);
    }
}