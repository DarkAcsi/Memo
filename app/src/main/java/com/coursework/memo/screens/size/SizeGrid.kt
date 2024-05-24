package com.coursework.memo.screens.size

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun SizeClassic(navigate: (rows: Int, column: Int) -> Unit, players: Int) {
    Column {
        Row {
            SizeButton(sizeText = "4x3", enabled = players < 3, navigate = navigate)
            SizeButton(sizeText = "5x4", enabled = players < 4, navigate = navigate)
        }
        Row {
            SizeButton(sizeText = "6x5", enabled = true, navigate = navigate)
            SizeButton(sizeText = "7x6", enabled = true, navigate = navigate)
        }
        Row {
            SizeButton(sizeText = "8x7", enabled = true, navigate = navigate)
            SizeButton(sizeText = "9x8", enabled = true, navigate = navigate)
        }
    }
}