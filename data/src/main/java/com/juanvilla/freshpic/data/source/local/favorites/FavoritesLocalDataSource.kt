package com.juanvilla.freshpic.data.source.local.favorites

import androidx.lifecycle.LiveData
import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface FavoritesLocalDataSource {
    suspend fun save(gif: DbGif): ResultType<Unit>
    suspend fun delete(gif: DbGif): ResultType<Unit>
    fun findAll(): LiveData<ResultType<List<DbGif>>>
}