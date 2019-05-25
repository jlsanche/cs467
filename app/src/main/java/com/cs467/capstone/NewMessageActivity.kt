package com.cs467.capstone

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_message.*

class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        newMessagetoolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)


        /* go back to previous activity */
        newMessagetoolbar.setNavigationOnClickListener({ finish() })



    }
}
