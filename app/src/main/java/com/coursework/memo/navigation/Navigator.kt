package com.coursework.memo.navigation

import com.coursework.memo.screens.support_classes.GameSupport


interface Navigator {

    fun back()

    fun toHome()

    fun toSize(routeGame: String)

    fun toGame(route: String, support: GameSupport)

    fun toSettings()

}