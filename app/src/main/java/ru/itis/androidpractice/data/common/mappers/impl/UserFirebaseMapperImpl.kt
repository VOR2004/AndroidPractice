package ru.itis.androidpractice.data.common.mappers.impl

import ru.itis.androidpractice.data.common.mappers.UserFireBaseMapper
import ru.itis.androidpractice.domain.model.BaseUserModel
import ru.itis.androidpractice.data.remote.entities.UserFirebaseEntity
import javax.inject.Inject

class UserFirebaseMapperImpl @Inject constructor() : UserFireBaseMapper {
    override fun toBaseUserModel(dto: UserFirebaseEntity): BaseUserModel = BaseUserModel(
        id = dto.id,
        email = dto.email,
        username = dto.username
    )

    override fun toFirebaseUserEntity(domain: BaseUserModel): UserFirebaseEntity = UserFirebaseEntity(
        id = domain.id,
        email = domain.email,
        username = domain.username
    )
}