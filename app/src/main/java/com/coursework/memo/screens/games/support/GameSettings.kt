package com.coursework.memo.screens.games.support

import androidx.compose.ui.unit.dp

data class GameSettings(
    val kindGame: String,
    val players: Int,
    val rows: Int,
    val columns: Int,
) {

    fun getGamePaddings(): GamePaddings {
        return when (rows) {
            4 -> GamePaddings(
                boxPadding = 0.dp,
                modPadding = 8.dp,
                borderStroke = 3.dp
            )

            5, 6 -> GamePaddings(
                boxPadding = 2.dp,
                modPadding = 6.dp,
                borderStroke = 2.dp
            )

            7, 8 -> GamePaddings(
                boxPadding = 4.dp,
                modPadding = 4.dp,
                borderStroke = 2.dp
            )

            else -> GamePaddings(
                boxPadding = 6.dp,
                modPadding = 2.dp,
                borderStroke = 1.dp
            )
        }
    }
}