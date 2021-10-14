package com.example.map_trips.data.model

import com.google.gson.annotations.SerializedName

data class UbicationDetailModel(
    @SerializedName("result")  val result: Result,
    @SerializedName("status") val status: String
)

data class Result(
    val formatted_address: String,
    val geometry: Geometry,
    val name: String,
    val photos: List<Photo>,
    val place_id: String,
    val reference: String
)

data class Photo(
    val height: Int,
    val photo_reference: String,
    val width: Int
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Geometry(
    val location: Location
)