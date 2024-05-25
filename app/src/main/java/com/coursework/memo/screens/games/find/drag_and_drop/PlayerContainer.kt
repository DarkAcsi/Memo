package com.coursework.memo.screens.games.find.drag_and_drop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.coursework.memo.screens.games.base.GameEvent

@Preview(showSystemUi = true)
@Composable
fun TestPlayerContainer() {
    PlayerContainer(modifier = Modifier, players = 4, event = {})
}

@Composable
fun PlayerContainer(modifier: Modifier, players: Int, event: (GameEvent) -> Unit) {
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            if (players > 2) {
                PlayerSide(order = 2, eventGame = event)
            }
            if (players > 3) {
                PlayerSide(order = 3, eventGame = event)
            }
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            PlayerSide(order = 0, eventGame = event)
            PlayerSide(order = 1, eventGame = event)
        }
    }
}