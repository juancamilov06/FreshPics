package com.juanvilla.freshpic.data.source.local.agecontrol

import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import com.juanvilla.freshpic.domain.util.ResultType

interface AgeControlLocalDataSource {
    suspend fun save(ageControlPreferences: ProtoAgeControlPreferences): ResultType<Boolean>
    suspend fun find(): ResultType<ProtoAgeControlPreferences>
}