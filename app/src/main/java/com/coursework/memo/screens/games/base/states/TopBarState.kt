package com.coursework.memo.screens.games.base.states

data class TopBarState(
    val players: Int,
    val orderPlayers: Int = 0,
    val scores: List<Int> = listOf(0, 0, 0, 0),
) {

    fun nextStep(win: Boolean): TopBarState {
        val newScores = scores.toMutableList()
        if (win)
            newScores[orderPlayers] += 1
        return TopBarState(
            players = players,
            orderPlayers = (if (win) orderPlayers else (orderPlayers + 1)) % players,
            scores = newScores.toList()
        )
    }

    fun nextStepFindGame(): TopBarState {
        return TopBarState(
            players = players,
            orderPlayers = (orderPlayers + 1) % players,
            scores = scores
        )
    }

    fun resultFindGame(player:Int, win: Boolean): TopBarState {
        val newScores = scores.toMutableList()
        if (win)
            newScores[player] += 1
        else if (newScores[player] > 0)
            newScores[player] -= 1
        return TopBarState(
            players = players,
            orderPlayers = (orderPlayers + 1) % players,
            scores = newScores.toList()
        )
    }

}