package com.example.tripadvisorboechat.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripadvisorboechat.R
import com.example.tripadvisorboechat.adapter.TripAdapter.*
import com.example.tripadvisorboechat.model.Travel
import com.example.tripadvisorboechat.view.DetailsTravelActivity
import kotlinx.android.synthetic.main.card_trip.view.*

class TripAdapter(var listTrip: ArrayList<Travel>) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(travel: Travel){
            itemView.dateGo.text = travel.dateGo
            itemView.dateback.text = travel.dateBack
            itemView.textOrigin.text = travel.origin
            itemView.textDestiny.text = travel.destiny

            itemView.buttonDetails.setOnClickListener{
                val intent = Intent(itemView.context, DetailsTravelActivity::class.java)
                intent.putExtra("id", travel.idTravel)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_trip, parent, false)
        return TripViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val travel = listTrip[position]
        holder.bind(travel)

    }

    override fun getItemCount(): Int {
        return listTrip.size
    }
}