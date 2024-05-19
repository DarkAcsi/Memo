package com.coursework.memo.screens.games

data class StateCard(
    val face: String,
    val back: String,
    val view: String = back,
    val withPair: Boolean = false,

    ) {

    fun open() : StateCard? {
        return if (view == face)
            null
        else
            StateCard(face, back, face, withPair)
    }

    fun close() : StateCard? {
        return if (view == back)
            null
        else
            StateCard(face, back, back, withPair)
    }

}