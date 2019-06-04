package com.cs467.capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso


import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*


class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        /*display back arrow on top left */
        newMessagetoolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)

        /* go back to previous activity */
        newMessagetoolbar.setNavigationOnClickListener({ finish() })

        supportActionBar?.title = "Select User"

        fetchUsers()

    }

    companion object {

        val USER_KEY = "USER_KEY"   // send username to chat log activity
    }

    private fun fetchUsers() {

        val ref = FirebaseDatabase.getInstance().getReference("/Users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    Log.d("newMsg", it.toString())
                    val user = it.getValue(User::class.java)
                    if(user != null) {
                        adapter.add(UserItem(user))
                    }

                }

                adapter.setOnItemClickListener { item, view ->

                    //cast user instance to intent to make avaiable to other activities
                    val userItem = item as UserItem
                    val intent = Intent(view.context, ChatLogActivity::class.java)
                    intent.putExtra(USER_KEY, userItem.user )
                    startActivity(intent)

                    finish()
                }

                recyclerView.adapter = adapter

            }

            override fun onCancelled(p0: DatabaseError) {


            }
        })
    }

}


class UserItem(val user : User) : Item<ViewHolder>() {

    //call in list for each user object
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textView3.text = user.username

        Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.imageView3)
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}


