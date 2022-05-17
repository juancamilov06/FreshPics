package com.juanvilla.freshpic.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    @Named("IoDispatcher") private val ioDispatcher: CoroutineDispatcher,
    @Named("MainDispatcher") private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchViewStateSource = MediatorLiveData<SearchViewState>().apply {
        postValue(SearchViewState.Empty)
    }
    val searchViewStateSource: LiveData<SearchViewState> = _searchViewStateSource

    var currentOffset = 0
    private var lastKeyWord = ""

    fun findGifsByKeyword(keyword: String, rating: String) {
        if (keyword.isBlank()) {
            _searchViewStateSource.postValue(SearchViewState.Empty)
            return
        }

        if (keyword != lastKeyWord) {
            currentOffset = 0
            _searchViewStateSource.postValue(SearchViewState.Loading)
        } else {
            _searchViewStateSource.postValue(SearchViewState.LoadingMore)
        }

        lastKeyWord = keyword

        viewModelScope.launch(ioDispatcher) {
            val result = searchUseCase.searchGifByName(
                name = keyword,
                offset = currentOffset,
                rating = rating,
                limit = 26
            )

            when (result) {
                is ResultType.Success -> {
                    _searchViewStateSource.postValue(
                        SearchViewState.Success(result.data)
                    )
                    withContext(mainDispatcher) {
                        currentOffset += 26
                    }
                }
                is ResultType.Error -> _searchViewStateSource.postValue(
                    SearchViewState.Error(result.error)
                )
            }
        }
    }
}