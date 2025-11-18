package com.hawkshaw.library.network.model

/**
 * Data class representing the response from a device registration request.
 *
 * @property deviceToken The authentication token assigned to the device
 * @property message A message describing the result of the registration
 */
data class DeviceRegistrationResponse(
    val deviceToken: String,
    val message: String
)
