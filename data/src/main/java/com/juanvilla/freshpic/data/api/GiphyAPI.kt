package com.juanvilla.freshpic.data.api

import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {
    @GET("search")
    suspend fun getGifsByName(
        @Query("q") name: String,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("limit") limit: Int = 25
    ): Response<ApiGifWrapper>

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("limit") limit: Int = 25
    ): Response<ApiGifWrapper>
}