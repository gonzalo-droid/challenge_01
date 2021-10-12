package com.example.map_trips.data.util

import android.content.Context

class DataPreference(val context:Context) {
    val SHARE_DATA = "MyData"

    val SHARE_NAME = "name"
    val SHARE_LASTNAME = "lastname"
    val SHARE_EMAIL = "email"
    val SHARE_PHONE = "phone"
    val SHARE_DATE = "date"

    val storage = context.getSharedPreferences(SHARE_DATA, 0)

    fun saveName(name:String){
        storage.edit().putString(SHARE_NAME, name).apply()
    }

    fun saveLastName(lastname:String){
        storage.edit().putString(SHARE_LASTNAME, lastname).apply()
    }

    fun saveEmail(email:String){
        storage.edit().putString(SHARE_EMAIL, email).apply()
    }

    fun savePhone(phone:String){
        storage.edit().putString(SHARE_PHONE, phone).apply()
    }

    fun saveDate(date:String){
        storage.edit().putString(SHARE_DATE, date).apply()
    }

    fun getName():String{
        return storage.getString(SHARE_NAME, "")!!
    }

    fun getLastName():String{
        return storage.getString(SHARE_LASTNAME, "")!!
    }

    fun getEmail():String{
        return storage.getString(SHARE_EMAIL, "")!!
    }

    fun getPhone():String{
        return storage.getString(SHARE_PHONE, "")!!
    }

    fun getDate():String{
        return storage.getString(SHARE_DATE, "")!!
    }
    fun resetData(){
        storage.edit().clear().apply()
    }
}