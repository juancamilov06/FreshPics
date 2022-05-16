package com.juanvilla.freshpic.domain.usecase.trending

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface TrendingUseCase {
    suspend fun getTrendingGifs(
        offset: Int,
        rating: String,
        limit: Int = 25
    ): ResultType<GifWrapper>
}