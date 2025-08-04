package com.app

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val firstName: String,
    val latestName: String,
    val age: Int,
    val gender: String,
    val phoneNumber: String,
    val email: String
)
