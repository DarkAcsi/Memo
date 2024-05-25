package com.coursework.memo.screens.size

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl
import com.coursework.memo.screens.games.support.GameSettings
import com.coursework.memo.screens.size.components.SizeClassic
import com.coursework.memo.screens.size.components.SizeHouse

@Preview(showSystemUi = true)
@Composable
fun TestSize() {
    val navigator = NavigatorImpl(rememberNavController())
    ScreenSize(navigator, Games.Classic.kind)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSize(navigator: Navigator, kindGame: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton({ navigator.back() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back))
                    }
                },
                actions = {
                    IconButton({ navigator.toSettings() }) {
                        Icon(Icons.Filled.Settings, stringResource(R.string.settings))
                    }
                }
            )
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val players = remember { mutableIntStateOf(0) }
                Row {
                    Button({ players.intValue = (players.intValue + 1) % 3 }) {
                        Text("${players.intValue + 2} " + stringResource(R.string.players))
                    }
                }
                fun navigate(rows: Int, columns: Int) {
                    navigator.toGame(
                        GameSettings(
                            kindGame = kindGame,
                            players = players.intValue + 2,
                            rows = rows,
                            columns = columns
                        )
                    )
                }
                when (kindGame) {
                    Games.Classic.kind -> SizeClassic(::navigate, players.intValue + 2)
                    Games.House.kind -> SizeHouse(::navigate, players.intValue + 2)
                }
            }
        }
    }
}