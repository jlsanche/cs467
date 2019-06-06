package com.cs467.capstone

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*

class ChatLogActivity : AppCompatActivity() {

    companion object {

        val TAG = "ChatLog"
    }

    val adapter = GroupAdapter<ViewHolder>()

    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        val toolbar = findViewById<Toolbar>(R.id.chat_log_toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)


        recyclerview_chat_log.adapter = adapter

        toUser = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

        supportActionBar?.title = toUser?.username

        listenForMessages()


        send_button_chat_log.setOnClickListener {

            Log.d(TAG, "attempt to send msg")
            performSendMessage()
        }

        /* go back to previous activity */
        chat_log_toolbar.setNavigationOnClickListener({ finish() })

    }

    private fun listenForMessages() {

        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.id

        val ref = FirebaseDatabase.getInstance().getReference("/User-Messages/$fromId/$toId")

        ref.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)

                if (chatMessage != null) {

                    Log.d(TAG, chatMessage.text)


                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {

                        val currentUser = LatestMessagesActivity.currentUser ?: return
                        adapter.add(ChatFromItem(chatMessage.text, currentUser))

                    } else {

                        adapter.add(ChatToItem(chatMessage.text, toUser!!))

                    }

                }

                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }


    class ChatMessage(val id: String, val text: String, val fromId: String, val toId: String, val timestamp: Long) {

        constructor() : this("", "", "", "", -1)
    }


    private fun performSendMessage() {

        val text = chat_log_edittext.text.toString()
        val fromId = FirebaseAuth.getInstance().uid

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user.id

        if (fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/User-Messages/$fromId/$toId").push()

        val toReference = FirebaseDatabase.getInstance().getReference("/User-Messages/$toId/$fromId").push()

        val chatMassage = ChatMessage(reference.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)
        reference.setValue(chatMassage).addOnSuccessListener {

            Log.d(TAG, "saved chat msg: ${reference.key}")
            chat_log_edittext.text.clear()
            recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)

        }

        toReference.setValue(chatMassage)

        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/Latest-Messages/$fromId/$toId")
        latestMessageRef.setValue(chatMassage)

        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/Latest-Messages/$toId/$fromId")

        latestMessageToRef.setValue(chatMassage)


    }

}


class ChatFromItem(val text: String, val user: User) : Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.text_from_View.text = text

        val targetImageView = viewHolder.itemView.imageView_from

        //load user image to msg thread
        val url = user.profileImageUrl
        Picasso.get().load(url).into(targetImageView)

    }

    override fun getLayout(): Int {

        return R.layout.chat_from_row

    }

}

class ChatToItem(val text: String, val user: User) : Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.text_to_view.text = text


        val targetImageView = viewHolder.itemView.imageView_to

        //load user image to msg thread
        val url = user.profileImageUrl
        Picasso.get().load(url).into(targetImageView)

    }

    override fun getLayout(): Int {

        return R.layout.chat_to_row

    }

}
