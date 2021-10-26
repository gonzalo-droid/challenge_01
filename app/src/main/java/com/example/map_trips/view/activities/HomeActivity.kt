package com.example.map_trips.view.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.map_trips.R
import com.example.map_trips.data.util.MyBroadcastReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val DEBUG_TAG = "NetworkStatusExample"
    private val myBroadcastReceiver: BroadcastReceiver = MyBroadcastReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configNav()
        initReceiver()

    }

    fun initReceiver(){
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_MANAGE_NETWORK_USAGE)
        }
        registerReceiver(myBroadcastReceiver, filter)
    }

    fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }



    fun configNav(){
        val navMenu = findViewById<BottomNavigationView>(R.id.nav_menu)
        NavigationUI.setupWithNavController(navMenu,
            Navigation.findNavController(this, R.id.nav_host_fragment))
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }


}