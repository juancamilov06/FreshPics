package com.juanvilla.freshpic.data.source.remote.search

import com.juanvilla.freshpic.data.api.GiphyAPI
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.utils.safeNetworkCall
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val giphyAPI: GiphyAPI
) : SearchRemoteDataSource {
    override suspend fun getGifsByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int
    ): ResultType<ApiGifWrapper> = safeNetworkCall {
        giphyAPI.getGifsByName(
            name,
            offset,
            rating,
            limit
        )
    }
}