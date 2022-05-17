package com.juanvilla.freshpic.domain.usecase.agecontrol

import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.util.ResultType

interface AgeControlUseCase {
    suspend fun saveUserAgeControlPreferences(
        ageControlPreferences: AgeControlPreferences
    ): ResultType<Unit>

    suspend fun getUserAgeControlPreferences(): ResultType<AgeControlPreferences>
}