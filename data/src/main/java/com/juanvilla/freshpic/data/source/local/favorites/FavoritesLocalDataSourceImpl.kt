package com.juanvilla.freshpic.data.source.local.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class FavoritesLocalDataSourceImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoritesLocalDataSource {
    override suspend fun save(gif: DbGif): ResultType<Unit> {
        val result = favoritesDao.save(gif)
        return when {
            result >= 0 -> ResultType.Success(Unit)
            else -> ResultType.Error(BaseException("Can't save"))
        }
    }

    override suspend fun delete(gif: DbGif): ResultType<Unit> {
        val result = favoritesDao.delete(gif.id)
        return when {
            result > 0 -> ResultType.Success(Unit)
            else -> ResultType.Error(BaseException("Can't save"))
        }
    }

    override fun findAll(): LiveData<ResultType<List<DbGif>>> = Transformations.map(
        favoritesDao.findAll()
    ) {
        ResultType.Success(it)
    }
}