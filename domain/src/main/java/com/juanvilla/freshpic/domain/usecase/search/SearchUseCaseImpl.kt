package com.juanvilla.freshpic.domain.usecase.search

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.repository.SearchRepository
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : SearchUseCase {
    override suspend fun searchGifByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int
    ): ResultType<GifWrapper> = searchRepository.searchByName(
        name,
        offset,
        rating,
        limit
    )
}