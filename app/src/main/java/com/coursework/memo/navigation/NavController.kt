package com.coursework.memo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.games.GameClassic
import com.coursework.memo.home.ScreenHome
import com.coursework.memo.home.ScreenSize
import kotlinx.serialization.json.Json


class NavController {

    @Composable
    fun SetNavigation() {
        val navController = rememberNavController()
        val navigator: Navigator = NavRealization(navController)
        NavHost(navController = navController, startDestination = Routes.HOME) {

            composable(Routes.HOME) { ScreenHome(navigator).Screen() }

            composable(Routes.SIZE + "?data={data}") { argument ->
                val data = argument.arguments?.getString("data")
                ScreenSize(navigator).Screen(Json.decodeFromString(data!!))
            }

            composable(Routes.CLASSIC + "?data={data}") { argument ->
                val data = argument.arguments?.getString("data")
                GameClassic(navigator).Screen(Json.decodeFromString(data!!))
            }
        }
    }
}