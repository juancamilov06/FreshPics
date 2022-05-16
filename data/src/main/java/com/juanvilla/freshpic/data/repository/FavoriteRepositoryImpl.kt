package com.juanvilla.freshpic.data.repository

import androidx.lifecycle.LiveData
import com.juanvilla.freshpic.data.mappers.DbGifMapper
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSource
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.repository.FavoriteRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
    private val dbGifMapper: DbGifMapper
) : FavoriteRepository {

    override suspend fun save(gif: Gif): ResultType<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(gif: Gif): ResultType<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): LiveData<ResultType<Gif>> {
        TODO("Not yet implemented")
    }
}