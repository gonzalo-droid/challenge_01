package com.example.map_trips.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.map_trips.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configNav()
    }

    fun configNav(){
        val navMenu = findViewById<BottomNavigationView>(R.id.nav_menu)
        NavigationUI.setupWithNavController(navMenu,
            Navigation.findNavController(this, R.id.nav_host_fragment))
    }


}