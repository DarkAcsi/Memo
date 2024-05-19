package com.coursework.memo.games

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.coursework.memo.R
import com.coursework.memo.navigation.NavRealization
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.support_classes.GameSupport
import java.util.Collections.shuffle

@Preview(showSystemUi = true)
@Composable
fun TestGameClassic() {
    val navigator = NavRealization(rememberNavController())
    ScreenClassic(navigator).Screen(GameSupport(4, 4, "Animals"))
}

class ScreenClassic(private val navigator: Navigator) {

    private var boxPadding = 0
    private var modPadding = 0
    private var borderStroke = 1
    private var viewModel: ViewModelClassic = ViewModelClassic()

    @Composable
    fun Screen(game: GameSupport) {
        Scaffold(
            topBar = { TopBarClassic(game.players) }
        ){ innerPadding ->
            val modifierPad = Modifier.padding(innerPadding)
            setPaddings(game.size)
            Game(game, modifierPad)
        }
    }

    @Composable
    private fun setImages(packImage: String, sizeDef: Int): List<String> {
        val images = LocalContext.current.assets.list("images/$packImage")?.toList()
        shuffle(images!!)
        val size = sizeDef * (sizeDef - 1) / 2
        val images2 = images.slice(1..size) + images.slice(1..size)
        shuffle(images2)
        return images2
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
    private fun Game(game: GameSupport, modifier: Modifier) {
        val images = setImages(game.packImage, game.size)
        Column(
                modifier = Modifier
                    .then(modifier)
                    .fillMaxSize()
                    .padding(all = boxPadding.dp),
            ) {
            for (row in 0 until game.size) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = (modPadding * 1.5).dp)
                ) {
                    for (column in 0 until game.size - 1) {
                        Card(
                            game.packImage,
                            images[column * game.size + row],
                            Modifier.weight(1f)
                        )
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
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.pause))
                }
            },
            actions = {
                IconButton({ navigator.toHome() }) {
                    Icon(Icons.Filled.Home, stringResource(R.string.home))
                }
            }
        )
    }

    @Composable
    private fun Card(
        imagePack: String,
        image: String,
        modifier: Modifier,
    ) {
        AsyncImage(
            model = "file:///android_asset/images/$imagePack/$image",
            contentDescription = null,
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