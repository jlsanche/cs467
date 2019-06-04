package com.cs467.capstone

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        recyclerview_chat_log.adapter = adapter

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

         supportActionBar?.title = user.username

        listenForMessages()

        send_button_chat_log.setOnClickListener {

            Log.d(TAG, "attempt to send msg")
            performSendMessage()
        }

    }

    private fun listenForMessages() {
        val ref = FirebaseDatabase.getInstance().getReference("/Messages")

        ref.addChildEventListener(object: ChildEventListener {
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
                val chatMessage = p0.getValue((ChatMessage::class.java))

                if(chatMessage != null) {

                    Log.d(TAG, chatMessage.text)


                    if(chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        adapter.add(ChatFromItem(chatMessage.text))

                    } else {

                        adapter.add(ChatToItem(chatMessage.text))

                    }


                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })

    }




    class ChatMessage(val id: String, val text: String, val fromId: String, val toId: String, val timestamp: Long) {

        constructor(): this("", "", "","", -1)
    }




    private fun performSendMessage() {

        val text = chat_log_edittext.text.toString()
        val fromId = FirebaseAuth.getInstance().uid

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user.id

        if(fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/Messages").push()

        val chatMassage = ChatMessage(reference.key!!,text,fromId, toId, System.currentTimeMillis() /1000 )
        reference.setValue(chatMassage).addOnSuccessListener {

            Log.d(TAG, "saved our chat msg: ${reference.key}")
        }


    }

    private fun dummyData() {

        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(ChatToItem("fuck "))
        adapter.add(ChatFromItem("you"))


        recyclerview_chat_log.adapter = adapter

    }
}



class ChatFromItem(val text:String): Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.text_from_View.text = text

    }

    override fun getLayout(): Int {

        return R.layout.chat_from_row

    }

}


class ChatToItem (val text:String): Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.text_to_View.text = text

    }

    override fun getLayout(): Int {

        return R.layout.chat_to_row

    }

}
