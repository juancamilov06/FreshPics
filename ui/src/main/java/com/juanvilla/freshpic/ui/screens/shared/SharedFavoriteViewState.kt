package com.juanvilla.freshpic.ui.screens.shared

sealed class SharedFavoriteViewState {
    object UpdateGif : SharedFavoriteViewState()
    data class GifFavoriteStatusChanged(val gifId: String, val isFavorite: Boolean) : SharedFavoriteViewState()
    object GifFavoriteStatusChangedError : SharedFavoriteViewState()
}