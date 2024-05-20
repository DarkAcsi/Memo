package com.coursework.memo.screens.games.base

data class CardState(
    val backSide: String,
    val faceSide: String,
    val opened: Boolean = false,
)