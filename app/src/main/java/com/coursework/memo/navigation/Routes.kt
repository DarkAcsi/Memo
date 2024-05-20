package com.coursework.memo.navigation

sealed class Routes(
    val route: String
) {
    data object Home: Routes(route = "homeScreen")
    data object Size: Routes(route = "sizeScreen")
    data object Game: Routes(route = "gameScreen")
    data object Settings: Routes(route = "settingsScreen")
    data object Pause: Routes(route = "pauseScreen")
}