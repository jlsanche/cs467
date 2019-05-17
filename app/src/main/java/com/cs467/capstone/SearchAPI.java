package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class SearchAPI extends AppCompatActivity {

    EditText searchBar;
    Button searchButton;
    TextView responseResult;
    //Toolbar menu = (Toolbar) findViewById(R.id.toolbar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_api);

        final Context context = getApplicationContext();

        searchBar = (EditText) findViewById(R.id.search);
        searchButton = (Button) findViewById(R.id.btnSearch);

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

   public void buttonClicked(String food) {
       Intent intent = new Intent(this, AddFoodActivity.class);
       intent.putExtra("value", food);
       startActivity(intent);
    }
}