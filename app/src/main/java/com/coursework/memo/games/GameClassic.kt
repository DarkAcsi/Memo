package com.coursework.memo.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.Routes

@Preview(showSystemUi = true)
@Composable
fun TestGameClassic() {
    val navController = rememberNavController()
    GameClassic(navController).Game(4)
}

class GameClassic(private val navController: NavHostController) {

    private var boxPadding = 0
    private var modPadding = 0
    private var borderStroke = 1

    private fun paddings(rows: Int) {
        when (rows) {
            4 -> {
                modPadding = 8
                borderStroke = 3
            }

            5, 6 -> {
                boxPadding = 2
                modPadding = 6
                borderStroke = 2
            }

            7, 8 -> {
                boxPadding = 4
                modPadding = 4
                borderStroke = 2
            }

            9 -> {
                boxPadding = 6
                modPadding = 2
            }
        }
    }

    @Composable
    fun Game(rows: Int) {
        paddings(rows)
        Scaffold(
            topBar = { TopBarClassic1() },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(all = boxPadding.dp),
            ) {
                for (row in 0 until rows) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = (modPadding * 1.5).dp)
                            .background(Color.Red)
                    ) {
                        for (column in 0 until rows - 1) {
                            Card(Modifier.weight(1f))
                        }
                    }
                }

            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarClassic1() {
        TopAppBar(
            title = { Text("Пар:   Время:") },
            navigationIcon = {
                IconButton({}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")}
            },
            actions = {
                IconButton({
//                    navController.navigate(Routes.Home.route)
                    navController.popBackStack(Routes.Home.route, inclusive = false)
                }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
            }
        )
    }

    @Composable
    fun Card(
        modifier: Modifier = Modifier,
        image: String = ""
    ) {
        Image(
            painter = ColorPainter(Color.DarkGray),
            contentDescription = "",
            modifier = Modifier
                .then(modifier)
                .padding(horizontal = modPadding.dp)
                .border(
                    width = borderStroke.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(percent = 10)
                )
                .clip(RoundedCornerShape(percent = 10))
        )
    }
}