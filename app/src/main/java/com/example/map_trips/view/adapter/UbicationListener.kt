package com.example.map_trips.view.adapter

import com.example.map_trips.data.model.entity.Ubication

interface UbicationListener {
    fun onUbicationCliked(ubication: Ubication, position:Int)
}