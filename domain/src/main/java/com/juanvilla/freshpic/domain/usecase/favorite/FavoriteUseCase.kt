package com.juanvilla.freshpic.domain.usecase.favorite

import androidx.lifecycle.LiveData
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.util.ResultType

interface FavoriteUseCase {
    suspend fun saveGifToFavorites(gif: Gif): ResultType<Long>
    suspend fun deleteGifFromFavorites(gif: Gif): ResultType<Unit>
    suspend fun getFavoriteGifs(): LiveData<ResultType<Gif>>
}