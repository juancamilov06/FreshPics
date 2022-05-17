package com.juanvilla.freshpic.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.entity.Image
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.usecase.trending.TrendingUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import com.juanvilla.freshpic.ui.screens.trending.TrendingViewModel
import com.juanvilla.freshpic.ui.screens.trending.TrendingViewState
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class TrendingViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val trendingUseCase: TrendingUseCase = mock()

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
    fun `Given getTrendingGifs is called, when useCase is success, result should be success`() = runBlockingTest {
        val data = buildGifWrapper()
        whenever(
            trendingUseCase.getTrendingGifs(
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Success(data))

        val viewModel = buildViewModel()
        viewModel.getTrendingGifs(TEST_RATING)

        val result = viewModel.trendingViewStateSource.value
        Assert.assertTrue(
            result is TrendingViewState.Success
        )
    }

    @Test
    fun `Given getTrendingGifs is called, when useCase is error, result should be error`() = runBlockingTest {
        whenever(
            trendingUseCase.getTrendingGifs(
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val viewModel = buildViewModel()
        viewModel.getTrendingGifs(TEST_RATING)

        val result = viewModel.trendingViewStateSource.value
        Assert.assertTrue(
            result is TrendingViewState.Error
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
        image = Image(
            height = 100,
            width = 100,
            url = "images.original.url"
        ),
        isFavorite = false
    )

    private fun buildViewModel() = TrendingViewModel(
        trendingUseCase,
        testDispatcher
    )

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }
}