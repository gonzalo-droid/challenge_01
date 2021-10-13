package com.example.map_trips.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.map_trips.R
import com.example.map_trips.data.model.entity.PredictionUbication

class UbicationAdapter(private val listUbicationPrediction:ArrayList<PredictionUbication>): RecyclerView.Adapter<UbicationAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ubication, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = listUbicationPrediction[position]
        holder.tvItemUbication.text = item.description
    }

    override fun getItemCount(): Int = listUbicationPrediction.size

    class  ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val tvItemUbication = itemView.findViewById<TextView>(R.id.tvItemUbication)
    }


}


