package com.juanvilla.freshpic.data.source.remote.search

import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface SearchRemoteDataSource {
    suspend fun getGifsByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int = 25
    ): ResultType<ApiGifWrapper>
}