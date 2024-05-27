package com.coursework.memo.screens.size.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coursework.memo.R

@Preview(showBackground = true)
@Composable
fun TestSizePlayers() {
    val s = remember { mutableIntStateOf(2) }
    SizePlayers(players = s)
}

@Composable
fun SizePlayers(players: MutableIntState) {
    Column {
        Text(
            stringResource(id = R.string.players),
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (i in 0..2) {
                Button(
                    { players.intValue = i },
                    modifier = Modifier
                        .border(
                            width = 4.dp,
                            color = if (players.intValue == i) Color.Blue else Color.LightGray,
                            shape = CircleShape
                        ),
                    shape = CircleShape,
                    colors = ButtonColors(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent
                    ),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text("${i + 2}", color = Color.Black)
                }
            }
        }
    }
}