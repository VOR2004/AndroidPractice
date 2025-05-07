package ru.itis.androidpractice.features.auth.data.remote.mappers.impl

import ru.itis.androidpractice.features.auth.data.remote.mappers.UserFireBaseMapper
import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import ru.itis.androidpractice.features.auth.data.remote.entities.UserFirebaseEntity
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