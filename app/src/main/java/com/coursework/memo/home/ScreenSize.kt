package com.coursework.memo.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.NavRealization
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.support_classes.GameSupport
import com.coursework.memo.support_classes.SizeSupport

@Preview(showSystemUi = true)
@Composable
fun TestSize() {
    val navigator = NavRealization(rememberNavController())
    ScreenSize(navigator).Screen(SizeSupport("kk", "kj"))
}

class ScreenSize(private val navigator: Navigator) {

    @Composable
    fun Screen(support: SizeSupport) {
        Size(support)
    }

    @Composable
    private fun Size(size: SizeSupport) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBarSize() },
            floatingActionButton = {
                IconButton({ navigator.toHome() }) {
                    Icon(Icons.Filled.Home, stringResource(R.string.home))
                }
            },
            floatingActionButtonPosition = FabPosition.Start

        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row() {
                        Button({}) {
                            Text("2 игрока")
                        }
                    }
                    Column {
                        for (c in 0..2) {
                            Row {
                                for (b in 0..1) {
                                    Button({
                                        navigator.toGame(
                                            size.route,
                                            GameSupport(c * 2 + b + 4, 2, size.packImage)
                                        )
                                    }) {
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopBarSize() {
        TopAppBar(title = {})
    }
}