package com.example.map_trips.core

import android.util.Log
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    const val TOKEN = "2bc5b4ec3231b30f22de7eb0e1d54c08"
    const val TOKEN_GOOGLE_PLACE = "AIzaSyCnTp_9Ei1DvvVwqlz0Od8QvRN-unk6aBo"

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitGooglePlaceByName():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/autocomplete/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitGooglePlaceDetailByID():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/details/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitGooglePlaceByPhoto():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/autocomplete/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}