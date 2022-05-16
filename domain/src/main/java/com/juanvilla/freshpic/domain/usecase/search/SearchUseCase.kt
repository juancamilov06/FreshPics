package com.juanvilla.freshpic.domain.usecase.search

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface SearchUseCase {
    suspend fun searchGifByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int = 25
    ): ResultType<GifWrapper>
}