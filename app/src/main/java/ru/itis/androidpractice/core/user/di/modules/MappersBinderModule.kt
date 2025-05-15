package ru.itis.androidpractice.core.user.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.auth.data.local.mappers.UserRoomMapper
import ru.itis.androidpractice.features.auth.data.local.mappers.impl.UserRoomMapperImpl
import ru.itis.androidpractice.features.auth.data.remote.mappers.UserFireBaseMapper
import ru.itis.androidpractice.features.auth.data.remote.mappers.impl.UserFirebaseMapperImpl

@Module
@InstallIn(SingletonComponent::class)
interface MappersBinderModule {

    @Binds
    fun bindFirebaseMapper(impl: UserFirebaseMapperImpl): UserFireBaseMapper

    @Binds
    fun bindRoomMapper(impl: UserRoomMapperImpl): UserRoomMapper
}