package ca.quantum.quants.it.housefy.models

// FeedbackDataModels.kt

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val email: String,
    val deviceName: String,
    val phoneNumber: String
)

@Serializable
data class Feedback(
    val rating: Int,
    val comment: String,
    val user: User
)
