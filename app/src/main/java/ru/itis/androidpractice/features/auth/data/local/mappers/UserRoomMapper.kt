package ru.itis.androidpractice.features.auth.data.local.mappers

import ru.itis.androidpractice.features.auth.data.local.entities.UserEntity
import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel

interface UserRoomMapper {

    fun toBaseUserModel(entity: UserEntity): BaseUserModel

    fun toUserEntity(domain: BaseUserModel): UserEntity
}