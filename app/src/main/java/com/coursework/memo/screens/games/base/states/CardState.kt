package com.coursework.memo.screens.games.base.states

data class CardState(
    val backSide: String,
    val faceSide: String,
    val open: Boolean = false,
) {
    fun changeOpen(open: Boolean) : CardState {
        return CardState(
            backSide = backSide,
            faceSide = faceSide,
            open = open,
        )
    }
}