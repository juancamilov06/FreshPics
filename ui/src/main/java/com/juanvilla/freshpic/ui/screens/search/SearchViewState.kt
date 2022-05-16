package com.juanvilla.freshpic.ui.screens.search

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.ui.screens.trending.TrendingViewState

sealed class SearchViewState {
    object Loading : SearchViewState()
    data class Success(val data: GifWrapper) : SearchViewState()
    data class Error(val error: BaseException) : SearchViewState()
    object Empty : SearchViewState()
}