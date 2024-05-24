package com.coursework.memo.screens.games.classic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.main.grobal_variables.Constants
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.componens.FinishAlertDialog
import com.coursework.memo.screens.games.componens.GameCard
import com.coursework.memo.screens.games.componens.GameTopBar
import com.coursework.memo.screens.games.support.GameSettings


@Preview(showSystemUi = true)
@Composable
fun ViewScreenClassic() {
    val listCard = mutableListOf<CardState>()
    for (i in 0..100)
        listCard.add(CardState("", "", false))
    GameClassic(
        navigator = NavigatorImpl(rememberNavController()),
        gameSettings = GameSettings(Games.Classic.kind, 3, 8, 7),
        cardStates = listOf(),
        stateTopBar = TopBarState(3),
        finishGame = true,
        event = {}
    )
}

@Composable
fun GameClassic(
    navigator: Navigator,
    gameSettings: GameSettings,
    cardStates: List<State<CardState>>,
    stateTopBar: TopBarState,
    finishGame: Boolean,
    event: (GameEvent) -> Unit,
) {
    Scaffold(
        topBar = { GameTopBar(navigator, stateTopBar) },
    ) { innerPadding ->
        val modifierPad = Modifier.padding(innerPadding)
        val gamePaddings = gameSettings.getGamePaddings()
        Column(
            modifier = Modifier
                .then(modifierPad)
                .fillMaxSize()
                .padding(gamePaddings.boxPadding),
        ) {
            var count = 0
            for (row in 0 until gameSettings.rows) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    for (column in 0 until gameSettings.columns) {
                        GameCard(
                            modifier = Modifier.weight(1f),
                            paddings = gamePaddings,
                            state = (cardStates.getOrElse(count) { mutableStateOf(CardState()) }).value,
                            backSide = Constants.PATH_BACKSIDES + gameSettings.backside,
                            index = count,
                            event = event
                        )
                        count += 1
                    }
                }
            }
        }
    }
    if (finishGame) {
        fun retryGame() = navigator.retryGame(gameSettings)
        FinishAlertDialog(
            stateTopBar = stateTopBar,
            toHome = navigator::toHome,
            retry = ::retryGame,
        )
    }
}