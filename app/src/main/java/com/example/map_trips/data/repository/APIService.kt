package com.example.map_trips.data.repository


import com.example.map_trips.data.model.DataModel
import com.example.map_trips.data.model.PredictionUbicationModel
import com.example.map_trips.data.model.UbicationDetailModel
import com.example.map_trips.data.model.entity.PredictionUbication
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getDataUbication(@Url url:String): Response<DataModel>


    @GET
    suspend fun getDataGooglePlaceByName(@Url url:String): Response<PredictionUbicationModel>


    @GET
    suspend fun getDataGooglePlaceDetailByID(@Url url:String): Response<UbicationDetailModel>
}