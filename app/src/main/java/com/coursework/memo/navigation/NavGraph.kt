package com.coursework.memo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.main.grobal_variables.GlobalVariables
import com.coursework.memo.main.grobal_variables.GlobalVariablesImpl
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.screens.games.ScreenGame
import com.coursework.memo.screens.games.support.GameSettings
import com.coursework.memo.screens.home.ScreenHome
import com.coursework.memo.screens.rules.ScreenRules
import com.coursework.memo.screens.settings.ScreenSettings
import com.coursework.memo.screens.size.ScreenSize
import com.google.gson.Gson

@Composable
fun NavGraph(settingsData: LocalSettingsData) {

    val navController = rememberNavController()
    val navigator: Navigator = NavigatorImpl(navController)
    val globalVariables: GlobalVariables = GlobalVariablesImpl()
    globalVariables.setVariables(settingsData)

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) { ScreenHome(navigator) }

        composable(Routes.Size.route + "/{game}") { argument ->
            val game = argument.arguments?.getString("game")
            ScreenSize(navigator, game ?: Games.Classic.kind)
        }

        composable(Routes.Game.route + "/{data}") { argument ->
            val data = argument.arguments?.getString("data")
            val variables = globalVariables.data
            val gameSettings = Gson().fromJson(data, GameSettings::class.java).copy(
                backside = variables.backside,
                imagePack = variables.imagePack,
            )
            ScreenGame(navigator, gameSettings)
        }

        composable(Routes.Settings.route) {
            ScreenSettings(navigator, globalVariables)
        }

        composable(Routes.Rules.route) {
            ScreenRules(navigator)
        }
    }

}