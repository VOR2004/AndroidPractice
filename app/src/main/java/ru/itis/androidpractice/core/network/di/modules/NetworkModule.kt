package ru.itis.androidpractice.core.network.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.androidpractice.BuildConfig
import ru.itis.androidpractice.core.network.api.TextGearsApi
import ru.itis.androidpractice.core.network.api.TextGearsApiKeyInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideTextGearsApiKey(): String = BuildConfig.API_KEY

    @Provides
    fun provideTextGearsApiKeyInterceptor(apiKey: String): TextGearsApiKeyInterceptor =
        TextGearsApiKeyInterceptor(apiKey)

    @Provides
    fun provideOkHttpClient(
        textGearsApiKeyInterceptor: TextGearsApiKeyInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(textGearsApiKeyInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun provideTextGearsApi(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): TextGearsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_TEXTGEARS)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
        return retrofit.create(TextGearsApi::class.java)
    }
}


