package com.coursework.memo.screens.games.base

sealed class GameEvent {

    data class EventClickCard(val index: Int) : GameEvent()
    data class EventDragAndDropCard(val indexCard: Int, val order: Int) : GameEvent()

}