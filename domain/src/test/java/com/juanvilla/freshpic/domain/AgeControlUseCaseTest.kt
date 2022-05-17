package com.juanvilla.freshpic.domain

import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.repository.AgeControlRepository
import com.juanvilla.freshpic.domain.usecase.agecontrol.AgeControlUseCaseImpl
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
class AgeControlUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val ageControlRepository: AgeControlRepository = mock()

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
    fun `when saveUserAgeControlPreferences, then repository should be invoked`() = runBlockingTest {
        val data = buildPreferences()
        whenever(
            ageControlRepository.save(data)
        ).thenReturn(ResultType.Success(Unit))

        val useCase = buildUseCase()
        useCase.saveUserAgeControlPreferences(data)

        verify(ageControlRepository).save(data)
    }

    @Test
    fun `when getUserAgeControlPreferences, then repository should be invoked`() = runBlockingTest {
        val data = buildPreferences()
        whenever(
            ageControlRepository.find()
        ).thenReturn(ResultType.Success(data))

        val useCase = buildUseCase()
        useCase.getUserAgeControlPreferences()

        verify(ageControlRepository).find()
    }

    private fun buildPreferences() = AgeControlPreferences(
        isAdult = null,
        selectedRating = null
    )

    private fun buildUseCase() = AgeControlUseCaseImpl(
        ageControlRepository
    )

}