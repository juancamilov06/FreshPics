package com.juanvilla.freshpic.domain.usecase.trending

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.repository.TrendingRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class TrendingUseCaseImpl @Inject constructor(
    private val trendingRepository: TrendingRepository
) : TrendingUseCase {
    override suspend fun getTrendingGifs(
        offset: Int,
        rating: String,
        limit: Int
    ): ResultType<GifWrapper> = trendingRepository.getTrending(
        offset,
        rating,
        limit
    )
}