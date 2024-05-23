package com.coursework.memo.screens.settings.pages.backside

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coursework.memo.screens.settings.base.SettingsEvent

@Preview(showBackground = true)
@Composable
fun TestPageBackside() {
    PageBackside(
        backsides = listOf(),
        select = {}
    )
}

@Composable
fun PageBackside(
    backsides: List<State<Backside>>,
    select: (SettingsEvent) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
        items(backsides.size) { index ->
            CardBackside(card = backsides[index].value, select = select)
        }
    }
}