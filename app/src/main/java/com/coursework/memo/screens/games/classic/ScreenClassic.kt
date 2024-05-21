package com.coursework.memo.screens.games.classic

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.componens.GameTopBar
import com.coursework.memo.screens.games.support.GameSettings
import java.util.Collections.shuffle


@Preview(showSystemUi = true)
@Composable
fun ViewScreenClassic() {

}

@Composable
fun ScreenGame(navigator: Navigator, gameSettings: GameSettings) {
    val viewModel: ViewModelClassic = hiltViewModel()
    Scaffold(
        topBar = { GameTopBar(navigator, viewModel.stateTopBar.value, gameSettings) }
    ) { innerPadding ->
        val modifierPad = Modifier.padding(innerPadding)
        GameClassic(navigator, viewModel::onEvent, viewModel.stateTopBar.value, gameSettings, modifierPad)
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

@Composable
private fun GameClassic(
    navigator: Navigator,
    event: (GameEvent) -> Unit,
    state: TopBarState,
    gameSettings: GameSettings,
    modifier: Modifier
) {
//    val images = setImages(game.packImage, game.size)
//    Column(
//        modifier = Modifier
//            .then(modifier)
//            .fillMaxSize()
//            .padding(all = boxPadding.dp),
//    ) {
//        for (row in 0 until game.size) {
//            Row(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(vertical = (modPadding * 1.5).dp)
//            ) {
//                for (column in 0 until game.size - 1) {
//                    Card(
//                        game.packImage,
//                        images[column * game.size + row],
//                        Modifier.weight(1f)
//                    )
//                }
//            }
//        }
//    }
}