package com.example.map_trips.view.adapter

import com.example.map_trips.data.model.entity.PredictionUbication

interface UbicationListener {
    fun onUbicationCliked(ubication: PredictionUbication, position:Int)

}