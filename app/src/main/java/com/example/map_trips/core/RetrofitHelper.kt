package com.example.map_trips.core

import android.util.Log
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    val TOKEN = "2bc5b4ec3231b30f22de7eb0e1d54c08"

    fun getRetrofit():Retrofit{

//        val url = HttpUrl.Builder()
//            .scheme("http")
//            .host("api.openweathermap.org")
//            .addPathSegment("data")
//            .addPathSegment("2.5")
//
//            .build()

//        Log.d("url", url.toString())

        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
//            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}