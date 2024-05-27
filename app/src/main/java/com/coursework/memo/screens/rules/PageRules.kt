package com.coursework.memo.screens.rules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.coursework.memo.R

@Preview(showSystemUi = true)
@Composable
fun TestPageRules() {
    PageRules(0)
}

@Composable
fun PageRules(page: Int) {
    val title = stringResource(
        when (page) {
            0 -> R.string.classic_game
            1 -> R.string.find_game
            else -> R.string.house_game
        }
    )
    val description = stringResource(
        when (page) {
            0 -> R.string.rules_classic
            1 -> R.string.rules_find
            else -> R.string.rules_house
        }
    )
    Column {
        Text(title, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(description)
    }
}