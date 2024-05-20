package com.coursework.memo.navigation

sealed class Routes(
    val route: String
) {
    data object Home: Routes(route = "homeScreen")
    data object Size: Routes(route = "sizeScreen")
    data object ClassicGame: Routes(route = "classicGameScreen")
    data object FindGame: Routes(route = "findGameScreen")
    data object HouseGame: Routes(route = "houseGameScreen")
    data object Settings: Routes(route = "settingsScreen")
}