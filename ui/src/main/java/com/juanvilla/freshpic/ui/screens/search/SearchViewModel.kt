package com.juanvilla.freshpic.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCase
import com.juanvilla.freshpic.domain.util.Constants
import com.juanvilla.freshpic.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    @Named("IoDispatcher") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchViewStateSource = MediatorLiveData<SearchViewState>().apply {
        postValue(SearchViewState.Empty)
    }
    val searchViewStateSource: LiveData<SearchViewState> = _searchViewStateSource

    private var currentPage = 0

    fun findGifsByKeyword(keyword: String) {
        if (keyword.isBlank()) {
            _searchViewStateSource.postValue(SearchViewState.Empty)
            return
        }

        viewModelScope.launch(ioDispatcher) {
            val result = searchUseCase.searchGifByName(
                name = keyword,
                offset = currentPage,
                rating = Constants.PG13_RATING,
                limit = 26
            )

            when (result) {
                is ResultType.Success -> _searchViewStateSource.postValue(
                    SearchViewState.Success(result.data)
                )
                is ResultType.Error -> _searchViewStateSource.postValue(
                    SearchViewState.Error(result.error)
                )
            }
        }
    }
}