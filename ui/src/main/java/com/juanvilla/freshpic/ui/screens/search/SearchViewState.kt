package com.juanvilla.freshpic.ui.screens.search

import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.exception.BaseException

sealed class SearchViewState {
    object Loading : SearchViewState()
    object LoadingMore : SearchViewState()
    data class Success(val data: GifWrapper) : SearchViewState()
    data class Error(val error: BaseException) : SearchViewState()
    object Empty : SearchViewState()
}