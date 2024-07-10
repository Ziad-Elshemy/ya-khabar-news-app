package com.example.newsapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.telecom.ConnectionService
import android.util.Log
import com.example.newsapp.database.MyDatabase

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.init(this)
        val networkHandler = object : NetworkHandler {
            override fun isOnline(): Boolean {
                return isNetworkAvailable()
            }
        }
        Constans.networkHandler = networkHandler
    }
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_BLUETOOTH")
                    return true
                }
            }
        }
        Log.i("Internet", "NetworkCapabilities.TRANSPORT_NO_INTERNET")
        return false
    }


}