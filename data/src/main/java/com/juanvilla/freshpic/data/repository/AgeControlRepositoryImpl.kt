package com.juanvilla.freshpic.data.repository

import com.juanvilla.freshpic.data.mappers.ProtoAgeMapper
import com.juanvilla.freshpic.data.source.local.agecontrol.AgeControlLocalDataSource
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.repository.AgeControlRepository
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject

class AgeControlRepositoryImpl @Inject constructor(
    private val ageControlLocalDataSource: AgeControlLocalDataSource,
    private val protoAgeMapper: ProtoAgeMapper
) : AgeControlRepository {

    override suspend fun save(ageControlPreferences: AgeControlPreferences): ResultType<Boolean> {
        val mappedAgePreferences = protoAgeMapper.mapDomainToDb(ageControlPreferences)
        return ageControlLocalDataSource.save(mappedAgePreferences)
    }

    override suspend fun find(): ResultType<AgeControlPreferences> {
        return when (val preferences = ageControlLocalDataSource.find()) {
            is ResultType.Success -> {
                ResultType.Success(protoAgeMapper.mapDbToDomain(preferences.data))
            }
            is ResultType.Error -> {
                preferences
            }
        }
    }
}