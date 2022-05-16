package com.juanvilla.freshpic.domain.repository

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface SearchRepository {
    suspend fun searchByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int = 25
    ): ResultType<GifWrapper>
}