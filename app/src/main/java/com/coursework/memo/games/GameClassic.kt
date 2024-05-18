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
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.NavRealization
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.support_classes.GameSupport

@Preview(showSystemUi = true)
@Composable
fun TestGameClassic() {
    val navigator = NavRealization(rememberNavController())
    GameClassic(navigator).Screen(GameSupport(4,4, "Animals"))
}

class GameClassic(private val navigator: Navigator) {

    private var boxPadding = 0
    private var modPadding = 0
    private var borderStroke = 1

    @Composable
    fun Screen(support: GameSupport) {
        setPaddings(support.size)
        Game(support)
    }

    private fun setPaddings(size: Int) {
        when (size) {
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
    private fun Game(game: GameSupport) {
        Scaffold(
            topBar = { TopBarClassic(game.players) },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(all = boxPadding.dp),
            ) {
                for (row in 0 until game.size) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = (modPadding * 1.5).dp)
                            .background(Color.Red)
                    ) {
                        for (column in 0 until game.size - 1) {
                            Card(Modifier.weight(1f))
                        }
                    }
                }

            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopBarClassic(players: Int) {
        TopAppBar(
            title = { Text("$players " + stringResource(R.string.players)) },
            navigationIcon = {
                IconButton({}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back))
                }
            },
            actions = {
                IconButton({ navigator.toHome() }) {
                    Icon(Icons.Filled.Home, stringResource(R.string.home))
                }
                IconButton({}) {
                    Icon(Icons.Filled.Settings, stringResource(R.string.settings))
                }
            }
        )
    }

    @Composable
    private fun Card(
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