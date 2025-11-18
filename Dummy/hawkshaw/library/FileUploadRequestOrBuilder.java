package com.hawkshaw.library;

import com.google.protobuf.C0369n0;
import com.google.protobuf.C0371o0;
import com.hawkshaw.library.FileUploadRequest;

public interface FileUploadRequestOrBuilder extends C0371o0 {
    /* synthetic */ C0369n0 getDefaultInstanceForType();

    FileUploadRequest.File getFile();

    FileUploadRequest.MetaData getMetadata();

    FileUploadRequest.RequestCase getRequestCase();

    boolean hasFile();

    boolean hasMetadata();

    /* synthetic */ boolean isInitialized();
}
