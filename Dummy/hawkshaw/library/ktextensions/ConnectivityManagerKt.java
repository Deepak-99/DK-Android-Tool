package com.hawkshaw.library.ktextensions;

import Y1.K;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class ConnectivityManagerKt {
    public static final boolean isOnMobileData(ConnectivityManager connectivityManager) {
        K.n(connectivityManager, "<this>");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static final boolean isOnWifi(ConnectivityManager connectivityManager) {
        K.n(connectivityManager, "<this>");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
