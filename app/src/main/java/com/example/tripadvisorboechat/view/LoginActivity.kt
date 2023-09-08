package com.example.tripadvisorboechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tripadvisorboechat.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener(this)
        buttonNewUser.setOnClickListener(this)

        insertToolbar()
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Login"
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.buttonLogin){
            val openActivity = Intent(this, MainActivity::class.java);
            startActivity(openActivity)
        }
        else if(view.id == R.id.buttonNewUser){
            val openActivity = Intent(this, Cadastre_se::class.java)
            startActivity(openActivity)
        }
    }
}