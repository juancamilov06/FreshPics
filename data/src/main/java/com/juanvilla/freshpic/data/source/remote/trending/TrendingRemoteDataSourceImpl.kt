package com.juanvilla.freshpic.data.source.remote.trending

import com.juanvilla.freshpic.data.api.GiphyAPI
import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.utils.safeNetworkCall
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class TrendingRemoteDataSourceImpl @Inject constructor(
    private val giphyAPI: GiphyAPI,
    private val favoritesDao: FavoritesDao
) : TrendingRemoteDataSource {
    override suspend fun getTrendingGifs(
        offset: Int,
        rating: String,
        limit: Int
    ): ResultType<ApiGifWrapper> {
        val result = safeNetworkCall {
            giphyAPI.getTrendingGifs(
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