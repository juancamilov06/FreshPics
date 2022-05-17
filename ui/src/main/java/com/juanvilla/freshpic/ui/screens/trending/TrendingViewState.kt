package com.juanvilla.freshpic.ui.screens.trending

import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.exception.BaseException

sealed class TrendingViewState {
    object Loading : TrendingViewState()
    object LoadingMore : TrendingViewState()
    data class Success(val data: MutableList<Gif>) : TrendingViewState()
    data class Error(
        val baseException: BaseException
    ) : TrendingViewState()
}