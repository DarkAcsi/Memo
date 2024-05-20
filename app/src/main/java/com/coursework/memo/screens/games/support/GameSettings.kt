package com.coursework.memo.screens.games.support

data class GameSettings(
    val kindGame: String,
    val players: Int,
    val rows: Int,
    val columns: Int,
) {

    fun getGamePaddings(): GamePaddings {
        return when (rows) {
            4 -> GamePaddings(
                boxPadding = 0,
                modPadding = 8,
                borderStroke = 3
            )

            5, 6 -> GamePaddings(
                boxPadding = 2,
                modPadding = 6,
                borderStroke = 2
            )

            7, 8 -> GamePaddings(
                boxPadding = 4,
                modPadding = 4,
                borderStroke = 2
            )

            else -> GamePaddings(
                boxPadding = 6,
                modPadding = 2,
                borderStroke = 1
            )
        }
    }
}
