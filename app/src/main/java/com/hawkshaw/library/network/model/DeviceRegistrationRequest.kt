package com.hawkshaw.library.network.model

/**
 * Data class representing a device registration request.
 *
 * @property deviceId The unique identifier for the device
 * @property deviceName The name of the device (e.g., "Samsung Galaxy S21")
 * @property model The device model
 * @property manufacturer The device manufacturer
 * @property androidVersion The Android version running on the device
 * @property apiLevel The Android API level of the device
 * @property imei The IMEI number of the device (if available)
 * @property phoneNumber The phone number associated with the device (if any)
 * @property fcmToken The Firebase Cloud Messaging token for push notifications
 * @property appVersion The version of the app installed on the device
 */
data class DeviceRegistrationRequest(
    val deviceId: String,
    val deviceName: String,
    val model: String,
    val manufacturer: String,
    val androidVersion: String,
    val apiLevel: Int,
    val imei: String? = null,
    val phoneNumber: String? = null,
    val fcmToken: String? = null,
    val appVersion: String
)
