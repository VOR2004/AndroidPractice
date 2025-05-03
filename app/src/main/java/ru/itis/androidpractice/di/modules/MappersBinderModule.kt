package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.data.common.mappers.UserFireBaseMapper
import ru.itis.androidpractice.data.common.mappers.UserRoomMapper
import ru.itis.androidpractice.data.common.mappers.impl.UserFirebaseMapperImpl
import ru.itis.androidpractice.data.common.mappers.impl.UserRoomMapperImpl

@Module
@InstallIn(SingletonComponent::class)
interface MappersBinderModule {

    @Binds
    fun bindFirebaseMapper(impl: UserFirebaseMapperImpl): UserFireBaseMapper

    @Binds
    fun bindRoomMapper(impl: UserRoomMapperImpl): UserRoomMapper
}