package com.coursework.memo.screens.games.find

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
import com.coursework.memo.screens.games.base.states.GameState
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.componens.FinishAlertDialog
import com.coursework.memo.screens.games.componens.GameTopBar
import com.coursework.memo.screens.games.find.drag_and_drop.FindGameCard
import com.coursework.memo.screens.games.find.drag_and_drop.PlayerContainer
import com.coursework.memo.screens.games.find.drag_and_drop.cardSourceModifier
import com.coursework.memo.screens.games.support.GameSettings

@Preview(showSystemUi = true)
@Composable
fun TestScreenFind() {
    val listCard = mutableListOf<CardState>()
    for (i in 0..100)
        listCard.add(CardState("", "", false))
    GameFind(
        navigator = NavigatorImpl(rememberNavController()),
        gameSettings = GameSettings(Games.Classic.kind, 4, 6, 5),
        cardStates = listOf(),
        stateTopBar = TopBarState(4),
        gameState = GameState(),
        event = {}
    )
}

@Composable
fun GameFind(
    navigator: Navigator,
    gameSettings: GameSettings,
    cardStates: List<State<CardState>>,
    stateTopBar: TopBarState,
    gameState: GameState,
    event: (GameEvent) -> Unit,
) {
    Scaffold(
        topBar = { GameTopBar(navigator, stateTopBar, gameSettings) },
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
                        FindGameCard(
                            modifier = Modifier.weight(1f),
                            paddings = gamePaddings,
                            state = (cardStates.getOrElse(count) { mutableStateOf(CardState()) }).value,
                            backSide = Constants.PATH_BACKSIDES + gameSettings.backside,
                            findModifier = Modifier.cardSourceModifier(count, event)
                        )
                        count += 1
                    }
                }
            }
        }
        PlayerContainer(modifier = modifierPad, players = stateTopBar.players, event = event)
    }
    if (gameState.finishedGame) {
        fun retryGame() = navigator.retryGame(gameSettings)
        FinishAlertDialog(
            stateTopBar = stateTopBar,
            toHome = navigator::toHome,
            retry = ::retryGame,
        )
    }
}