package com.hawkshaw.library.features.telephony

import android.Manifest
import android.content.Context
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.IInterface
import android.telecom.TelecomManager
import android.telephony.PhoneNumberUtils
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.flow.first
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi

object S1C {
    fun d(): StringMatcherForBlocking {
        Log.d("CallBlocker", "[DEBUG] S1C.d() called, returning StringMatcherForBlocking instance.") // Using CallBlocker TAG for simplicity or define a new one
        return StringMatcherForBlocking
    }
}

object StringMatcherForBlocking {
    private const val TAG_MATCHER = "StringMatcher" // Specific TAG for this object

    fun g(pattern: String, input: String): Int {
        Log.d(TAG_MATCHER, "[DEBUG] g called. Raw pattern: \"$pattern\", Raw input: \"$input\"")
        val normalizedPattern = PhoneNumberUtils.stripSeparators(pattern) ?: ""
        val normalizedInput = PhoneNumberUtils.stripSeparators(input) ?: ""
        Log.d(TAG_MATCHER, "[DEBUG] Normalized pattern: \"$normalizedPattern\", Normalized input: \"$normalizedInput\"")

        val result = if (normalizedPattern == normalizedInput) {
            2
        } else if (normalizedInput.contains(normalizedPattern) || normalizedPattern.contains(normalizedInput)) {
            1
        } else {
            0
        }
        Log.d(TAG_MATCHER, "[DEBUG] Match result: $result (2=exact, 1=partial, 0=none)")
        return result
    }
}


object CallBlocker {
    val INSTANCE: CallBlocker = this
    private const val TAG = "CallBlocker"
    @RequiresApi(Build.VERSION_CODES.O)
    private const val REQUIRED_PERMISSION = Manifest.permission.ANSWER_PHONE_CALLS

    /**
     * Internal method to end a call using appropriate API.
     * This corresponds to the private boolean endCall() method in Java.
     * @return Boolean indicating if ending the call was successful
     */
    private fun endCall(): Boolean {
        Log.d(TAG, "[DEBUG] Internal endCall() invoked.")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Log.d(TAG, "[DEBUG] SDK >= P. Attempting to use TelecomManager.")
            val context = App.getContext()
            val hasPermission = ManifestPermissionsKt.hasPermission(REQUIRED_PERMISSION)
            Log.d(TAG, "[DEBUG] Permission $REQUIRED_PERMISSION: $hasPermission")
            if (!hasPermission) {
                Logger.e( // Existing log
                    TAG,
                    "Missing required permission: $REQUIRED_PERMISSION for TelecomManager.endCall()",
                    b = false,
                    i = 12,
                    nothing = null
                )
                Log.w(TAG, "[DEBUG] Missing $REQUIRED_PERMISSION. Returning false from endCall().")
                return false
            }
            val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager

            return try {
                Log.d(TAG, "[DEBUG] Attempting telecomManager.endCall()")
                telecomManager.endCall()
                Logger.d(TAG, "Call blocked using TelecomManager.endCall()", false, 4, null) // Existing
                Log.d(TAG, "[DEBUG] telecomManager.endCall() successful.")
                true
            } catch (e: SecurityException) {
                Logger.e( // Existing log
                    TAG,
                    "Security exception while using TelecomManager.endCall()",
                    e,
                    false,
                    12,
                    null
                )
                Log.e(TAG, "[DEBUG] SecurityException with TelecomManager: ${e.message}")
                false
            } catch (e: Exception) {
                Logger.e(TAG, "Exception while using TelecomManager.endCall()", e, false, 12, null) // Existing
                Log.e(TAG, "[DEBUG] Exception with TelecomManager: ${e.message}")
                false
            }
        }
        
        Log.d(TAG, "[DEBUG] SDK < P or TelecomManager failed. Attempting to use reflection.")
        return try {
            val telephonyClass = Class.forName("com.android.internal.telephony.ITelephony")
            val stubClass = telephonyClass.declaredClasses[0]
            val serviceManagerClass = Class.forName("android.os.ServiceManager")
            val serviceManagerNativeClass = Class.forName("android.os.ServiceManagerNative")
            
            val getServiceMethod = serviceManagerClass.getMethod("getService", String::class.java)
            val asInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder::class.java)
            
            val binder = Binder()
            binder.attachInterface(null as IInterface?, "fake") // This line might be problematic if IInterface is expected
            
            val phoneServiceBinder = getServiceMethod.invoke(
                asInterfaceMethod.invoke(null, binder), // This looks suspicious, binder is local and not from system service
                "phone"
            ) as IBinder
            Log.d(TAG, "[DEBUG] Reflection: phoneServiceBinder obtained.")

