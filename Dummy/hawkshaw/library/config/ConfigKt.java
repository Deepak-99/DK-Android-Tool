package com.hawkshaw.library.config;

import android.content.Context;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Config;

public final class ConfigKt {
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0063, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0064, code lost:
        Y1.K.q(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.hawkshaw.library.datalayer.models.Config getConfig(android.content.Context r4) {
        /*
            java.lang.String r0 = "context"
            Y1.K.n(r4, r0)
            android.content.res.AssetManager r4 = r4.getAssets()
            java.lang.String r0 = "hawkshaw-config.json"
            java.io.InputStream r4 = r4.open(r0)
            java.lang.String r0 = "open(...)"
            Y1.K.m(r4, r0)
            java.nio.charset.Charset r0 = r3.C0931a.f8930a
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            r1.<init>(r4, r0)
            boolean r4 = r1 instanceof java.io.BufferedReader
            r0 = 8192(0x2000, float:1.148E-41)
            if (r4 == 0) goto L_0x0024
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            goto L_0x002a
        L_0x0024:
            java.io.BufferedReader r4 = new java.io.BufferedReader
            r4.<init>(r1, r0)
            r1 = r4
        L_0x002a:
            java.io.StringWriter r4 = new java.io.StringWriter     // Catch:{ all -> 0x0061 }
            r4.<init>()     // Catch:{ all -> 0x0061 }
            char[] r0 = new char[r0]     // Catch:{ all -> 0x0061 }
            int r2 = r1.read(r0)     // Catch:{ all -> 0x0061 }
        L_0x0035:
            if (r2 < 0) goto L_0x0040
            r3 = 0
            r4.write(r0, r3, r2)     // Catch:{ all -> 0x0061 }
            int r2 = r1.read(r0)     // Catch:{ all -> 0x0061 }
            goto L_0x0035
        L_0x0040:
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0061 }
            java.lang.String r0 = "toString(...)"
            Y1.K.m(r4, r0)     // Catch:{ all -> 0x0061 }
            r0 = 0
            Y1.K.q(r1, r0)
            F3.b r0 = com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.getJson()
            r0.getClass()
            com.hawkshaw.library.datalayer.models.Config$Companion r1 = com.hawkshaw.library.datalayer.models.Config.Companion
            kotlinx.serialization.KSerializer r1 = r1.serializer()
            java.lang.Object r4 = r0.a(r1, r4)
            com.hawkshaw.library.datalayer.models.Config r4 = (com.hawkshaw.library.datalayer.models.Config) r4
            return r4
        L_0x0061:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r0 = move-exception
            Y1.K.q(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.config.ConfigKt.getConfig(android.content.Context):com.hawkshaw.library.datalayer.models.Config");
    }

    public static /* synthetic */ Config getConfig$default(Context context, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        return getConfig(context);
    }
}
