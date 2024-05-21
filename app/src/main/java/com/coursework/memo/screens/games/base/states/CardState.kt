package com.coursework.memo.screens.games.base.states

data class CardState(
    val backSide: String,
    val faceSide: String,
    val open: Boolean = false,
)