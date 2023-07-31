package ca.quantum.quants.it.housefy.models

// FeedbackDataModels.kt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("FullName")
    val name: String,
    val email: String,
    @SerialName("PhoneModel")
    val deviceName: String,
    @SerialName("PhoneNumber")
    val phoneNumber: String
)

@Serializable
data class Feedback(
    @SerialName("Rate")
    val rating: Int,
    val comment: String,
    val user: User
)

