package ru.itis.androidpractice.data.common.mappers

import ru.itis.androidpractice.domain.model.BaseUserModel
import ru.itis.androidpractice.data.local.entities.UserEntity

interface UserRoomMapper {

    fun toBaseUserModel(entity: UserEntity): BaseUserModel

    fun toUserEntity(domain: BaseUserModel): UserEntity
}