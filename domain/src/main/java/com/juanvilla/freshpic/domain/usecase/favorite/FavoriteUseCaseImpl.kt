package com.juanvilla.freshpic.domain.usecase.favorite

import androidx.lifecycle.LiveData
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.repository.FavoriteRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : FavoriteUseCase {
    override suspend fun saveGifToFavorites(gif: Gif): ResultType<Unit> =
        favoriteRepository.save(gif)

    override suspend fun deleteGifFromFavorites(gif: Gif): ResultType<Unit> =
        favoriteRepository.delete(gif)

    override fun getFavoriteGifs(): LiveData<ResultType<List<Gif>>> = favoriteRepository.findAll()
}