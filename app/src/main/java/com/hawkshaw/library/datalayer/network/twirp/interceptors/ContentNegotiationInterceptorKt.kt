package com.hawkshaw.library.datalayer.network.twirp.interceptors

import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Content negotiation interceptor for HTTP client
 */
object ContentNegotiationInterceptorKt {
    /**
     * Shared JSON configuration
     */
    val json by lazy {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            prettyPrint = false
            coerceInputValues = true
        }
    }

    private const val JSON_MEDIA_TYPE = "application/json; charset=utf-8"

    /**
     * Installs content negotiation interceptor on the HTTP client
     */
    fun installContentNegotiationInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(ContentNegotiationInterceptor())
    }
    
    private class ContentNegotiationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            
            // Add JSON headers to request if not present
            val request = originalRequest.newBuilder()
                .apply {
                    if (originalRequest.header("Content-Type") == null) {
                        header("Content-Type", JSON_MEDIA_TYPE)
                    }
                    if (originalRequest.header("Accept") == null) {
                        header("Accept", JSON_MEDIA_TYPE)
                    }
                }
                .build()
            
            val response = chain.proceed(request)
            
            // Handle response if it's JSON
            return if (response.header("Content-Type")?.contains("application/json") == true) {
                response.newBuilder()
                    .body(response.body?.let { body ->
                        // Keep the response as is, just ensure proper media type
                        body.source().readString(Charsets.UTF_8)
                            .toByteArray()
                            .toResponseBody(JSON_MEDIA_TYPE.toMediaType())
                    })
                    .build()
            } else {
                response
            }
        }
    }
} 