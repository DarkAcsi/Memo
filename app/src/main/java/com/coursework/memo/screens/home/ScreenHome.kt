package com.coursework.memo.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.Games
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl

@Preview(showSystemUi = true)
@Composable
fun ViewScreenHome() {
    val navigator = NavigatorImpl(rememberNavController())
    ScreenHome(navigator)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(navigator: Navigator) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {},
                actions = {
                    IconButton({ navigator.toRules() }) {
                        Icon(Icons.Outlined.Info, stringResource(R.string.rules))
                    }
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
                Text(stringResource(R.string.app_name), fontSize = 30.sp)
                Button({ navigator.toSize(Games.Classic.kind) }) {
                    Text(stringResource(R.string.classic_game))
                }
                Button({ navigator.toSize(Games.Find.kind) }) {
                    Text(stringResource(R.string.find_game))
                }
                Button({ navigator.toSize(Games.House.kind) }) {
                    Text(stringResource(R.string.house_game))
                }
            }
        }
    }
}