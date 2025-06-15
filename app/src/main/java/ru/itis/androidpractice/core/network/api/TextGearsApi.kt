package ru.itis.androidpractice.core.network.api

import retrofit2.http.GET
import retrofit2.http.Query

interface TextGearsApi {
    @GET("grammar")
    suspend fun checkSpelling(
        @Query("text") text: String,
        @Query("language") language: String = "en-US",
        @Query("ai") ai: Boolean = true
    ): TextGearsResponse
}

data class TextGearsResponse(
    val status: Boolean,
    val description: String?,
    val response: ResponseData?
)

data class Description(
    val en: String? = null
)

data class ResponseData(
    val errors: List<GrammarError> = emptyList()
)

data class GrammarError(
    val id: String,
    val offset: Int,
    val length: Int,
    val bad: String,
    val description: Description,
    val better: List<String> = emptyList(),
    val type: String
)




