package com.juanvilla.freshpic.data.source.remote.search

import com.juanvilla.freshpic.data.api.GiphyAPI
import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.utils.safeNetworkCall
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val giphyAPI: GiphyAPI,
    private val favoritesDao: FavoritesDao
) : SearchRemoteDataSource {
    override suspend fun getGifsByName(
        name: String,
        offset: Int,
        rating: String,
        limit: Int
    ): ResultType<ApiGifWrapper> {
        val result = safeNetworkCall {
            giphyAPI.getGifsByName(
                name,
                offset,
                rating,
                limit
            )
        }

        return when (result) {
            is ResultType.Success -> ResultType.Success(
                buildResponseWithFavorites(result.data)
            )
            is ResultType.Error -> result
        }
    }


    private suspend fun buildResponseWithFavorites(
        apiGifWrapper: ApiGifWrapper
    ): ApiGifWrapper {
        val listWithFavorites = apiGifWrapper.gifs.map {
            val gifIsFavorite = favoritesDao.isFavorite(it.id)
            it.copy(
                isFavorite = gifIsFavorite
            )
        }
        return apiGifWrapper.copy(
            gifs = listWithFavorites
        )
    }
}