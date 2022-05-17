package com.juanvilla.freshpic.data.repository

import com.juanvilla.freshpic.data.mappers.ProtoAgeMapper
import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlLocalDataSource
import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
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
class AgeControlRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val ageControlLocalDataSource: AgeControlLocalDataSource = mock()
    private val protoAgeMapper = ProtoAgeMapper

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
    fun `When save is called, localDatasource save should be invoked`() = runBlockingTest {
        val domainPrefs = getDomainPreferences()
        val dbPrefs = getDbPreferences()

        whenever(
            ageControlLocalDataSource.save(dbPrefs)
        ).thenReturn(ResultType.Success(Unit))

        val repository = buildRepository()
        repository.save(domainPrefs)

        verify(
            ageControlLocalDataSource
        ).save(dbPrefs)
    }

    @Test
    fun `Given save is called, when localDatasource save is success, then result should be success`() = runBlockingTest {
        val domainPrefs = getDomainPreferences()
        val dbPrefs = getDbPreferences()

        whenever(
            ageControlLocalDataSource.save(dbPrefs)
        ).thenReturn(ResultType.Success(Unit))

        val repository = buildRepository()
        val result = repository.save(domainPrefs)

        Assert.assertTrue(
            result is ResultType.Success
        )
    }

    @Test
    fun `Given save is called, when localDatasource save is error, then result should be error`() = runBlockingTest {
        val domainPrefs = getDomainPreferences()
        val dbPrefs = getDbPreferences()

        whenever(
            ageControlLocalDataSource.save(dbPrefs)
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val repository = buildRepository()
        val result = repository.save(domainPrefs)

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `When find is called, localDatasource find should be invoked`() = runBlockingTest {
        val dbPrefs = getDbPreferences()

        whenever(
            ageControlLocalDataSource.find()
        ).thenReturn(ResultType.Success(dbPrefs))

        val repository = buildRepository()
        repository.find()

        verify(
            ageControlLocalDataSource
        ).find()
    }

    @Test
    fun `Given find is called, when localDatasource find is error, then result should be error`() = runBlockingTest {
        whenever(
            ageControlLocalDataSource.find()
        ).thenReturn(ResultType.Error(BaseException("Exception")))

        val repository = buildRepository()
        val result = repository.find()

        Assert.assertTrue(
            result is ResultType.Error
        )
    }

    @Test
    fun `Given find is called, when localDatasource find is success, then result should be success`() = runBlockingTest {
        val domainPrefs = getDomainPreferences()
        val dbPrefs = getDbPreferences()

        whenever(
            ageControlLocalDataSource.find()
        ).thenReturn(ResultType.Success(dbPrefs))

        val repository = buildRepository()
        val result = repository.find()

        Assert.assertTrue(
            result is ResultType.Success && result.data == domainPrefs
        )
    }

    private fun buildRepository() = AgeControlRepositoryImpl(
        ageControlLocalDataSource,
        protoAgeMapper
    )

    private fun getDomainPreferences() = AgeControlPreferences(
        isAdult = true,
        selectedRating = "r"
    )

    private fun getDbPreferences() = ProtoAgeControlPreferences(
        isAdult = true,
        selectedRating = "r"
    )
}