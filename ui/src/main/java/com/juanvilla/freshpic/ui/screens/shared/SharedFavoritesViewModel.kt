package com.juanvilla.freshpic.ui.screens.shared

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import com.juanvilla.freshpic.ui.screens.search.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class SharedFavoritesViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    @Named("IoDispatcher") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _gifFavoriteStatusSource = MediatorLiveData<SharedFavoriteViewState>()
    val gifFavoriteStatusSource = _gifFavoriteStatusSource

    fun deleteGifFromFavorites(gif: Gif) {
        viewModelScope.launch(ioDispatcher) {
            _gifFavoriteStatusSource.postValue(SharedFavoriteViewState.UpdateGif)
            when (favoriteUseCase.deleteGifFromFavorites(gif)) {
                is ResultType.Success -> {
                    _gifFavoriteStatusSource.postValue(
                        SharedFavoriteViewState.GifFavoriteStatusChanged(gif.id)
                    )
                }
                is ResultType.Error -> {
                    _gifFavoriteStatusSource.postValue(
                        SharedFavoriteViewState.GifFavoriteStatusChangedError
                    )
                }
            }
        }
    }

    fun saveGifInFavorites(gif: Gif) {
        viewModelScope.launch(ioDispatcher) {
            _gifFavoriteStatusSource.postValue(SharedFavoriteViewState.UpdateGif)
            when (favoriteUseCase.saveGifToFavorites(gif)) {
                is ResultType.Success -> {
                    _gifFavoriteStatusSource.postValue(
                        SharedFavoriteViewState.GifFavoriteStatusChanged(gif.id)
                    )
                }
                is ResultType.Error -> {
                    _gifFavoriteStatusSource.postValue(
                        SharedFavoriteViewState.GifFavoriteStatusChangedError
                    )
                }
            }
        }
    }
}