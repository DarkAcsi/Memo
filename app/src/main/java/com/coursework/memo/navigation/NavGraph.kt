package com.coursework.memo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.screens.ScreenHome
import com.coursework.memo.screens.games.classic.ScreenGame
import com.coursework.memo.screens.games.support.GameSettings
import com.coursework.memo.screens.size.ScreenSize
import com.google.gson.Gson

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navigator: Navigator = NavigatorImpl(navController)
    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) { ScreenHome(navigator) }

        composable(Routes.Size.route + "/{game}") { argument ->
            val game = argument.arguments?.getString("game")
            ScreenSize(navigator, game ?: Games.Classic.kind)
        }

        composable(Routes.Game.route + "/{data}") { argument ->
            val data = argument.arguments?.getString("data")
            ScreenGame(navigator, Gson().fromJson(data, GameSettings::class.java))
        }

        composable(Routes.Settings.route) {
            // todo settings
        }

        composable(Routes.Pause.route){
            // todo pause
        }
    }
}