package com.coursework.memo.screens.games.base.states

data class TopBarState(
    val players: Int,
    val orderPlayers: Int = 0,
    val scores: List<Int> = listOf(0, 0, 0, 0),
) {

    fun nextStep(win: Boolean) : TopBarState {
        val newScores = scores.toMutableList()
        if (win)
            newScores[orderPlayers] += 1
        return TopBarState(
            players = players,
            orderPlayers = if (win) orderPlayers else (orderPlayers + 1) % players,
            scores = newScores.toList()
        )
    }

}