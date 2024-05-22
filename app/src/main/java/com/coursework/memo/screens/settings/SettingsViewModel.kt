package com.coursework.memo.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coursework.memo.main.grobal_variables.GlobalVariables
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.SaveSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    saveSettings: SaveSettings
) : ViewModel() {

    private val _settingData = mutableStateOf(LocalSettingsData())
    val settingsData: State<LocalSettingsData> = _settingData

    fun initSettingsData(globalVariables: GlobalVariables){
        _settingData.value = globalVariables.data
    }

}