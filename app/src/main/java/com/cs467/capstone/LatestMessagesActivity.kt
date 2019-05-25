package com.cs467.capstone

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_latest_messages.*

class LatestMessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)

        val toolbar = findViewById<Toolbar>(R.id.chatToolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)

        //make sure user is signed in
        val uid = FirebaseAuth.getInstance().uid
        if(uid == null) {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /* go back to previous activity */
        chatToolbar.setNavigationOnClickListener({ finish() })

    }

    override fun onOptionsItemSelected(item: MenuItem?) : Boolean {

        when(item?.itemId) {
            R.id.menu_new_message -> {

                val intent = Intent(this, NewMessageActivity::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_messages, menu)

        return super.onCreateOptionsMenu(menu)

    }


}
