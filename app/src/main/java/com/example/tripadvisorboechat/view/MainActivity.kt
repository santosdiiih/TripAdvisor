package com.example.tripadvisorboechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tripadvisorboechat.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNewTravel.setOnClickListener(this)

        insertToolbar()
        toolbar.setOnClickListener(this)
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.buttonNewTravel){
            val open = Intent(this, NewTravel::class.java)
            startActivity(open)
        }
        else if(view.id == R.id.toolbar){
            onBackPressed()
        }
    }
}