package com.example.movierecap.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("filter?type=education")
    suspend fun getMoviesList(): List<MovieDto>

    @GET("activity/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
    ): MovieDto

}