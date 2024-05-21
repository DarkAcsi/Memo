package com.coursework.memo.screens.games.base.states

data class TopBarState(
    val players: Int,
    val orderPlayers: Int = 0,
    val scores: List<Int> = listOf(0, 0, 0, 0),
)