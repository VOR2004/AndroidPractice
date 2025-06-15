package ru.itis.androidpractice.features.topic.data.remote.entities

import androidx.compose.runtime.Immutable

@Immutable
data class UserRatingEntity(
    val userId: String = "",
    val rating: Int = 0
)