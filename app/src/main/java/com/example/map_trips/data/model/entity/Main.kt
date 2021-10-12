package com.example.map_trips.data.model.entity

import com.google.gson.annotations.SerializedName


data class Main (

    @SerializedName("temp") val temp : Double,
    @SerializedName("humidity") val humidity : Int
)