package ru.itis.androidpractice.features.topic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.topic.common.validators.DefaultDescriptionValidator
import ru.itis.androidpractice.features.topic.common.validators.DefaultTitleValidator
import ru.itis.androidpractice.features.topic.domain.validation.DescriptionValidator
import ru.itis.androidpractice.features.topic.domain.validation.TitleValidator

@Module
@InstallIn(SingletonComponent::class)
interface TopicValidationBinderModule {

    @Binds
    fun bindTitleValidator_toImpl(impl: DefaultTitleValidator): TitleValidator

    @Binds
    fun bindDescValidator_toImpl(impl: DefaultDescriptionValidator): DescriptionValidator
}