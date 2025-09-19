package data

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileModel(
    val firstName: String,
    val lastName: String,
    val age: String,
    val gender: String,
    val phoneNumber: String,
    val email: String
)