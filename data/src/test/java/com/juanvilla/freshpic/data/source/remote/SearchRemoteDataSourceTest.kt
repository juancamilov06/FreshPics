package com.juanvilla.freshpic.data.source.remote

import com.juanvilla.freshpic.data.api.GiphyAPI
import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.remote.entities.ApiGif
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.source.remote.entities.ApiImages
import com.juanvilla.freshpic.data.source.remote.entities.ApiOriginal
import com.juanvilla.freshpic.data.source.remote.search.SearchRemoteDataSourceImpl
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
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRemoteDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val giphyAPI: GiphyAPI = mock()
    private val favoritesDao: FavoritesDao = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test(expected = Exception::class)
    fun `given getGifsByName is called, when api response throws exception, then result should be successful`() = runBlockingTest {
        whenever(
            giphyAPI.getGifsByName(
                name = "",
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenThrow(Exception())

        val dataSource = buildDataSource()
        val result = dataSource.getGifsByName(
            name = "",
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `given getGifsByName is called, when api response is successful, then result should be successful`() = runBlockingTest {
        whenever(
            giphyAPI.getGifsByName(
                name = "",
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenReturn(Response.success(200, buildApiGifWrapper()))

        whenever(
            favoritesDao.isFavorite("id")
        ).thenReturn(false)

        val dataSource = buildDataSource()
        val result = dataSource.getGifsByName(
            name = "",
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    @Test
    fun `given getGifsByName is called, when api response is successful and gif not favorite in db, then result should be successful and gif favorite should be false`() = runBlockingTest {
        whenever(
            giphyAPI.getGifsByName(
                name = "",
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenReturn(Response.success(200, buildApiGifWrapper()))

        whenever(
            favoritesDao.isFavorite("id")
        ).thenReturn(false)

        val dataSource = buildDataSource()
        val result = dataSource.getGifsByName(
            name = "",
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        Assert.assertTrue(
            result is ResultType.Success && !result.data.gifs[0].isFavorite
        )
    }

    @Test
    fun `given getGifsByName is called, when api response is successful and gif is favorite in db, then result should be successful and gif favorite should be true`() = runBlockingTest {
        whenever(
            giphyAPI.getGifsByName(
                name = "",
                offset = TEST_OFFSET,
                rating = TEST_RATING,
                limit = TEST_LIMIT
            )
        ).thenReturn(Response.success(200, buildApiGifWrapper()))

        whenever(
            favoritesDao.isFavorite("id")
        ).thenReturn(true)

        val dataSource = buildDataSource()
        val result = dataSource.getGifsByName(
            name = "",
            offset = TEST_OFFSET,
            rating = TEST_RATING,
            limit = TEST_LIMIT
        )

        Assert.assertTrue(
            result is ResultType.Success && result.data.gifs[0].isFavorite
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

    private fun buildDataSource() = SearchRemoteDataSourceImpl(
        giphyAPI,
        favoritesDao
    )

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }
}