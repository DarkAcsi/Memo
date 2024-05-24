package com.coursework.memo.screens.games.componens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.coursework.memo.R
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.ui.theme.colorsBorderWait
import com.coursework.memo.ui.theme.colorsPlayersWait

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestFinishAlertDialog() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FinishAlertDialog(
            stateTopBar = TopBarState(3, 1, listOf(3, 1, 2, 0)),
            {},
            {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishAlertDialog(
    stateTopBar: TopBarState,
    toHome: () -> Unit,
    retry: () -> Unit,
) {
    val results = resultsGame(stateTopBar.scores)
    val text =
        if (results[0] > 1) {
            stringResource(R.string.game_tie)
        } else if (results[1] > 0) stringResource(R.string.game_win, stringResource(R.string.blue))
        else if (results[2] > 0) stringResource(R.string.game_win, stringResource(R.string.red))
        else if (results[3] > 0) stringResource(R.string.game_win, stringResource(R.string.yellow))
        else stringResource(R.string.game_win, stringResource(R.string.green))

    BasicAlertDialog(
        onDismissRequest = {},
        modifier = Modifier
            .wrapContentSize()
            .background(Color.LightGray, shape = RoundedCornerShape(percent = 15))
            .padding(16.dp),
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text, fontSize = 30.sp)
            for (i in 0 until (stateTopBar.players + 1) / 2) {
                Row(Modifier.wrapContentSize(), horizontalArrangement = Arrangement.Center) {
                    FinishPlayer(
                        order = i * 2,
                        score = stateTopBar.scores[i * 2],
                        result = results[i * 2 + 1],
                    )
                    if (i == 0 || stateTopBar.players == 4) FinishPlayer(
                        order = i * 2 + 1,
                        score = stateTopBar.scores[i * 2 + 1],
                        result = results[i * 2 + 2],
                    )
                }
            }
            BottomButtons(toHome, retry)
        }
    }
}

@Composable
private fun FinishPlayer(order: Int, score: Int, result: Int) {
    Box(contentAlignment = Alignment.TopCenter) {
        Box(
            Modifier
                .padding(top = 60.dp)
                .size(width = 120.dp, height = 70.dp)
                .padding(4.dp)
                .border(4.dp, colorsBorderWait[order], RoundedCornerShape(percent = 30))
                .background(colorsPlayersWait[order], RoundedCornerShape(percent = 30))
                .padding(4.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                "$score",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
        Image(
            imageVector = ImageVector.vectorResource(
                id = when (result) {
                    0 -> R.drawable.emoji_lose
                    1 -> R.drawable.emoji_win
                    else -> R.drawable.emoji_tie
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(108.dp)
                .background(Color.Transparent),
        )
    }
}

@Composable
private fun BottomButtons(
    toHome: () -> Unit,
    retry: () -> Unit,
) {
    Row {
        IconButton({ toHome() }) {
            Icon(Icons.Default.Home, stringResource(id = R.string.home))
        }
        IconButton({ retry() }) {
            Icon(Icons.Default.Refresh, stringResource(id = R.string.retry))
        }
    }
}

private fun resultsGame(scores: List<Int>): List<Int> {
    val maxValue = scores.max()
    val winners = scores.count { it == maxValue }
    return listOf(winners) + scores.map { if (it == maxValue) winners else 0 }
}