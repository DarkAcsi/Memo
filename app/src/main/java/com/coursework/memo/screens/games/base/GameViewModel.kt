package com.coursework.memo.screens.games.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.ReadSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
abstract class GameViewModel @Inject constructor(
    readSettings: ReadSettings,
    players: Int,
) : ViewModel() {

    private val _localSettingsData = mutableStateOf(LocalSettingsData("", ""))
    val localSettingsData: State<LocalSettingsData> = _localSettingsData

    init {
        readSettings().onEach { data ->
            _localSettingsData.value = data
        }.launchIn(viewModelScope)
    }

    abstract fun onEvent(event: GameEvent)

}