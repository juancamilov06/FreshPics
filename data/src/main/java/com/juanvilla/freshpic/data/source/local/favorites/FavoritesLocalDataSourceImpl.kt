package com.juanvilla.freshpic.data.source.local.favorites

import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.local.dao.UserDao
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class FavoritesLocalDataSourceImpl @Inject constructor(
    private val favoritesDao: FavoritesDao,
    private val userDao: UserDao
) : FavoritesLocalDataSource {
    override fun save(gif: Gif): ResultType<Long> {
        TODO("Not yet implemented")
    }

    override fun delete(gif: Gif): ResultType<Unit> {
        TODO("Not yet implemented")
    }

    override fun findAll(): ResultType<GifWrapper> {
        TODO("Not yet implemented")
    }
}