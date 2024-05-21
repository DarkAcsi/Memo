package com.coursework.memo.screens.games.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.support.GameSettings

@Preview(showBackground = true)
@Composable
fun ViewGameTopBar() {
    val navigator = NavigatorImpl(rememberNavController())
    GameTopBar(
        navigator = navigator,
        state = TopBarState(2),
        GameSettings("", 2, 3, 4)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopBar(
    navigator: Navigator,
    state: TopBarState,
    gameSettings: GameSettings,
) {
    TopAppBar(
        title = {
            Row {
                for (i in 0..<state.players) {
                    Player(
                        order = i,
                        score = state.scores[i],
                        leading = i == state.orderPlayers,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        actions = {
            IconButton({ navigator.toPause(gameSettings) }) {
                Icon(Icons.Filled.Home, stringResource(R.string.pause))
            }
        }
    )
}

val colors = listOf(R.color.blue, R.color.red, R.color.yellow, R.color.green)
val names = listOf(R.string.blue, R.string.red, R.string.yellow, R.string.green)

@Composable
fun Player(order: Int, score: Int, leading: Boolean, modifier: Modifier) {
    Box(
        Modifier
            .then(modifier)
            .background(Color(colors[order]), RoundedCornerShape(percent = 10))
    ) {
        Text(stringResource(names[order]))
    }
}