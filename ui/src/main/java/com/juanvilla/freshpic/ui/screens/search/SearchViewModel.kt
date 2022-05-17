package com.juanvilla.freshpic.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.entity.Gif
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
    private val items: MutableList<Gif> = mutableListOf()
    private var lastKeyWord = ""

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

    fun findGifsByKeyword(keyword: String, rating: String) {
        if (keyword.isBlank()) {
            _searchViewStateSource.postValue(SearchViewState.Empty)
            currentOffset = 0
            return
        }

        if (keyword != lastKeyWord) {
            currentOffset = 0
            items.clear()
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
                    items.addAll(result.data.gifs)
                    _searchViewStateSource.postValue(
                        SearchViewState.Success(items)
                    )
                    currentOffset += 26
                }
                is ResultType.Error -> _searchViewStateSource.postValue(
                    SearchViewState.Error(result.error)
                )
            }
        }
    }
}