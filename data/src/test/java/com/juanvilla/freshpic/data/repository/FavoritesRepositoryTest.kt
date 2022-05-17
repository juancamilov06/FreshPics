package com.juanvilla.freshpic.data.repository

import com.juanvilla.freshpic.data.mappers.DbGifMapper
import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSource
import com.juanvilla.freshpic.data.source.remote.entities.ApiGif
import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.data.source.remote.entities.ApiImages
import com.juanvilla.freshpic.data.source.remote.entities.ApiOriginal
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.Image
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
class FavoritesRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val favoritesLocalDataSource: FavoritesLocalDataSource = mock()
    private val dbGifMapper = DbGifMapper

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
    fun `When save is called, then localDataSource save should be invoked`() = runBlockingTest {
        val data = getTestGif()
        val domainData = getDomainTestGif()

        whenever(
            favoritesLocalDataSource.save(data)
        ).thenReturn(ResultType.Success(Unit))

        val repository = buildRepository()
        repository.save(domainData)

        verify(
            favoritesLocalDataSource
        ).save(data)
    }

    @Test
    fun `Given save is called, when localDataSource is successful, then result should be success`() = runBlockingTest {
        val data = getTestGif()
        val domainData = getDomainTestGif()

        whenever(
            favoritesLocalDataSource.save(data)
        ).thenReturn(ResultType.Success(Unit))

        val repository = buildRepository()
        val result = repository.save(domainData)

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    @Test
    fun `Given save is called, when localDataSource is error, then result should be error`() = runBlockingTest {
        val data = getTestGif()
        val domainData = getDomainTestGif()

        whenever(
            favoritesLocalDataSource.save(data)
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val repository = buildRepository()
        val result = repository.save(domainData)

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `When delete is called, then localDataSource delete should be invoked`() = runBlockingTest {
        val data = getTestGif()
        val domainData = getDomainTestGif()

        whenever(
            favoritesLocalDataSource.delete(data)
        ).thenReturn(ResultType.Success(Unit))

        val repository = buildRepository()
        repository.delete(domainData)

        verify(
            favoritesLocalDataSource
        ).delete(data)
    }

    @Test
    fun `Given delete is called, when localDataSource is successful, then result should be success`() = runBlockingTest {
        val data = getTestGif()
        val domainData = getDomainTestGif()

        whenever(
            favoritesLocalDataSource.delete(data)
        ).thenReturn(ResultType.Success(Unit))

        val repository = buildRepository()
        val result = repository.delete(domainData)

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    @Test
    fun `Given delete is called, when localDataSource is error, then result should be error`() = runBlockingTest {
        val data = getTestGif()
        val domainData = getDomainTestGif()

        whenever(
            favoritesLocalDataSource.delete(data)
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val repository = buildRepository()
        val result = repository.delete(domainData)

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    private fun getDomainTestGif() = Gif(
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
        isFavorite = true
    )

    private fun getTestGif() = DbGif(
        rowId = 0,
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
        height = 100,
        width = 100,
        contentUrl = "images.original.url",
        isFavorite = true
    )

    private fun buildRepository() = FavoriteRepositoryImpl(
        favoritesLocalDataSource,
        dbGifMapper
    )

    companion object {
        const val TEST_OFFSET = 0
        const val TEST_LIMIT = 26
        const val TEST_RATING = "pg-13"
    }
}