package com.coursework.memo.screens.games

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.screens.games.base.GameViewModel
import com.coursework.memo.screens.games.classic.GameClassic
import com.coursework.memo.screens.games.classic.ViewModelClassic
import com.coursework.memo.screens.games.house.GameHouse
import com.coursework.memo.screens.games.house.ViewModelHouse
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
        Games.House.kind -> viewModel = hiltViewModel<ViewModelHouse>()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.initStateTopBar(gameSettings.players)
        viewModel.initCardStates(gameSettings, context)
    }

    when (gameSettings.kindGame) {
        Games.Classic.kind -> GameClassic(
            navigator = navigator,
            gameSettings = gameSettings,
            cardStates = viewModel.listCards,
            stateTopBar = viewModel.stateTopBar.value,
            finishGame = viewModel.stateGame.value.finishedGame,
            event = viewModel::onEvent
        )

        Games.Find.kind -> {}
        Games.House.kind -> GameHouse(
            navigator = navigator,
            gameSettings = gameSettings,
            cardStates = viewModel.listCards,
            stateTopBar = viewModel.stateTopBar.value,
            gameState = viewModel.stateGame.value,
            event = viewModel::onEvent
        )
    }
}