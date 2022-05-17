package com.juanvilla.freshpic.data.source.local.agecontrol

import androidx.datastore.core.DataStore
import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.util.ResultType
import javax.inject.Inject
import kotlinx.coroutines.flow.first

class AgeControlLocalDataSourceImpl @Inject constructor(
    private val ageControlPreferencesDataStore: DataStore<ProtoAgeControlPreferences>
) : AgeControlLocalDataSource {
    override suspend fun save(ageControlPreferences: ProtoAgeControlPreferences): ResultType<Unit> {
        return try {
            ageControlPreferencesDataStore.updateData {
                it.copy(
                    isAdult = ageControlPreferences.isAdult,
                    selectedRating = ageControlPreferences.selectedRating
                )
            }
            ResultType.Success(Unit)
        } catch (error: Throwable) {
            ResultType.Error(
                error = BaseException("")
            )
        }
    }

    override suspend fun find(): ResultType<ProtoAgeControlPreferences> {
        val currentPreferences = ageControlPreferencesDataStore
            .data
            .first()
        return ResultType.Success(currentPreferences)
    }
}