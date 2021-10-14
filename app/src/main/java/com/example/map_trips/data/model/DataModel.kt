package com.example.map_trips.data.model

import com.example.map_trips.data.model.entity.*
import com.google.gson.annotations.SerializedName

data class DataModel (

    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("main") val main : Main,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int
)

