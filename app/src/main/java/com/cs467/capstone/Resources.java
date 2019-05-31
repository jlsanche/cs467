package com.cs467.capstone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.*;

public class Resources extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        final Context context = getApplicationContext();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        final TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        //String[][] resourceList = new String[4][3];
        String[][] resourceList = {
                {"2012 ICN Food List" , "Interstitial Cystitis Network’s PDF about foods that are safe/bad for people with IC.", "https://www.ic-network.com/downloads/2012icnfoodlist.pdf" },
                {"Mayo Clinic on Interstitial Cystitis" , "Information about symptoms, causes, and treatments.", "https://www.mayoclinic.org/diseases-conditions/interstitial-cystitis/symptoms-causes/syc-20354357" },
                {"ICNetwork" , "With over 47,000 members, the IC Network is a good place to get support, and learn about diagnosis, treatments, diet, flares, pain care & much more. ", "https://www.ic-network.com/" },
                {"Interstitial Cystitis Association (ICA)" , "The Interstitial Cystitis Association (ICA) advocates for IC research for the cure and better treatments, raises awareness, and serves as a place for the healthcare providers, researchers, and patients who suffer with IC.", "https://www.ichelp.org/" }

        };

        for (int i = 0; i < resourceList.length; i++) {
            String title = (resourceList[i][0]);
            String blurb = (resourceList[i][1]);
            String link = (resourceList[i][2]);

            //title
            TableRow row = new TableRow(context);
            row.setLayoutParams(lp);

            TextView textView0 = new TextView(context);
            textView0.setText(title);
            textView0.setGravity(0);
            textView0.setPadding(10, 10, 5, 5);
            textView0.setTextColor(0xFF000000);
            row.addView(textView0, 0);

            table.addView(row);

            //blurb
            TableRow row1 = new TableRow(context);
            row1.setLayoutParams(lp);

            TextView textView1 = new TextView(context);
            textView1.setText(blurb);
            textView1.setGravity(0);
            textView1.setPadding(5, 5, 5, 5);
            textView1.setTextColor(0xFF000000);
            row1.addView(textView1, 0);

            table.addView(row1);

            //link
            TableRow row2 = new TableRow(context);
            row2.setLayoutParams(lp);

            TextView textView2 = new TextView(context);
            textView2.setText(link);
            Linkify.addLinks(textView2, Linkify.WEB_URLS);
            textView2.setGravity(0);
            textView2.setPadding(5, 5, 5, 20);
            textView2.setTextColor(0xFF000000);
            row2.addView(textView2, 0);

            table.addView(row2);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_resources, menu);
        //return super.onCreateOptionsMenu(menu_home);
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
            case R.id.food:
                Intent rated_food = new Intent(this, DisplayAllFood.class);
                startActivity(rated_food);
                break;
            case R.id.chat:
                Toast.makeText( getApplicationContext(),"chat forum", Toast.LENGTH_SHORT).show();
                //Intent chat = new Intent(this, Chat.class);
                //startActivity(chat);
                break;
            default:

        }

        //return super.onOptionsItemSelected(item);
        return true;
    }
}

