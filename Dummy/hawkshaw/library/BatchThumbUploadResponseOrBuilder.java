package com.hawkshaw.library;

import com.google.protobuf.C0369n0;
import com.google.protobuf.C0371o0;
import com.hawkshaw.library.BatchThumbUploadResponse;
import java.util.List;

public interface BatchThumbUploadResponseOrBuilder extends C0371o0 {
    /* synthetic */ C0369n0 getDefaultInstanceForType();

    BatchThumbUploadResponse.Thumb getSuccess(int i5);

    int getSuccessCount();

    List<BatchThumbUploadResponse.Thumb> getSuccessList();

    /* synthetic */ boolean isInitialized();
}
