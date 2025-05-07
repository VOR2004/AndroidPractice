package ru.itis.androidpractice.features.auth.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.features.auth.data.remote.mappers.UserFireBaseMapper
import ru.itis.androidpractice.features.auth.data.local.mappers.UserRoomMapper
import ru.itis.androidpractice.features.auth.data.remote.mappers.impl.UserFirebaseMapperImpl
import ru.itis.androidpractice.features.auth.data.local.mappers.impl.UserRoomMapperImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface MappersBinderModule {

    @Binds
    fun bindFirebaseMapper(impl: UserFirebaseMapperImpl): UserFireBaseMapper

    @Binds
    fun bindRoomMapper(impl: UserRoomMapperImpl): UserRoomMapper
}