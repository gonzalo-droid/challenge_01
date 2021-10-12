package com.example.map_trips.data.repository


import com.example.map_trips.data.model.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getDataUbication(@Url url:String): Response<DataModel>
}