package com.coursework.memo.navigation

import androidx.navigation.NavHostController
import com.coursework.memo.screens.games.support.GameSettings
import com.google.gson.Gson

class NavigatorImpl(private val navController: NavHostController): Navigator {

    override fun back(){
        navController.navigateUp()
    }

    override fun toHome(){
        navController.popBackStack(Routes.Home.route, inclusive = false)
    }

    override fun toSize(kindGame: String) {
        navController.navigate(Routes.Size.route + "/" + kindGame)
    }

    override fun toGame(gameSettings: GameSettings){
        navController.navigate(gameSettings.kindGame + "/" + Gson().toJson(gameSettings))
    }

    override fun toSettings() {
        navController.navigate(Routes.Settings.route)
    }

    override fun toPause(gameSettings: GameSettings) {
        navController.navigate(Routes.Pause.route + "/" + Gson().toJson(gameSettings))
    }

    override fun retryGame(gameSettings: GameSettings) {
        navController.getBackStackEntry(Routes.Size.route)
        toGame(gameSettings)
    }

}