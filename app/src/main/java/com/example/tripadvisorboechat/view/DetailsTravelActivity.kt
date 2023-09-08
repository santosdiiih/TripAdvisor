package com.example.tripadvisorboechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tripadvisorboechat.R
import com.example.tripadvisorboechat.model.Travel
import com.example.tripadvisorboechat.repository.TravelRepository
import kotlinx.android.synthetic.main.activity_details_travel.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailsTravelActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_travel)

        inflateForm()
        insertToolbar()
        toolbar.setOnClickListener(this)
    }

    private fun inflateForm() {
        var travel = Travel()
        val id = intent.getIntExtra("id", 0)

        val repository = TravelRepository(this)
        travel = repository.getTravel(id)

        dateGo.setText(travel.dateGo)
        dateback.setText(travel.dateBack)
        textOrigin.setText(travel.origin)
        textDestiny.setText(travel.destiny)
        textClassification.setText(travel.classification)
        textType.setText(travel.typeTrip)
        textAmount.setText(travel.amounttickets)
    }

    private fun insertToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Detalhes Da Viagem"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.toolbar){
            onBackPressed()
        }
    }
}