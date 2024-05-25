package com.coursework.memo.screens.games.support

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp

@Stable
data class GameSettings(
    val kindGame: String,
    val players: Int,
    val rows: Int,
    val columns: Int,
    val backside: String = "",
    val imagePack: String = "",
) {

    fun getGamePaddings(): GamePaddings {
        return when (columns) {
            3 -> GamePaddings(
                boxPadding = 0.dp,
                modPadding = 8.dp,
                borderStroke = 3.dp
            )

            4, 5 -> GamePaddings(
                boxPadding = 2.dp,
                modPadding = 6.dp,
                borderStroke = 2.dp
            )

            6 -> GamePaddings(
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
