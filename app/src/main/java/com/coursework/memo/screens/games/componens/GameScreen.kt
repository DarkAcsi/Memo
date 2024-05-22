package com.coursework.memo.screens.games.componens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.screens.games.classic.GameClassic
import com.coursework.memo.screens.games.classic.ViewModelClassic
import com.coursework.memo.screens.games.support.GameSettings

@Composable
fun ScreenGame(
    navigator: Navigator,
    gameSettings: GameSettings
) {
    Log.d("Deb", "ScreenGame")
    when (gameSettings.kindGame) {
        Games.Classic.kind -> {
            val viewModel: ViewModelClassic = hiltViewModel()
            val stateTopBar = viewModel.getStateTopBar(gameSettings.players)
            viewModel.initCardStates(gameSettings.rows, gameSettings.columns, LocalContext.current)
            GameClassic(
                navigator = navigator,
                gameSettings = gameSettings,
                cardStates = viewModel.listCardStates,
                stateTopBar = stateTopBar,
                event = viewModel::onEvent
            )
        }

        Games.Find.kind -> {}
        Games.House.kind -> {}
    }
}