package ru.itis.androidpractice.data.common.mappers

import ru.itis.androidpractice.domain.model.BaseUserModel
import ru.itis.androidpractice.data.remote.entities.UserFirebaseEntity

interface UserFireBaseMapper {

    fun toBaseUserModel(dto: UserFirebaseEntity): BaseUserModel

    fun toFirebaseUserEntity(domain: BaseUserModel): UserFirebaseEntity
}