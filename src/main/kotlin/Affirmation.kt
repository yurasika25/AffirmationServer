package com.app

import kotlinx.serialization.Serializable

@Serializable
data class Affirmation(
    val id: Int,
    val message: String
)
