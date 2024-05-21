package com.coursework.memo.screens.games.componens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.classic.GameClassic
import com.coursework.memo.screens.games.classic.ViewModelClassic
import com.coursework.memo.screens.games.support.GameSettings

@Preview(showSystemUi = true)
@Composable
fun ViewScreenClassic() {
    ScreenGameInner(
        navigator = NavigatorImpl(rememberNavController()),
        gameSettings = GameSettings(Games.Classic.kind, 3, 8, 7),
        backSide = "",
        images = listOf(""),
        stateTopBar = TopBarState(3),
        event = {}
    )
}

@Composable
fun ScreenGame(navigator: Navigator, gameSettings: GameSettings) {
    val viewModel: ViewModelClassic = hiltViewModel()
    val stateTopBar = viewModel.getStateTopBar(gameSettings.players)
    val images = viewModel.getImages(gameSettings.rows, gameSettings.columns, LocalContext.current)
    Log.d("Deb", "ScreenGame")
    ScreenGameInner(
        navigator = navigator,
        gameSettings = gameSettings,
        backSide = viewModel.localSettingsData.value.backSide,
        images = images,
        stateTopBar = stateTopBar,
        event = viewModel::onEvent
    )
}

@Composable
private fun ScreenGameInner(
    navigator: Navigator,
    gameSettings: GameSettings,
    backSide: String,
    images: List<String>,
    stateTopBar: TopBarState,
    event: (GameEvent) -> Unit,
) {
    Log.d("Deb", "ScreenGameInner")
    Scaffold(
        topBar = { GameTopBar(navigator, stateTopBar, gameSettings) },
    ) { innerPadding ->
        val modifierPad = Modifier.padding(innerPadding)

        when (gameSettings.kindGame) {
            Games.Classic.kind -> GameClassic(
                modifier = modifierPad,
                gameSettings = gameSettings,
                backSide = backSide,
                images = images,
                event = event
            )

            Games.Find.kind -> {}
            Games.House.kind -> {}
        }
    }
}