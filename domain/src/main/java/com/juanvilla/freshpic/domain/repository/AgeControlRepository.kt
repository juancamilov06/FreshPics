package com.juanvilla.freshpic.domain.repository

import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.util.ResultType

interface AgeControlRepository {
    suspend fun save(
        ageControlPreferences: AgeControlPreferences
    ): ResultType<Unit>

    suspend fun find(): ResultType<AgeControlPreferences>
}