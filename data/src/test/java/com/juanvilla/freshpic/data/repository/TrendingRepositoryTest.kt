package com.juanvilla.freshpic.data.repository

import androidx.datastore.core.DataStore
import com.juanvilla.freshpic.data.mappers.ApiGifMapper
import com.juanvilla.freshpic.data.mappers.GifWrapperMapper
import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import com.juanvilla.freshpic.data.source.remote.entities.ApiGif
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.source.remote.entities.ApiImages
import com.juanvilla.freshpic.data.source.remote.entities.ApiOriginal
import com.juanvilla.freshpic.data.source.remote.trending.TrendingRemoteDataSource
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class TrendingRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val trendingRemoteDataSource: TrendingRemoteDataSource = mock()
    private val gifWrapperMapper = GifWrapperMapper

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
    fun `When getTrending is called, remote data source should be called`() = runBlockingTest {
        val data = buildApiGifWrapper()
        whenever(
            trendingRemoteDataSource.getTrendingGifs(
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenReturn(ResultType.Success(data))

        val repository = buildRepository()
        repository.getTrending(
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        verify(trendingRemoteDataSource).getTrendingGifs(
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )
    }

    @Test
    fun `Given getTrending is called, when getTrendingGifs is success, result should be success`() = runBlockingTest {
        val data = buildApiGifWrapper()
        whenever(
            trendingRemoteDataSource.getTrendingGifs(
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenReturn(ResultType.Success(data))

        val repository = buildRepository()
        val result = repository.getTrending(
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        Assert.assertTrue(
            result is ResultType.Success && result.data == gifWrapperMapper.mapApiToDomain(data)
        )
    }

    @Test
    fun `Given getTrending is called, when getTrendingGifs is error, result should be error`() = runBlockingTest {
        buildApiGifWrapper()
        whenever(
            trendingRemoteDataSource.getTrendingGifs(
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenReturn(ResultType.Error(BaseException("")))

        val repository = buildRepository()
        val result = repository.getTrending(
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    private fun buildApiGifWrapper() = ApiGifWrapper(
        gifs = listOf(getTestGif()),
        meta = null,
        pagination = null
    )

    private fun getTestGif() = ApiGif(
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
        images = ApiImages(
            ApiOriginal(
                height = 100,
                width = 100,
                url = "images.original.url"
            )
        ),
        isFavorite = false
    )

    private fun buildRepository() = TrendingRepositoryImpl(
        trendingRemoteDataSource,
        gifWrapperMapper
    )

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }
}