package ru.itis.androidpractice.data.remote.mappers

import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.data.remote.models.FirebaseUser


object UserMappers {
    fun UserEntity.toFirebaseUser() = FirebaseUser(
        id = id,
        email = email,
        hashPassword = hashPassword,
        username = username
    )

    fun FirebaseUser.toUserEntity() = UserEntity(
        id = id,
        email = email,
        hashPassword = hashPassword,
        username = username
    )
}