            val endCallMethod = telephonyClass.getMethod("endCall")
            Log.d(TAG, "[DEBUG] Reflection: Attempting endCallMethod.invoke()")
            val reflectionResult = endCallMethod.invoke(
                stubClass.getMethod("asInterface", IBinder::class.java)
                    .invoke(null, phoneServiceBinder)
            ) as Boolean
            Log.d(TAG, "[DEBUG] Reflection endCall() successful: $reflectionResult")
            reflectionResult
        } catch (e: SecurityException) {
            Logger.e( // Existing log
                TAG,
                "Security exception while using reflection to end call",
                e,
                false,
                12,
                null
            )
            Log.e(TAG, "[DEBUG] SecurityException with reflection: ${e.message}")
            false
        } catch (e: Exception) {
            Logger.e(TAG, "Exception while using reflection to end call", e, false, 12, null) // Existing
            Log.e(TAG, "[DEBUG] Exception with reflection: ${e.message}")
            false
        }
    }

    /**
     * Ends a call and logs the result.
     */
    private fun endCall(phoneNumber: String, isOutgoing: Boolean = false): Boolean {
        Log.d(TAG, "[DEBUG] endCall(phoneNumber=\"$phoneNumber\", isOutgoing=$isOutgoing) invoked.")
        val result = try {
            Log.d(TAG, "[DEBUG] Calling internal parameterless endCall() from endCall(phoneNumber, isOutgoing).")
            endCall()
        } catch (e: SecurityException) {
            Logger.e(TAG, "Security exception while ending call", e, false, 12, null) // Existing
            Log.e(TAG, "[DEBUG] SecurityException in endCall(phoneNumber, isOutgoing) wrapper: ${e.message}")
            false
        } catch (e: Exception) {
            Logger.e(TAG, "Exception while ending call", e, false, 12, null) // Existing
            Log.e(TAG, "[DEBUG] Exception in endCall(phoneNumber, isOutgoing) wrapper: ${e.message}")
            false
        }
        Log.d(TAG, "[DEBUG] Result of internal endCall(): $result")

        val callType = if (isOutgoing) "Outgoing" else "Incoming"
        val logMessage = if (result) {
            "$callType call blocked from $phoneNumber"
        } else {
            "Call blocking for $phoneNumber failed!"
        }
        Logger.d(TAG, logMessage, false, 4, null) // Existing
        Log.d(TAG, "[DEBUG] endCall(phoneNumber, isOutgoing) returning: $result. Log message: \"$logMessage\"")
        return result
    }


    /**
     * Gets matching call block entity for a phone number.
     */
    private suspend fun getMatch(phoneNumber: String): CallBlockEntity? {
        Log.d(TAG, "[DEBUG] getMatch called with phoneNumber: \"$phoneNumber\"")
        val telephonyDao = AppDatabaseKt.db.telephonyDao()
        Log.d(TAG, "[DEBUG] Fetching call block numbers from DAO.")
        val callBlockNumbers = telephonyDao.getCallBlockNumbers().first()
        Log.d(TAG, "[DEBUG] Retrieved ${callBlockNumbers.size} call block numbers.")

        if (callBlockNumbers.isEmpty()) {
            Log.d(TAG, "[DEBUG] No call block numbers in DB. Returning null from getMatch.")
            return null
        }

        val s1cInstance = S1C.d()
        Log.d(TAG, "[DEBUG] Iterating through call block numbers.")
        for (record in callBlockNumbers) {
            Log.d(TAG, "[DEBUG] Checking record: ID=${record.id}, Name=${record.name}, Number=${record.number}, BlockOutgoing=${record.blockOutgoing}")
            if (record.name == "HAWKSHAW_ALL") {
                Log.d(TAG, "[DEBUG] Matched HAWKSHAW_ALL. Returning record: $record")
                return record
            }

            Log.d(TAG, "[DEBUG] Calling s1cInstance.g with pattern: \"${record.number}\", input: \"$phoneNumber\"")
            val matchResult = s1cInstance.g(record.number, phoneNumber)
            Log.d(TAG, "[DEBUG] Match result from s1cInstance.g: $matchResult")
            if (matchResult == 2 || matchResult == 1) {
                Log.d(TAG, "[DEBUG] Match found (result $matchResult). Returning record: $record")
                return record
            }
        }
        Log.d(TAG, "[DEBUG] No match found after iterating all records. Returning null from getMatch.")
        return null
    }


    /**
     * Checks the call state and blocks calls if applicable.
     */
    suspend fun onCallStateChanged(state: Int, incomingNumber: String?): Boolean {
        Log.d(TAG, "[DEBUG] onCallStateChanged invoked. State: $state, IncomingNumber: \"$incomingNumber\"")
        if (incomingNumber == null) {
            Log.d(TAG, "[DEBUG] incomingNumber is null. Returning false.")
            return false
        }
        if (state == 0) { // Assuming 0 is idle state
            Log.d(TAG, "[DEBUG] Call state is 0 (Idle). Returning false.")
            return false
        }

        Log.d(TAG, "[DEBUG] Calling getMatch for incomingNumber: \"$incomingNumber\"")
        val callBlockEntity = getMatch(incomingNumber)

        if (callBlockEntity == null) {
            Log.d(TAG, "[DEBUG] getMatch returned null. No blocking rule matched. Returning false.")
            return false
        }
        Log.d(TAG, "[DEBUG] getMatch returned entity: $callBlockEntity")

        return when (state) {
            1 -> { // Incoming call ringing
                Log.d(TAG, "[DEBUG] State is 1 (Incoming Ringing). Attempting to end call for \"$incomingNumber\".")
                endCall(incomingNumber, false)
            }
            2 -> { // Off-hook (could be outgoing or an active incoming call)
                Log.d(TAG, "[DEBUG] State is 2 (Off-hook). Checking if blockOutgoing is true for entity: ${callBlockEntity.blockOutgoing}")
                if (callBlockEntity.blockOutgoing) {
                    Log.d(TAG, "[DEBUG] blockOutgoing is true. Attempting to end call for \"$incomingNumber\" (as outgoing).")
                    endCall(incomingNumber, true)
                } else {
                    Log.d(TAG, "[DEBUG] blockOutgoing is false. Not blocking.")
                    false
                }
            }
            else -> {
                Log.d(TAG, "[DEBUG] Unhandled call state: $state. Returning false.")
                false
            }
        }.also { result ->
             Log.d(TAG, "[DEBUG] onCallStateChanged for state $state, number \"$incomingNumber\" returning: $result")
        }
    }
}
