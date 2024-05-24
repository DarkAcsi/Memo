package com.coursework.memo.screens.games.base.states

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf

data class GameState(
    val showCards: Boolean = false,
    val finishedGame: Boolean = false,
    val stepsToWin: MutableIntState = mutableIntStateOf(0)
)