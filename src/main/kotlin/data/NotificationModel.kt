package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class NotificationModel(
    val id: Int,
    val title: String,
    val message: String,
    val dateText: String
)