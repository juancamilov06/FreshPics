package com.juanvilla.freshpic.ui.screens.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.usecase.trending.TrendingUseCase
import com.juanvilla.freshpic.domain.util.Constants
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

    fun getTrendingGifs() {
        viewModelScope.launch(ioDispatcher) {
            _trendingViewStateSource.postValue(TrendingViewState.Loading)
            val result = trendingUseCase.getTrendingGifs(
                currentPage,
                Constants.PG13_RATING,
                26
            )
            when (result) {
                is ResultType.Success -> _trendingViewStateSource.postValue(
                    TrendingViewState.Success(result.data)
                )
                is ResultType.Error -> _trendingViewStateSource.postValue(
                    TrendingViewState.Error(result.error)
                )
            }
            currentPage++
        }
    }

}