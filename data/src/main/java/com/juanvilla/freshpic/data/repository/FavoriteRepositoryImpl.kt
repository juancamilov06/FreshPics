package com.juanvilla.freshpic.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.juanvilla.freshpic.data.mappers.DbGifMapper
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSource
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.repository.FavoriteRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
    private val dbGifMapper: DbGifMapper
) : FavoriteRepository {

    override suspend fun save(gif: Gif): ResultType<Unit> = favoritesLocalDataSource.save(
        dbGifMapper.mapDomainToDb(gif)
    )

    override suspend fun delete(gif: Gif): ResultType<Unit> = favoritesLocalDataSource.delete(
        dbGifMapper.mapDomainToDb(gif)
    )

    override fun findAll(): LiveData<ResultType<List<Gif>>> = Transformations.switchMap(
        favoritesLocalDataSource.findAll()
    ) {
        when (it) {
            is ResultType.Success -> {
                MutableLiveData(
                    ResultType.Success(
                        dbGifMapper.mapDbListToDomainList(it.data)
                    )
                )
            }
            is ResultType.Error -> {
                MutableLiveData(
                    ResultType.Error(
                        BaseException("")
                    )
                )
            }
        }
    }
}