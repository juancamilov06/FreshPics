package com.juanvilla.freshpic.ui.screens.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val favoritesSource: LiveData<ResultType<List<Gif>>> =
        favoriteUseCase.getFavoriteGifs()

    fun fetchFavorites() = favoritesSource
}