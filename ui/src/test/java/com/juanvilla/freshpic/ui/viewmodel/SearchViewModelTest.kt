package com.juanvilla.freshpic.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.entity.Image
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import com.juanvilla.freshpic.ui.screens.search.SearchViewModel
import com.juanvilla.freshpic.ui.screens.search.SearchViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val searchUseCase: SearchUseCase = mock(SearchUseCase::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `given a search term, when findGifsByKeyword is executed and success, source result should be success`() = runBlockingTest {
        val searchTerm = "test"
        val result = buildGifWrapper()

        `when`(
            searchUseCase.searchGifByName(
                name = searchTerm,
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Success(result))

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        val sourceResult = viewModel.searchViewStateSource.value
        Assert.assertTrue(
            sourceResult is SearchViewState.Success && sourceResult.data.gifs == result.gifs
        )
    }

    @Test
    fun `given a search term, when findGifsByKeyword is executed for the first time and error, then offset should remain 0`() = runBlockingTest {
        val searchTerm = "test"

        `when`(
            searchUseCase.searchGifByName(
                name = searchTerm,
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        Assert.assertTrue(
            viewModel.currentOffset == 0
        )
    }

    @Test
    fun `given a search term, when findGifsByKeyword is executed for the first time and success, then offset should be 26`() = runBlockingTest {
        val searchTerm = "test"
        val result = buildGifWrapper()

        `when`(
            searchUseCase.searchGifByName(
                name = searchTerm,
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Success(result))

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        Assert.assertTrue(
            viewModel.currentOffset == 26
        )
    }

    @Test
    fun `given a search term, when findGifsByKeyword is executed and error, source result should be error`() = runBlockingTest {
        val searchTerm = "test"
        `when`(
            searchUseCase.searchGifByName(
                name = searchTerm,
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        val sourceResult = viewModel.searchViewStateSource.value
        Assert.assertTrue(
            sourceResult is SearchViewState.Error
        )
    }

    @Test
    fun `given a search term, when findGifsByKeyword is executed, searchGifByName should be called`() = runBlockingTest {
        val searchTerm = "test"
        `when`(
            searchUseCase.searchGifByName(
                name = searchTerm,
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        verify(searchUseCase).searchGifByName(
            name = searchTerm,
            offset = TEST_OFFSET,
            limit = TEST_LIMIT,
            rating = TEST_RATING
        )
    }

    @Test
    fun `given an empty term, when findGifsByKeyword is executed and success, source result should be empty`() = runBlockingTest {
        val searchTerm = ""

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        val sourceResult = viewModel.searchViewStateSource.value
        Assert.assertTrue(
            sourceResult is SearchViewState.Empty
        )
    }

    @Test
    fun `given an empty term, when findGifsByKeyword is executed and success, offset should remain as 0`() = runBlockingTest {
        val searchTerm = ""

        val viewModel = buildViewModel()
        viewModel.findGifsByKeyword(searchTerm, TEST_RATING)

        Assert.assertTrue(
            viewModel.currentOffset == 0
        )
    }

    private fun buildGifWrapper() = GifWrapper(
        gifs = listOf(
            getTestGif()
        )
    )

    private fun getTestGif() = Gif(
        type = "type",
        id = "id",
        slug = "slug",
        url = "url",
        bitlyUrl = "bitlyUrl",
        embedUrl = "embedUrl",
        username = "username",
        source = "source",
        rating = "rating",
        updateDateTime = "updateDateTime",
        createDateTime = "createDateTime",
        importDateTime = "importDateTime",
        trendingDateTime = "trendingDateTime",
        title = "title",
        user = null,
        image = Image(
            height = 100,
            width = 100,
            url = "images.original.url"
        ),
        isFavorite = false
    )


    private fun buildViewModel() = SearchViewModel(
        searchUseCase,
        testDispatcher,
        testDispatcher
    )

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }
}