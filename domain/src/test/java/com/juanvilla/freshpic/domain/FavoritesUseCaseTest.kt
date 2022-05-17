package com.juanvilla.freshpic.domain

import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.Image
import com.juanvilla.freshpic.domain.repository.FavoriteRepository
import com.juanvilla.freshpic.domain.usecase.favorite.FavoriteUseCaseImpl
import com.juanvilla.freshpic.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val favoritesRepository: FavoriteRepository = mock()

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
    fun `When deleteGifFromFavorites is called, then the repository should be invoked`() = runBlockingTest {
        val data = getTestGif()
        whenever(
            favoritesRepository.delete(data)
        ).thenReturn(ResultType.Success(Unit))

        val useCase = buildUseCase()
        useCase.deleteGifFromFavorites(data)

        verify(favoritesRepository).delete(data)
    }

    @Test
    fun `When saveGifToFavorites is called, then the repository should be invoked`() = runBlockingTest {
        val data = getTestGif()
        whenever(
            favoritesRepository.save(data)
        ).thenReturn(ResultType.Success(Unit))

        val useCase = buildUseCase()
        useCase.saveGifToFavorites(data)

        verify(favoritesRepository).save(data)
    }

    private fun buildUseCase() = FavoriteUseCaseImpl(
        favoritesRepository
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

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }

}