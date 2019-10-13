package com.example.desafiopitang.util

import android.content.Context
import android.net.ConnectivityManager

object InternetUtil {
    fun isConnected(ctx : Context) : Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo

        return ni != null && ni.isConnectedOrConnecting
    }
}