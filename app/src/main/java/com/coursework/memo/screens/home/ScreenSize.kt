package com.coursework.memo.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl
import com.coursework.memo.navigation.Routes

@Preview(showSystemUi = true)
@Composable
fun TestSize() {
    val navigator = NavigatorImpl(rememberNavController())
    ScreenSize(navigator, Routes.ClassicGame.route)
}

@Composable
fun ScreenSize(
    navigator: Navigator,
    routeGame: String,
){
    Size(navigator, routeGame)
}

@Composable
private fun Size(navigator: Navigator, routeGame: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Row {
                IconButton({ navigator.toHome() }) {
                    Icon(Icons.Filled.Home, stringResource(R.string.home))
                }
                IconButton({}) {
                    Icon(Icons.Filled.Settings, stringResource(R.string.settings))
                }
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    val players = remember { mutableIntStateOf(0) }
                    Button({players.intValue = (players.intValue + 1) % 3}) {
                        Text("${players.intValue + 2} игрока")
                    }
                }
                Column {
                    for (c in 0..2) {
                        Row {
                            for (b in 0..1) {
                                Button({}) {
                                    Text("${c * 2 + b + 4}x${c * 2 + b + 3}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}