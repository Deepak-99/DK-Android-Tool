package com.hawkshaw.library.datalayer.network.grpc;

import Y1.C0105e0;
import Y1.C0107f0;
import Y1.C0109g0;
import Z1.d;
import a2.C0227u0;
import com.hawkshaw.library.App;
import com.hawkshaw.library.config.RemoteConfig;
import com.hawkshaw.library.datalayer.network.grpc.interceptors.AuthInterceptor;
import com.hawkshaw.library.datalayer.network.grpc.interceptors.LoggingInterceptor;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import me.pushy.sdk.lib.jackson.core.util.MinimalPrettyPrinter;

public final class GrpcChannelKt {
    private static final C0105e0 channel;

    static {
        RemoteConfig remoteConfig = RemoteConfig.INSTANCE;
        String grpcName = remoteConfig.getGrpcName();
        int grpcPort = remoteConfig.getGrpcPort();
        C0109g0 g0Var = d.f2983c;
        Logger logger = C0227u0.f3625a;
        try {
            d dVar = new d(new URI((String) null, (String) null, grpcName, grpcPort, (String) null, (String) null, (String) null).getAuthority());
            dVar.f2985b = App.Companion.getContext();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            C0107f0 f0Var = dVar.f2984a;
            f0Var.d(timeUnit);
            f0Var.e(timeUnit);
            dVar.f();
            dVar.i();
            if (remoteConfig.getGrpcPort() == 443) {
                dVar.h();
            } else {
                dVar.g();
            }
            f0Var.c(new AuthInterceptor(), new LoggingInterceptor(LoggingInterceptor.Level.HEADERS, LoggingInterceptor.Level.STATUS));
            channel = dVar.a();
        } catch (URISyntaxException e5) {
            throw new IllegalArgumentException("Invalid host or port: " + grpcName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + grpcPort, e5);
        }
    }

    public static final C0105e0 getChannel() {
        return channel;
    }
}
