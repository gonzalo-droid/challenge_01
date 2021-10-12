package com.example.map_trips.data.util

import android.app.Application


class PreferenceApplication: Application() {
    companion object{
        lateinit var prefs: DataPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = DataPreference(applicationContext)
    }
}