package com.coursework.memo.screens.games.base

import android.content.Context
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.base.states.GameState
import com.coursework.memo.screens.games.base.states.TopBarState

abstract class GameViewModel : ViewModel() {

    abstract val localSettingsData: State<LocalSettingsData>

    abstract val stateGame: State<GameState>

    abstract val stateTopBar: State<TopBarState>

    abstract val listCards: List<State<CardState>>

    abstract fun initStateTopBar(players: Int)

    abstract fun initCardStates(rows: Int, columns: Int, context: Context)

    abstract fun onEvent(event: GameEvent)

}