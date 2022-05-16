package com.juanvilla.freshpic.ui.screens.age

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.usecase.agecontrol.AgeControlUseCase
import com.juanvilla.freshpic.domain.util.Constants
import com.juanvilla.freshpic.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val ageControlUseCase: AgeControlUseCase,
    @Named("IoDispatcher") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _preferencesSetSource = MediatorLiveData<Unit?>()
    val preferencesSetSource: LiveData<Unit?> = _preferencesSetSource

    fun setAgePrefs(adult: Boolean) {
        val ageControlPreferences = AgeControlPreferences(
            isAdult = adult,
            selectedRating = if (adult) {
                Constants.R_RATING
            } else {
                Constants.PG13_RATING
            }
        )

        viewModelScope.launch(ioDispatcher) {
            val result = ageControlUseCase.saveUserAgeControlPreferences(
                ageControlPreferences
            )
            when (result) {
                is ResultType.Success -> {
                    _preferencesSetSource.postValue(Unit)
                }
                is ResultType.Error -> {
                    _preferencesSetSource.postValue(null)
                }
            }
        }
    }
}