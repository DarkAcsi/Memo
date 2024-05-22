package com.coursework.memo.screens.games.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.support.GameSettings
import com.coursework.memo.ui.theme.colorsBorder
import com.coursework.memo.ui.theme.colorsBorderWait
import com.coursework.memo.ui.theme.colorsPlayers
import com.coursework.memo.ui.theme.colorsPlayersWait

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun ViewGameTopBar() {
    val navigator = NavigatorImpl(rememberNavController())
    GameTopBar(
        navigator = navigator,
        state = TopBarState(4, 2),
        GameSettings("", 4, 3, 4)
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                for (i in 0..<state.players) {
                    Player(
                        order = i,
                        score = state.scores[i],
                        wait = i != state.orderPlayers,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        actions = {
            IconButton({ navigator.toPause(gameSettings) }) {
                Icon(Icons.Filled.Home, stringResource(R.string.pause))
            }
        },
        colors = TopAppBarColors(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color.Unspecified,
            Color.Unspecified
        )
    )
}

@Composable
fun Player(order: Int, score: Int, wait: Boolean, modifier: Modifier) {
    Box(modifier = Modifier.then(modifier)) {
        Row(
            Modifier
                .fillMaxWidth(0.95f)
                .border(
                    4.dp,
                    if (wait) colorsBorderWait[order] else colorsBorder[order],
                    RoundedCornerShape(percent = 30)
                )
                .background(
                    if (wait) colorsPlayersWait[order] else colorsPlayers[order],
                    RoundedCornerShape(percent = 30)
                )
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "$score",
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
    }
}