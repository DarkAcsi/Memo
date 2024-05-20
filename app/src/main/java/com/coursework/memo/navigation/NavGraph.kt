package com.coursework.memo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.screens.games.ScreenClassic
import com.coursework.memo.screens.home.ScreenHome
import com.coursework.memo.screens.home.ScreenSize
import com.coursework.memo.screens.support_classes.GameSupport
import com.google.gson.Gson

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navigator: Navigator = NavigatorImpl(navController)
    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) { ScreenHome(navigator) }

        composable(Routes.Size.route + "/{routeGame}") { argument ->
            val game = argument.arguments?.getString("routeGame")
            ScreenSize(navigator, game ?: Routes.ClassicGame.route)
        }

        composable(Routes.ClassicGame.route + "/{data}") { argument ->
            val data = argument.arguments?.getString("data")
            ScreenClassic(navigator, Gson().fromJson(data, GameSupport::class.java)).Screen()
        }

        composable(Routes.FindGame.route + "/{data}") { argument ->
            val data = argument.arguments?.getString("data")
            // todo screen find game
        }

        composable(Routes.HouseGame.route + "/{data}") { argument ->
            val data = argument.arguments?.getString("data")
            // todo screen house game
        }

        composable(Routes.Settings.route) {
            // todo settings
        }

    }
}