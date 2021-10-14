package com.example.map_trips.core

import android.util.Log
import com.example.map_trips.R
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    const val TOKEN = R.string.token_weather
    const val TOKEN_GOOGLE_PLACE = R.string.token_google_place

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