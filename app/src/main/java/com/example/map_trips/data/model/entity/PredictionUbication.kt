package com.example.map_trips.data.model.entity

import com.google.gson.annotations.SerializedName

data class PredictionUbication(

    @SerializedName("description") val description: String,
    @SerializedName("place_id") val place_id: String
)