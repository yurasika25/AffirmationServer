package com.app.data


import kotlinx.serialization.Serializable

@Serializable
data class PostData(
    val name: String,
    val message: String
)