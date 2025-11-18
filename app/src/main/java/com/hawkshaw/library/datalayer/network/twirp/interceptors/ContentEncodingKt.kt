package com.hawkshaw.library.datalayer.network.twirp.interceptors

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.GzipSource
import okio.buffer
import java.io.IOException
import android.util.Log // Added for logging

/**
 * Content encoding interceptor for HTTP client
 */
object ContentEncodingKt {
    private const val TAG = "ContentEncodingKt" // Added for logging

    /**
     * Installs content encoding interceptor on the HTTP client
     */
    fun installCustomContentEncoding(builder: OkHttpClient.Builder) {
        Log.d(TAG, "[DEBUG] installCustomContentEncoding called. Adding ContentEncodingInterceptor to OkHttpClient builder.")
        builder.addInterceptor(ContentEncodingInterceptor())
    }
    
    private class ContentEncodingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalResponse = chain.proceed(chain.request())
            val contentEncoding = originalResponse.header("Content-Encoding")
            Log.d(TAG, "[DEBUG] ContentEncodingInterceptor: Intercepting response. Content-Encoding: $contentEncoding")
            
            return when (contentEncoding) {
                "gzip" -> {
                    Log.d(TAG, "[DEBUG] ContentEncodingInterceptor: Content-Encoding is 'gzip'. Attempting to uncompress.")
                    uncompressGzip(originalResponse)
                }
                else -> {
                    Log.d(TAG, "[DEBUG] ContentEncodingInterceptor: Content-Encoding is not 'gzip' (or null). Returning original response.")
                    originalResponse
                }
            }
        }
        
        private fun uncompressGzip(response: Response): Response {
            Log.d(TAG, "[DEBUG] uncompressGzip called for response from: ${response.request.url}")
            val body = response.body
            if (body == null) {
                Log.w(TAG, "[DEBUG] uncompressGzip: Response body is null. Returning original response.")
                return response
            }
            
            Log.d(TAG, "[DEBUG] uncompressGzip: Decompressing GZIP body.")
            val gzipSource = GzipSource(body.source())
            val bodyString = gzipSource.buffer().readString(Charsets.UTF_8)
            Log.d(TAG, "[DEBUG] uncompressGzip: Body uncompressed. Length: ${bodyString.length}")

            val contentType = body.contentType()
            Log.d(TAG, "[DEBUG] uncompressGzip: Original content type: $contentType")
            val uncompressedBody = bodyString.toByteArray()
                .toResponseBody(contentType)
            
            Log.d(TAG, "[DEBUG] uncompressGzip: Removing 'Content-Encoding' and 'Content-Length' headers.")
            return response.newBuilder()
                .removeHeader("Content-Encoding")
                .removeHeader("Content-Length")
                .body(uncompressedBody)
                .build()
        }
    }
}
