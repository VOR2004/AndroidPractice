package ru.itis.androidpractice.data.remote.mappers

import ru.itis.androidpractice.data.common.model.BaseUserModel
import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.data.remote.models.UserFirebase

object UserMappers {

    fun BaseUserModel.toUserEntity() = UserEntity(
        id = id,
        email = email,
        username = username
    )

    fun BaseUserModel.toFirebaseUser() = UserFirebase(
        id = id,
        email = email,
        username = username
    )

    fun UserFirebase.toBaseUserModel() = BaseUserModel(
        id = id,
        email = email,
        username = username
    )

    fun UserEntity.toBaseUserModel() = BaseUserModel(
        id = id,
        email = email,
        username = username
    )
}
