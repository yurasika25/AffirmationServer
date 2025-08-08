package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class AffirmationModel(
    val id: Int,
    val text: String
)