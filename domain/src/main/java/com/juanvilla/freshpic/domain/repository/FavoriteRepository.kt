package com.juanvilla.freshpic.domain.repository

import androidx.lifecycle.LiveData
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface FavoriteRepository {
    suspend fun save(gif: Gif): ResultType<Unit>
    suspend fun delete(gif: Gif): ResultType<Unit>
    suspend fun findAll(): LiveData<ResultType<List<Gif>>>
}