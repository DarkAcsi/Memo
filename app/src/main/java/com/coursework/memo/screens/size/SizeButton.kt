package com.coursework.memo.screens.size

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SizeButton(sizeText: String, enabled: Boolean, navigate: (rows: Int, column: Int) -> Unit) {
    Button({ navigate(sizeText[0].digitToInt(), sizeText[2].digitToInt()) }, enabled = enabled) {
        Text(sizeText)
    }
}