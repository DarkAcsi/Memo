package com.coursework.memo.screens.settings.base

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Build
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.coursework.memo.R

enum class TabPage(val icon: ImageVector, @StringRes val string: Int) {
    Images(Icons.Sharp.Edit, R.string.tab_images),
    BackSides(Icons.Sharp.Build, R.string.tab_backside),
    Themes(Icons.Sharp.Lock, R.string.tab_theme)
}

@Composable
fun TabSettings(selectedIndex: Int, onSelect:(TabPage) -> Unit) {
    TabRow(selectedTabIndex = selectedIndex, contentColor = Color.LightGray) {
        TabPage.entries.forEachIndexed { index, tabPage ->
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