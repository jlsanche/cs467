package com.cs467.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*

class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        /*display back arrow on top left */
        newMessagetoolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)


        /* go back to previous activity */
        newMessagetoolbar.setNavigationOnClickListener({ finish() })


        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(UserItem())
        adapter.add(UserItem())
        adapter.add(UserItem())



        recyclerView.adapter = adapter


    }
}


class UserItem : Item<ViewHolder>() {

    //call in list for each user object
    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {

        return R.layout.user_row_new_message


    }


}