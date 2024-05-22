package com.coursework.memo.screens.games.componens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.screens.games.base.GameViewModel
import com.coursework.memo.screens.games.classic.GameClassic
import com.coursework.memo.screens.games.classic.ViewModelClassic
import com.coursework.memo.screens.games.support.GameSettings

@Composable
fun ScreenGame(
    navigator: Navigator,
    gameSettings: GameSettings
) {
    lateinit var viewModel: GameViewModel
    when (gameSettings.kindGame) {
        Games.Classic.kind -> viewModel = hiltViewModel<ViewModelClassic>()
        Games.Find.kind -> viewModel = hiltViewModel<ViewModelClassic>()
        Games.House.kind -> viewModel = hiltViewModel<ViewModelClassic>()
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.stateGame.value.loadedData) {
        viewModel.initStateTopBar(gameSettings.players)
        viewModel.initCardStates(gameSettings.rows, gameSettings.columns, context)
    }

    when (gameSettings.kindGame) {
        Games.Classic.kind -> {
            GameClassic(
                navigator = navigator,
                gameSettings = gameSettings,
                cardStates = viewModel.listCards,
                stateTopBar = viewModel.stateTopBar.value,
                event = viewModel::onEvent
            )
        }

        Games.Find.kind -> {}
        Games.House.kind -> {}
    }
}