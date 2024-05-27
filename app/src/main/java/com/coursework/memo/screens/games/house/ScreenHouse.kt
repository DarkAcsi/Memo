package com.coursework.memo.screens.games.house

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.coursework.memo.screens.games.componens.GameCard
import com.coursework.memo.screens.games.componens.GameTopBar
import com.coursework.memo.screens.games.support.GameSettings
import com.coursework.memo.ui.theme.backgroundIndicator

@Preview(showSystemUi = true)
@Composable
fun TestScreenHouse() {
    val listCard = mutableListOf<CardState>()
    for (i in 0..100)
        listCard.add(CardState("", "", false))
    GameHouse(
        navigator = NavigatorImpl(rememberNavController()),
        gameSettings = GameSettings(Games.Classic.kind, 3, 7, 7),
        cardStates = listOf(),
        stateTopBar = TopBarState(3),
        gameState = GameState(),
        event = {}
    )
}

@Composable
fun GameHouse(
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(gameSettings.rows.toFloat() / 3)
                    .wrapContentSize(),
                contentAlignment = Alignment.TopEnd,
            ) {
                GameCard(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(),
                    paddings = gamePaddings,
                    state = (cardStates.getOrElse(cardStates.size - gameState.stepsToWin.intValue) {
                        mutableStateOf(
                            CardState()
                        )
                    }).value,
                    backSide = Constants.PATH_BACKSIDES + gameSettings.backside,
                    index = gameState.stepsToWin.intValue,
                    event = {},
                )
                Text(
                    "${gameState.stepsToWin.intValue}",
                    Modifier
                        .padding(8.dp)
                        .background(backgroundIndicator, CircleShape)
                        .padding(8.dp),
                    fontSize = 20.sp,
                )
            }

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
    if (gameState.finishedGame) {
        fun retryGame() = navigator.retryGame(gameSettings)
        FinishAlertDialog(
            stateTopBar = stateTopBar,
            toHome = navigator::toHome,
            retry = ::retryGame,
        )
    }
}