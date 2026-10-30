package com.example.movierecap.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object MovieClient {

    // Creation MovieClient pour l'API REST
    fun create(): MovieApiService {
        val networkJson = Json { ignoreUnknownKeys = true }

        return Retrofit
            .Builder()
            .baseUrl("https://bored-api.appbrewery.com/")
            .addConverterFactory(
                networkJson
                    .asConverterFactory(
                        "application/json".toMediaType()
                    )
            )
            .build()
            .create(MovieApiService::class.java)
    }

}