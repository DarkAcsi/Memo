package com.coursework.memo.screens.games.classic

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.ReadSettings
import com.coursework.memo.preferences.usecases.SaveSettings
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.GameViewModel
import com.coursework.memo.screens.games.base.states.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelClassic @Inject constructor(
    readSettings: ReadSettings,
    saveSettings: SaveSettings,
) : GameViewModel() {

    private val _localSettingsData = mutableStateOf(LocalSettingsData("", ""))
    override val localSettingsData: State<LocalSettingsData> = _localSettingsData

    private  val _stateTopBar = mutableStateOf(TopBarState(2))
    override val stateTopBar: State<TopBarState> = _stateTopBar

    init {
        readSettings().onEach { data ->
            _localSettingsData.value = data
        }.launchIn(viewModelScope)
    }

    override fun onEvent(event: GameEvent){
        when(event){
            is GameEvent.Lion -> {}
            is GameEvent.Cat -> {}
        }
    }


}