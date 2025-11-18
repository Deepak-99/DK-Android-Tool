package com.hawkshaw.library.config;

import W0.i;
import X1.C0082b;
import Y1.C0110h;
import android.content.Context;
import android.util.Log;
import com.google.firebase.messaging.C0340i;
import com.hawkshaw.library.fcm.Firebase;
import com.hawkshaw.library.ktextensions.MapKt;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import k0.C0667g;
import kotlinx.serialization.json.b;
import n1.C0733a;
import n1.C0734b;
import o1.C0797e;
import o1.C0798f;
import o1.C0801i;
import o1.C0804l;
import org.json.JSONException;
import org.json.JSONObject;
import p3.q;
import r.p;

public final class RemoteConfig {
    public static final RemoteConfig INSTANCE = new RemoteConfig();

    private RemoteConfig() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long getLong(java.lang.String r7) {
        
            r6 = this;
            com.hawkshaw.library.fcm.Firebase r0 = com.hawkshaw.library.fcm.Firebase.INSTANCE
            n1.b r0 = r0.getConfig()
            o1.j r0 = r0.f7800g
            o1.d r1 = r0.f8230c
            o1.f r2 = o1.C0802j.b(r1)
            r3 = 0
            if (r2 != 0) goto L_0x0013
        L_0x0011:
            r2 = r3
            goto L_0x001d
        L_0x0013:
            org.json.JSONObject r2 = r2.f8203b     // Catch:{ JSONException -> 0x0011 }
            long r4 = r2.getLong(r7)     // Catch:{ JSONException -> 0x0011 }
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ JSONException -> 0x0011 }
        L_0x001d:
            if (r2 == 0) goto L_0x002b
            o1.f r1 = o1.C0802j.b(r1)
            r0.a(r1, r7)
            long r0 = r2.longValue()
            goto L_0x004c
        L_0x002b:
            o1.d r0 = r0.f8231d
            o1.f r0 = o1.C0802j.b(r0)
            if (r0 != 0) goto L_0x0034
            goto L_0x003e
        L_0x0034:
            org.json.JSONObject r0 = r0.f8203b     // Catch:{ JSONException -> 0x003e }
            long r0 = r0.getLong(r7)     // Catch:{ JSONException -> 0x003e }
            java.lang.Long r3 = java.lang.Long.valueOf(r0)     // Catch:{ JSONException -> 0x003e }
        L_0x003e:
            if (r3 == 0) goto L_0x0045
            long r0 = r3.longValue()
            goto L_0x004c
        L_0x0045:
            java.lang.String r0 = "Long"
            o1.C0802j.c(r7, r0)
            r0 = 0
        L_0x004c:
            return r0
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.config.RemoteConfig.getLong(java.lang.String):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getString(java.lang.String r5) {
        
            r4 = this;
            com.hawkshaw.library.fcm.Firebase r0 = com.hawkshaw.library.fcm.Firebase.INSTANCE
            n1.b r0 = r0.getConfig()
            o1.j r0 = r0.f7800g
            o1.d r1 = r0.f8230c
            o1.f r2 = o1.C0802j.b(r1)
            r3 = 0
            if (r2 != 0) goto L_0x0013
        L_0x0011:
            r2 = r3
            goto L_0x0019
        L_0x0013:
            org.json.JSONObject r2 = r2.f8203b     // Catch:{ JSONException -> 0x0011 }
            java.lang.String r2 = r2.getString(r5)     // Catch:{ JSONException -> 0x0011 }
        L_0x0019:
            if (r2 == 0) goto L_0x0023
            o1.f r1 = o1.C0802j.b(r1)
            r0.a(r1, r5)
            goto L_0x003d
        L_0x0023:
            o1.d r0 = r0.f8231d
            o1.f r0 = o1.C0802j.b(r0)
            if (r0 != 0) goto L_0x002c
            goto L_0x0032
        L_0x002c:
            org.json.JSONObject r0 = r0.f8203b     // Catch:{ JSONException -> 0x0032 }
            java.lang.String r3 = r0.getString(r5)     // Catch:{ JSONException -> 0x0032 }
        L_0x0032:
            if (r3 == 0) goto L_0x0036
            r2 = r3
            goto L_0x003d
        L_0x0036:
            java.lang.String r0 = "String"
            o1.C0802j.c(r5, r0)
            java.lang.String r2 = ""
        L_0x003d:
            return r2
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.config.RemoteConfig.getString(java.lang.String):java.lang.String");
    }

