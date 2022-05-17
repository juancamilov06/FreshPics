package com.juanvilla.freshpic.ui.screens.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.usecase.trending.TrendingUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingUseCase: TrendingUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    @Named("IoDispatcher") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _trendingViewStateSource = MediatorLiveData<TrendingViewState>()
    val trendingViewStateSource: LiveData<TrendingViewState> = _trendingViewStateSource

    private var currentPage = 0
    private val items: MutableList<Gif> = mutableListOf()

    fun updateFavorite(id: String, isFavorite: Boolean) {
        val indexToUpdate = items.indexOfFirst {
            id == it.id
        }
        if (indexToUpdate >= 0 && indexToUpdate <= items.size -1) {
            val itemToUpdate = items[indexToUpdate]
            items[indexToUpdate] = itemToUpdate.copy(
                isFavorite = isFavorite
            )
        }
    }

    fun getTrendingGifs(rating: String) {
        viewModelScope.launch(ioDispatcher) {
            if (currentPage == 0) {
                _trendingViewStateSource.postValue(TrendingViewState.Loading)
            } else {
                _trendingViewStateSource.postValue(TrendingViewState.LoadingMore)
            }
            val result = trendingUseCase.getTrendingGifs(
                offset = currentPage,
                rating = rating,
                limit = 26
            )
            when (result) {
                is ResultType.Success -> {
                    items.addAll(result.data.gifs)
                    _trendingViewStateSource.postValue(
                        TrendingViewState.Success(items)
                    )
                    currentPage += 26
                }
                is ResultType.Error -> _trendingViewStateSource.postValue(
                    TrendingViewState.Error(result.error)
                )
            }
        }
    }

}