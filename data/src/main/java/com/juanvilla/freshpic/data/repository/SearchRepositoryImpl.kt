package com.juanvilla.freshpic.data.repository

import com.juanvilla.freshpic.data.mappers.GifWrapperMapper
import com.juanvilla.freshpic.data.source.remote.search.SearchRemoteDataSource
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.repository.SearchRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val gifWrapperMapper: GifWrapperMapper
) : SearchRepository {
    override suspend fun searchByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int
    ): ResultType<GifWrapper> {
        val result = searchRemoteDataSource.getGifsByName(
            name,
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