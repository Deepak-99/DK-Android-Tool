package com.hawkshaw.library.ktextensions

import android.net.ConnectivityManager

/**
 * Utility functions for ConnectivityManager
 */

/**
 * Checks if the device is currently using mobile data
 */
fun ConnectivityManager.isOnMobileData(): Boolean {
    val networkInfo = this.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}

/**
 * Checks if the device is currently using WiFi
 */
fun ConnectivityManager.isOnWifi(): Boolean {
    val networkInfo = this.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    return networkInfo != null && networkInfo.isConnectedOrConnecting
} 