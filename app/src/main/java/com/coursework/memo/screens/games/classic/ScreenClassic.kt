package com.coursework.memo.screens.games.classic

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.componens.GameCard
import com.coursework.memo.screens.games.support.GameSettings

@Preview(showBackground = true, backgroundColor = 0xFF5CFC0C, showSystemUi = true)
@Composable
fun ViewScreenClassic() {
    GameClassic(
        modifier = Modifier,
        gameSettings = GameSettings("", 2, 4, 3),
        backSide = "",
        images = listOf(),
        event = {}
    )
}

@Composable
fun GameClassic(
    modifier: Modifier,
    gameSettings: GameSettings,
    backSide: String,
    images: List<String>,
    event: (GameEvent) -> Unit,
) {
    Log.d("Deb", "GameClassic")
    val gamePaddings = gameSettings.getGamePaddings()
    Column(
        modifier = Modifier
            .then(modifier)
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
                    Log.d("Deb", "card $count")
                    GameCard(
                        modifier = Modifier.weight(1f),
                        paddings = gamePaddings,
                        state = CardState(backSide, images.getOrElse(count){""}, false),
                        event = event
                    )
                    count += 1
                }
            }
        }
    }
}