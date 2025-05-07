package ru.itis.androidpractice.features.auth.data.remote.mappers

import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import ru.itis.androidpractice.features.auth.data.remote.entities.UserFirebaseEntity

interface UserFireBaseMapper {

    fun toBaseUserModel(dto: UserFirebaseEntity): BaseUserModel

    fun toFirebaseUserEntity(domain: BaseUserModel): UserFirebaseEntity
}