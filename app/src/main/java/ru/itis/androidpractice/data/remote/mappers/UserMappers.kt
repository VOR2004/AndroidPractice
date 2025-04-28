package ru.itis.androidpractice.data.remote.mappers

import ru.itis.androidpractice.data.common.model.BaseUserModel
import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.data.remote.models.FirebaseUser

object UserMappers {

    fun BaseUserModel.toUserEntity() = UserEntity(
        id = id,
        email = email,
        username = username
    )

    fun BaseUserModel.toFirebaseUser() = FirebaseUser(
        id = id,
        email = email,
        hashPassword = hashPassword,
        username = username
    )

    fun FirebaseUser.toBaseUserModel() = BaseUserModel(
        id = id,
        email = email,
        hashPassword = hashPassword,
        username = username
    )

    fun UserEntity.toBaseUserModel() = BaseUserModel(
        id = id,
        email = email,
        username = username
    )
}
