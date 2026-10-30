package com.example.movierecap.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("key")
    val id: String,
    @SerialName("activity")
    val title: String,
    @SerialName("type")
    val director: String,
    @SerialName("price")
    val rating: Float,
)