package com.juanvilla.freshpic.data.repository

import com.juanvilla.freshpic.data.mappers.GifWrapperMapper
import com.juanvilla.freshpic.data.source.remote.trending.TrendingRemoteDataSource
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.repository.TrendingRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val trendingRemoteDataSource: TrendingRemoteDataSource,
    private val gifWrapperMapper: GifWrapperMapper
) : TrendingRepository {
    override suspend fun getTrending(offset: Int, rating: String, limit: Int): ResultType<GifWrapper> {
        val result = trendingRemoteDataSource.getTrendingGifs(
            offset,
            rating,
            limit
        )

        return when (result) {
            is ResultType.Success -> {
                ResultType.Success(
                    gifWrapperMapper.mapApiToDomain(result.data)
                )
            }
            is ResultType.Error -> result
        }
    }
}