package com.juanvilla.freshpic.data.source.remote.trending

import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface TrendingRemoteDataSource {
    suspend fun getTrendingGifs(
        offset: Int,
        rating: String,
        limit: Int = 25
    ): ResultType<ApiGifWrapper>
}