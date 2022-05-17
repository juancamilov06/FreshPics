package com.juanvilla.freshpic.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.Image
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoriteViewState
import com.juanvilla.freshpic.ui.screens.shared.SharedFavoritesViewModel
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
class SharedFavoritesViewModelTest {

    private val favoritesUseCase: FavoriteUseCase = mock(FavoriteUseCase::class.java)
    private val testDispatcher = TestCoroutineDispatcher()

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
    fun `given a favorite item is added, when saveGifInFavorites is called and success, then gif status changed is emitted with correct id`() = runBlockingTest {
        val gif = getTestGif()

        `when`(favoritesUseCase.saveGifToFavorites(gif)).thenReturn(
            ResultType.Success(Unit)
        )

        val viewModel = buildViewModel()

        viewModel.saveGifInFavorites(gif)
        val sourceResult = viewModel.gifFavoriteStatusSource.value

        Assert.assertEquals(
            gif.id,
            (sourceResult as SharedFavoriteViewState.GifFavoriteStatusChanged).gifId
        )
    }

    @Test
    fun `given a favorite item is added, when saveGifInFavorites is called and error, then gif status changed is emitted`() = runBlockingTest {
        val gif = getTestGif()

        `when`(favoritesUseCase.saveGifToFavorites(gif)).thenReturn(
            ResultType.Error(BaseException("Exception"))
        )

        val viewModel = buildViewModel()

        viewModel.saveGifInFavorites(gif)
        val sourceResult = viewModel.gifFavoriteStatusSource.value

        Assert.assertTrue(
            sourceResult is SharedFavoriteViewState.GifFavoriteStatusChangedError
        )
    }

    @Test
    fun `given a favorite item is deleted, when saveGifInFavorites is called and success, then gif status changed is emitted with correct id`() = runBlockingTest {
        val gif = getTestGif()

        `when`(favoritesUseCase.deleteGifFromFavorites(gif)).thenReturn(
            ResultType.Success(Unit)
        )

        val viewModel = buildViewModel()

        viewModel.deleteGifFromFavorites(gif)
        val sourceResult = viewModel.gifFavoriteStatusSource.value

        Assert.assertEquals(
            gif.id,
            (sourceResult as SharedFavoriteViewState.GifFavoriteStatusChanged).gifId
        )
    }

    @Test
    fun `given a favorite item is deleted, when saveGifInFavorites is called and error, then gif status changed is emitted`() = runBlockingTest {
        val gif = getTestGif()

        `when`(favoritesUseCase.deleteGifFromFavorites(gif)).thenReturn(
            ResultType.Error(BaseException("Exception"))
        )

        val viewModel = buildViewModel()

        viewModel.deleteGifFromFavorites(gif)
        val sourceResult = viewModel.gifFavoriteStatusSource.value

        Assert.assertTrue(
            sourceResult is SharedFavoriteViewState.GifFavoriteStatusChangedError
        )
    }

    @Test
    fun `given a favorite item is added, when saveGifInFavorites is called, then saveGifToFavorites should be called`() = runBlockingTest {
        val gif = getTestGif()

        `when`(favoritesUseCase.saveGifToFavorites(gif)).thenReturn(
            ResultType.Success(Unit)
        )

        val viewModel = buildViewModel()

        viewModel.saveGifInFavorites(gif)
        verify(favoritesUseCase).saveGifToFavorites(gif)
    }

    @Test
    fun `given a favorite item is deleted, when saveGifInFavorites is called, then deleteGifFromFavorites should be called`() = runBlockingTest {
        val gif = getTestGif()

        `when`(favoritesUseCase.deleteGifFromFavorites(gif)).thenReturn(
            ResultType.Error(BaseException("Exception"))
        )

        val viewModel = buildViewModel()

        viewModel.deleteGifFromFavorites(gif)
        verify(favoritesUseCase).deleteGifFromFavorites(gif)
    }

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

    private fun buildViewModel() = SharedFavoritesViewModel(
        favoritesUseCase,
        testDispatcher
    )
}