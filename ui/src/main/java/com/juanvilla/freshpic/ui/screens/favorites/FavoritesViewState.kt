package com.juanvilla.freshpic.ui.screens.favorites

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.exception.BaseException

sealed class FavoritesViewState {
    object Loading : FavoritesViewState()
    data class Success(val data: GifWrapper) : FavoritesViewState()
    data class Error(val error: BaseException) : FavoritesViewState()
}