package com.juanvilla.freshpic.data.source.local

import androidx.datastore.core.DataStore
import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlLocalDataSourceImpl
import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import com.juanvilla.freshpic.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class AgeControlLocalDataSourceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val ageControlPreferencesDataStore: DataStore<ProtoAgeControlPreferences> = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test(expected = Throwable::class)
    fun `Given save is executed, when updateData throws exception, result should be error`() = runBlockingTest {
        val data = buildAgePreferences()
        whenever(
            ageControlPreferencesDataStore.updateData(
                mock()
            )
        ).thenThrow(Throwable())

        val dataSource = buildDataSource()
        val result = dataSource.save(data)

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `Given save is executed, when updateData is success, result should be successful`() = runBlockingTest {
        val data = buildAgePreferences()
        whenever(
            ageControlPreferencesDataStore.updateData(
                mock()
            )
        ).thenReturn(data)

        val dataSource = buildDataSource()
        val result = dataSource.save(data)

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    @Test
    fun `Given find is executed, when saved preferences are empty, result should be successful with generic object`() = runBlockingTest {
        val data = buildGenericPreferences()
        whenever(
            ageControlPreferencesDataStore
                .data
        ).thenReturn(flowOf(data))

        val dataSource = buildDataSource()
        val result = dataSource.find()

        Assert.assertTrue(
            result is ResultType.Success && result.data == data
        )
    }

    @Test
    fun `Given find is executed, when saved preferences are set, result should be successful with set object`() = runBlockingTest {
        val data = buildAgePreferences()
        whenever(
            ageControlPreferencesDataStore
                .data
        ).thenReturn(flowOf(data))

        val dataSource = buildDataSource()
        val result = dataSource.find()

        Assert.assertTrue(
            result is ResultType.Success && result.data == data
        )
    }

    private fun buildDataSource() = AgeControlLocalDataSourceImpl(
        ageControlPreferencesDataStore
    )

    private fun buildGenericPreferences() = ProtoAgeControlPreferences(
        isAdult = null,
        selectedRating = null
    )

    private fun buildAgePreferences() = ProtoAgeControlPreferences(
        isAdult = true,
        selectedRating = "pg-13"
    )
}