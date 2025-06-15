package ru.itis.androidpractice.features.people.presentation.screenstates

data class PeopleState(
    val username: String = "",
    val rating: String = "",
    val status: String = "",
    val isError: Boolean = false,
    val inputUsername: String = "",
    val avatarUrl: String? = null
)
