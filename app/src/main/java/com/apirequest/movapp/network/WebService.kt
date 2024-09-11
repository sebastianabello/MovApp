package com.apirequest.movapp.network

import com.apirequest.movapp.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("now_playing")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String,
    ): Response<MoviesResponse>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
    ): Response<MoviesResponse>

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
    ): Response<MoviesResponse>

    @GET("upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
    ): Response<MoviesResponse>
}