package com.coursework.memo.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.memo.main.grobal_variables.Constants
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.ReadSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    readSettings: ReadSettings
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _settingData = mutableStateOf(LocalSettingsData("", ""))
    val settingsData: State<LocalSettingsData> = _settingData

    init {
        readSettings().onEach { data ->
            _settingData.value = data
            if (settingsData.value.isEmpty())
                _settingData.value = Constants.DEFAULT_LOCAL_SETTINGS_DATA
            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}