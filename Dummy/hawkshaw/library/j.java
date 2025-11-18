package com.hawkshaw.library;

import com.google.protobuf.O;
import com.hawkshaw.library.FileUploadResponse;

public final class j implements O {

    /* renamed from: a  reason: collision with root package name */
    public static final j f5045a = new Object();

    public final boolean a(int i5) {
        return FileUploadResponse.Status.forNumber(i5) != null;
    }
}