    public final String getBaseUrl() {
        return getString("http_base_url");
    }

    public final long getDelayAfterLoginMs() {
        return getLong("delay_after_login_ms");
    }

    public final long getExceptionCounterResetDelayInMillis() {
        return getLong("exception_handler_exception_counter_reset_delay_in_millis");
    }

    public final String getGrpcName() {
        return getString("grpc_channel_name");
    }

    public final int getGrpcPort() {
        return (int) getLong("grpc_channel_port");
    }

    public final long getMaxFrequentExceptionAllowedCount() {
        return getLong("exception_handler_max_frequent_exception_allowed_count");
    }

    public final long getPushLogsThreshold() {
        return getLong("push_logs_threshold");
    }

    public final String getPushyAppId() {
        return getString("pushy_app_id");
    }

    public final String getTusEndpoint() {
        return getString("tus_endpoint");
    }

    public final String getWebsocketUri() {
        return getString("web_socket_uri");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [X1.b, java.lang.Object] */
    public final void init() {
        i iVar = i.f2321D;
        C0734b config = Firebase.INSTANCE.getConfig();
        ? obj = new Object();
        obj.f2487a = C0801i.f8217i;
        long seconds = TimeUnit.HOURS.toSeconds(1);
        if (seconds >= 0) {
            obj.f2487a = seconds;
            p pVar = new p((C0082b) obj, 0);
            config.getClass();
            C0340i iVar2 = new C0340i(1, config, pVar);
            Executor executor = config.f7795b;
            q.x(executor, iVar2);
            Map<String, b> defaultRemoteConfig = ConfigKt.getConfig$default((Context) null, 1, (Object) null).getDefaultRemoteConfig();
            LinkedHashMap linkedHashMap = new LinkedHashMap(C0110h.B(defaultRemoteConfig.size()));
            for (Map.Entry next : defaultRemoteConfig.entrySet()) {
                linkedHashMap.put(next.getKey(), MapKt.parseValue((b) next.getValue()));
            }
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof byte[]) {
                    hashMap.put((String) entry.getKey(), new String((byte[]) value));
                } else {
                    hashMap.put((String) entry.getKey(), value.toString());
                }
            }
            try {
                C0797e c5 = C0798f.c();
                c5.f8195a = new JSONObject(hashMap);
                config.f7798e.c(c5.a()).l(iVar, new S0.b(3));
            } catch (JSONException e5) {
                Log.e("FirebaseRemoteConfig", "The provided defaults map could not be processed.", e5);
                q.i0((Object) null);
            }
            C0801i iVar3 = config.f7799f;
            C0804l lVar = iVar3.f8225g;
            lVar.getClass();
            long j5 = lVar.f8236a.getLong("minimum_fetch_interval_in_seconds", C0801i.f8217i);
            HashMap hashMap2 = new HashMap(iVar3.f8226h);
            hashMap2.put("X-Firebase-RC-Fetch-Type", "BASE/1");
            iVar3.f8223e.b().e(iVar3.f8221c, new C0667g(iVar3, j5, hashMap2)).l(iVar, new S0.b(4)).l(executor, new C0733a(config));
            return;
        }
        throw new IllegalArgumentException("Minimum interval between fetches has to be a non-negative number. " + seconds + " is an invalid argument");
    }
}
