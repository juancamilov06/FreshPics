package com.juanvilla.freshpic.data.source.remote.trending

import com.juanvilla.freshpic.data.api.GiphyAPI
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.utils.safeNetworkCall
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class TrendingRemoteDataSourceImpl @Inject constructor(
    private val giphyAPI: GiphyAPI
) : TrendingRemoteDataSource {
    override suspend fun getTrendingGifs(offset: Int, rating: String, limit: Int): ResultType<ApiGifWrapper> =
        safeNetworkCall() {
            giphyAPI.getTrendingGifs(
                offset,
                rating,
                limit
            )
        }
}