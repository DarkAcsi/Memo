package com.coursework.memo.screens.games.base.states

data class GameState(
    val loadedData: Boolean = false,
    val showCards: Boolean = false,
    val finishedGame: Boolean = false,
)