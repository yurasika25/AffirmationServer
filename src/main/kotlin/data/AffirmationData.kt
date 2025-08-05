package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class AffirmationData(
    val id: Int,
    val text: String
)