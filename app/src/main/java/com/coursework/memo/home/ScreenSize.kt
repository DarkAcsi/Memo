package com.coursework.memo.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.Routes

@Preview(showSystemUi = true)
@Composable
fun TestSize() {
    val navController = rememberNavController()
    ScreenSize(navController).Size()
}

class ScreenSize(private val navController: NavHostController) {

    @Composable
    fun Size() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBarSize() },
            floatingActionButton = {
                IconButton({navController.navigateUp()}) {
                    Icon(Icons.Filled.Home, contentDescription = "Домой")
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
                                    Button({ navController.navigate(Routes.GameClassic.route + "/${c * 2 + b + 4}") }) {
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
    fun TopBarSize() {
        TopAppBar(
            title = {},
            actions = {
                Button({ }) {
                    Row(
                        modifier = Modifier.width(120.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(Icons.Filled.Star, contentDescription = "?")
                        Text(
                            "Звезды",
                            modifier = Modifier.fillMaxWidth(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        )
    }
}