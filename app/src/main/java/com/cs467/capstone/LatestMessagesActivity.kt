package com.cs467.capstone

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_messages.*
import kotlinx.android.synthetic.main.latest_messsage_row.view.*

class LatestMessagesActivity : AppCompatActivity() {

    companion object {

        var currentUser: User? = null
        val TAG = "Latest msgs. "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)

        recyclerView_latestMessages.adapter = adapter
        recyclerView_latestMessages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        fetchCurrentUser()

        listenForLatestMessages()

        val toolbar = findViewById<Toolbar>(R.id.chatToolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)

        adapter.setOnItemClickListener { item, view ->
            Log.d(TAG, "123")

            val row = item as LatestMessageRow


            val intent = Intent(this, ChatLogActivity::class.java)
            intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUSer )
            startActivity(intent)

        }

        //make sure user is signed in
        val uid = FirebaseAuth.getInstance().uid
        if(uid == null) {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        /* go back to previous activity */
        chatToolbar.setNavigationOnClickListener({ finish() })


    }



    class LatestMessageRow(val chatMessage: ChatLogActivity.ChatMessage): Item<ViewHolder>() {

        var chatPartnerUSer: User? = null

        override fun getLayout(): Int {

            return R.layout.latest_messsage_row


        }

        override fun bind(viewHolder: ViewHolder, position: Int) {

            val chatPartnerId: String

            if(chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                chatPartnerId = chatMessage.toId
            } else {

                chatPartnerId = chatMessage.fromId
            }

            val ref = FirebaseDatabase.getInstance().getReference("/Users/$chatPartnerId")
            ref.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    chatPartnerUSer = p0.getValue(User::class.java)
                    viewHolder.itemView.username_tv_latest_msg.text = chatPartnerUSer?.username

                    val targetImageView = viewHolder.itemView.img_latest_msg
                    Picasso.get().load(chatPartnerUSer?.profileImageUrl).into(targetImageView)
                }


            })

            viewHolder.itemView.message_tv_latest_msg.text = chatMessage.text


        }

    }


    val latestMessagesMap = HashMap<String, ChatLogActivity.ChatMessage>()


    //utility fn. to clear old msgs. and populate current chats view  with latest messages
    private fun refreshRecyclerViewMessages() {

        adapter.clear()

        latestMessagesMap.values.forEach {
            adapter.add(LatestMessageRow(it))
        }



    }



    //populates chat threads with latest messages
    private fun listenForLatestMessages() {

        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/Latest-Messages/$fromId")
        ref.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                val chatMessage = p0.getValue(ChatLogActivity.ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()



            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                val chatMessage = p0.getValue(ChatLogActivity.ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()

            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


    }

    val adapter = GroupAdapter<ViewHolder>()

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
