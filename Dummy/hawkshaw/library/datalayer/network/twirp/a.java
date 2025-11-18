package com.hawkshaw.library.datalayer.network.twirp;

import A2.C0003d;
import A2.s;
import W2.y;
import Y1.J;
import Y1.K;
import com.hawkshaw.library.BuildConfig;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.AuthInterceptorKt;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentEncodingKt;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.LoggingInterceptorKt;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.RetryInterceptorKt;
import com.hawkshaw.library.deviceinfo.DeviceInfo;
import i2.g;
import i3.l;
import j3.j;
import java.util.List;
import p2.C0840f;
import p2.C0842h;

public final class a extends j implements l {

    /* renamed from: E  reason: collision with root package name */
    public static final a f4937E = new a(0);

    /* renamed from: F  reason: collision with root package name */
    public static final a f4938F = new a(1);

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ int f4939D;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i5) {
        super(1);
        this.f4939D = i5;
    }

    public final Object invoke(Object obj) {
        y yVar = y.f2418a;
        switch (this.f4939D) {
            case 0:
                C0840f fVar = (C0840f) obj;
                K.n(fVar, "$this$install");
                List list = s.f102a;
                J.r(fVar, "Content-Type", C0003d.f80a);
                J.r(fVar, "App-Id", DeviceInfo.INSTANCE.getAndroidId());
                J.r(fVar, "libv", Integer.valueOf(BuildConfig.VERSION_CODE));
                return yVar;
            default:
                g gVar = (g) obj;
                K.n(gVar, "$this$HttpClient");
                AuthInterceptorKt.installAuthInterceptor(gVar);
                LoggingInterceptorKt.installLoggingInterceptor(gVar);
                ContentNegotiationInterceptorKt.installContentNegotiationInterceptor(gVar);
                RetryInterceptorKt.installRetryInterceptor(gVar);
                ContentEncodingKt.installCustomContentEncoding(gVar);
                gVar.a(C0842h.f8438b, f4937E);
                return yVar;
        }
    }
}
