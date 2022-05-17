package com.juanvilla.freshpic.domain

import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.entity.Image
import com.juanvilla.freshpic.domain.repository.SearchRepository
import com.juanvilla.freshpic.domain.usecase.search.SearchUseCaseImpl
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
class SearchUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val searchRepository: SearchRepository = mock()

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
    fun `When searchGifByName is called, then the repository should be invoked`() = runBlockingTest {
        val data = buildGifWrapper()
        whenever(
            searchRepository.searchByName(
                name = "",
                offset = TEST_OFFSET,
                limit = TEST_LIMIT,
                rating = TEST_RATING
            )
        ).thenReturn(ResultType.Success(data))

        val useCase = buildUseCase()
        useCase.searchGifByName(
            name = "",
            offset = TEST_OFFSET,
            limit = TEST_LIMIT,
            rating = TEST_RATING
        )

        verify(searchRepository).searchByName(
            name = "",
            offset = TEST_OFFSET,
            limit = TEST_LIMIT,
            rating = TEST_RATING
        )
    }

    private fun buildUseCase() = SearchUseCaseImpl(
        searchRepository
    )

    private fun buildGifWrapper() = GifWrapper(
        gifs = listOf(getTestGif())
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

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }

}