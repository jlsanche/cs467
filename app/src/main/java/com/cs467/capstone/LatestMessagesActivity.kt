package com.cs467.capstone

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_latest_messages.*

class LatestMessagesActivity : AppCompatActivity() {

    companion object {

        var currentUser: User? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)

        fetchCurrentUser()

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

    private fun fetchCurrentUser() {
        val id = FirebaseAuth.getInstance().uid

        val ref = FirebaseDatabase.getInstance().getReference("/Users/$id")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                currentUser = p0.getValue(User::class.java)
                Log.d("latest Messages", "current user ${currentUser?.profileImageUrl}")


            }

        })
    }


    /*handle user select new message from menu event*/
    override fun onOptionsItemSelected(item: MenuItem?) : Boolean {

        when(item?.itemId) {
            R.id.menu_new_message -> {

                val intent = Intent(this, NewMessageActivity::class.java)  //take user to new msg screen
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)

    }

    /* display menu */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_messages, menu)

        return super.onCreateOptionsMenu(menu)

    }

}
