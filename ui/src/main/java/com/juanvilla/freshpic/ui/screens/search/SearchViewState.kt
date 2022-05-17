package com.juanvilla.freshpic.ui.screens.search

import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.exception.BaseException

sealed class SearchViewState {
    object Loading : SearchViewState()
    object LoadingMore : SearchViewState()
    data class Success(val data: MutableList<Gif>) : SearchViewState()
    data class Error(val error: BaseException) : SearchViewState()
    object Empty : SearchViewState()
}