package com.example.map_trips.data.util

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.map_trips.R

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    @SuppressLint("RestrictedApi")
    override fun onReceive(context: Context, intent: Intent) {
        val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = conn.activeNetworkInfo
        var statusConnection = ""

        if (networkInfo?.type == ConnectivityManager.TYPE_WIFI) {
            statusConnection = context.getString(R.string.wifi_connected)
            Log.i("CONNECTIVITY_SERVICE",context.getString(R.string.wifi_connected) )
        } else if (networkInfo != null) {
            statusConnection = context.getString(R.string.data_connected)
            Log.i("CONNECTIVITY_SERVICE", context.getString(R.string.data_connected) )
        } else {
            statusConnection = context.getString(R.string.lost_connection)
            Log.i("CONNECTIVITY_SERVICE", context.getString(R.string.lost_connection) )
        }

        PreferenceApplication.prefs.saveConnection(statusConnection)
    }


    /*override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }*/
}
