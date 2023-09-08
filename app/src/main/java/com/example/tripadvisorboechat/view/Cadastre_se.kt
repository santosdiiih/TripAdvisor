package com.example.tripadvisorboechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tripadvisorboechat.R
import kotlinx.android.synthetic.main.toolbar.*

class Cadastre_se : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastre_se)

        insertToolbar()
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Cadastre-se"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}