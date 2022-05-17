package com.juanvilla.freshpic.data.source.local

import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.data.source.local.favorites.FavoritesLocalDataSourceImpl
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

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesLocalDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()
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

    @Test
    fun `Given save is executed, when database save is error, result should be error`() = runBlockingTest {
        val gif = getTestGif()
        whenever(
            favoritesDao.save(gif)
        ).thenReturn(-1)

        val dataSource = buildDataSource()
        val result = dataSource.save(gif)

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `Given save is executed, when database save is successful, result should be success`() = runBlockingTest {
        val gif = getTestGif()
        whenever(
            favoritesDao.save(gif)
        ).thenReturn(1)

        val dataSource = buildDataSource()
        val result = dataSource.save(gif)

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    @Test
    fun `Given delete is executed, when database delete is error, result should be error`() = runBlockingTest {
        val gif = getTestGif()
        whenever(
            favoritesDao.delete(gif.id)
        ).thenReturn(-1)

        val dataSource = buildDataSource()
        val result = dataSource.delete(gif)

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `Given delete is executed, when database delete is successful, result should be success`() = runBlockingTest {
        val gif = getTestGif()
        whenever(
            favoritesDao.delete(gif.id)
        ).thenReturn(1)

        val dataSource = buildDataSource()
        val result = dataSource.delete(gif)

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    private fun buildDataSource() = FavoritesLocalDataSourceImpl(
        favoritesDao
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
        isFavorite = false
    )
}