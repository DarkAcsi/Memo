package com.coursework.memo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.screens.games.ScreenClassic
import com.coursework.memo.screens.home.ScreenHome
import com.coursework.memo.screens.home.ScreenSize
import com.coursework.memo.screens.support_classes.GameSupport
import com.coursework.memo.screens.support_classes.SizeSupport
import com.google.gson.Gson


class NavController {

    @Composable
    fun SetNavigation() {
        val navController = rememberNavController()
        val navigator: Navigator = NavRealization(navController)
        NavHost(navController = navController, startDestination = Routes.HOME) {

            composable(Routes.HOME) { ScreenHome(navigator).Screen() }

            composable(Routes.SIZE + "/{data}") { argument ->
                val data = argument.arguments?.getString("data")
                ScreenSize(navigator).Screen(Gson().fromJson(data, SizeSupport::class.java))
            }

            composable(Routes.CLASSIC + "/{data}") { argument ->
                val data = argument.arguments?.getString("data")
                ScreenClassic(navigator, Gson().fromJson(data, GameSupport::class.java)).Screen()
            }
        }
    }
}