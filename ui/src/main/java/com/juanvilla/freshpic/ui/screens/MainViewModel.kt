package com.juanvilla.freshpic.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences
import com.juanvilla.freshpic.domain.usecase.agecontrol.AgeControlUseCase
import com.juanvilla.freshpic.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val ageControlUseCase: AgeControlUseCase,
    @Named("IoDispatcher") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _preferencesSource = MediatorLiveData<AgeControlPreferences>()
    val preferencesSource: LiveData<AgeControlPreferences> = _preferencesSource

    fun getSavedAgeControlPreferences() {
        viewModelScope.launch(ioDispatcher) {
            when (val result = ageControlUseCase.getUserAgeControlPreferences()) {
                is ResultType.Success -> {
                    _preferencesSource.postValue(result.data!!)
                }
                is ResultType.Error -> {
                    Log.d(TAG, "Error")
                }
            }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}