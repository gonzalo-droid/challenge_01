package com.example.map_trips.data.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ubication(val id: String="",
                val name: String="",
                val photo: String="",
                val latitude: Double= 0.0,
                val longitude: Double=0.0,
                val humidity : String="",
                val temperature: String="",
                val clouds: String="",
                val wind:String ="",
                val icon : String =""

): Serializable




