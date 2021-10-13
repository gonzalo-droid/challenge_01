package com.example.map_trips.data.model

import com.example.map_trips.data.model.entity.PredictionUbication
import com.google.gson.annotations.SerializedName

data class PredictionUbicationModel(
    @SerializedName("predictions") val predictions: List<PredictionUbication>,
    @SerializedName("status") val status: String
)