package com.coursework.memo.navigation

import com.coursework.memo.screens.games.support.GameSettings


interface Navigator {

    fun back()

    fun toHome()

    fun toSize(kindGame: String)

    fun toGame(gameSettings: GameSettings)

    fun toSettings()

    fun retryGame(gameSettings: GameSettings)

}