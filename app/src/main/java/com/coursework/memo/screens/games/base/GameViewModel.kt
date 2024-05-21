package com.coursework.memo.screens.games.base

import android.content.Context
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.screens.games.base.states.TopBarState

abstract class GameViewModel : ViewModel() {

    abstract val localSettingsData: State<LocalSettingsData>

    abstract fun getStateTopBar(players: Int): TopBarState

    abstract fun onEvent(event: GameEvent)

    abstract fun getImages(rows: Int, columns: Int, context: Context): List<String>

}