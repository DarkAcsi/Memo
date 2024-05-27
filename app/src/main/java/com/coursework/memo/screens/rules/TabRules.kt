package com.coursework.memo.screens.rules

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.coursework.memo.R

enum class TabPageRules(val icon: ImageVector, @StringRes val string: Int) {
    Classic(Icons.Sharp.Star, R.string.classic_game),
    Find(Icons.Sharp.Search, R.string.find_game),
    House(Icons.Sharp.Home, R.string.house_game)
}

@Composable
fun TabRules(selectedIndex: Int, onSelect:(TabPageRules) -> Unit) {
    TabRow(selectedTabIndex = selectedIndex, contentColor = Color.LightGray) {
        TabPageRules.entries.forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onSelect(tabPage) },
                text = { Text(stringResource(tabPage.string)) },
                icon = { Icon(tabPage.icon, "")},
                unselectedContentColor = Color.DarkGray,
                selectedContentColor = Color.Blue,
            )
        }
    }
}