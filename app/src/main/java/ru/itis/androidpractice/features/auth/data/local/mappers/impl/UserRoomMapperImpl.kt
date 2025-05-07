package ru.itis.androidpractice.features.auth.data.local.mappers.impl

import ru.itis.androidpractice.features.auth.data.local.entities.UserEntity
import ru.itis.androidpractice.features.auth.data.local.mappers.UserRoomMapper
import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import javax.inject.Inject

class UserRoomMapperImpl @Inject constructor() : UserRoomMapper {
    override fun toBaseUserModel(entity: UserEntity): BaseUserModel = BaseUserModel(
        id = entity.id,
        email = entity.email,
        username = entity.username
    )

    override fun toUserEntity(domain: BaseUserModel): UserEntity = UserEntity(
        id = domain.id,
        email = domain.email,
        username = domain.username
    )
}