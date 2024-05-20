package com.coursework.memo.screens.games.base

sealed class GameEvent {
    data object Lion: GameEvent()
    data class Cat(val size: Int): GameEvent()
}