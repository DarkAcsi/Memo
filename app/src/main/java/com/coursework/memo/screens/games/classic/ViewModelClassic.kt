package com.coursework.memo.screens.games.classic

import androidx.compose.runtime.mutableStateOf
import com.coursework.memo.preferences.usecases.ReadSettings
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.GameState
import com.coursework.memo.screens.games.base.GameViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelClassic @Inject constructor(readSettings: ReadSettings, players: Int) :
    GameViewModel(readSettings, players) {

    var state = mutableStateOf(GameState(players))
        private set

    override fun onEvent(event: GameEvent){
        when(event){
            is GameEvent.Lion -> {}
            is GameEvent.Cat -> {}
        }
    }


}