package com.juanvilla.freshpic.domain.usecase.agecontrol

import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.repository.AgeControlRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class AgeControlUseCaseImpl @Inject constructor(
    private val ageControlRepository: AgeControlRepository
) : AgeControlUseCase {

    override suspend fun saveUserAgeControlPreferences(ageControlPreferences: AgeControlPreferences): ResultType<Unit> =
        ageControlRepository.save(ageControlPreferences)

    override suspend fun getUserAgeControlPreferences(): ResultType<AgeControlPreferences> =
        ageControlRepository.find()
}