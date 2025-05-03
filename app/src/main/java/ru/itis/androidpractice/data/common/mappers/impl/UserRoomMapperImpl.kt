package ru.itis.androidpractice.data.common.mappers.impl

import ru.itis.androidpractice.data.common.mappers.UserRoomMapper
import ru.itis.androidpractice.domain.model.BaseUserModel
import ru.itis.androidpractice.data.local.entities.UserEntity
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