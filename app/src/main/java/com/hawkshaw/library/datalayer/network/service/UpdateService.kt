package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.network.InstallReportRequest
import com.hawkshaw.library.datalayer.network.UpdateCheckRequest
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface UpdateService {
    
    @POST("app-update/check")
    suspend fun checkUpdate(@Body request: UpdateCheckRequest): Response<JSONObject>
    
    @GET
    suspend fun downloadUpdate(@Url downloadUrl: String): Response<ResponseBody>
    
    @POST("app-update/install-report")
    suspend fun reportInstall(@Body request: InstallReportRequest): Response<JSONObject>
}
