package com.coursework.memo.screens.games.componens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.support.GamePaddings
import com.coursework.memo.screens.games.support.GameSettings

@Preview(showBackground = true)
@Composable
fun ll() {
    Button({},
        shape = RectangleShape,
        colors = ButtonColors(Color.Transparent, Color.Transparent, Color.Transparent, Color.Transparent)) {
        Image(
            painter = ColorPainter(Color.Red),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(percent = 10)
                )
                .clip(RoundedCornerShape(percent = 10))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ViewCard() {
    val paddings = GameSettings("ll", 3, 4,5).getGamePaddings()
    Card(
        state = CardState(
            "file:///android_asset/images/backs/1.jpg",
            "file:///android_asset/images/Animals/1.jpg",
            open = false
        ),
        paddings,
        Modifier
    )
}

@Composable
fun Card(
    state: CardState,
    paddings: GamePaddings,
    modifier: Modifier,
) {
    val image = if (state.open) state.faceSide else state.backSide
    Button({},
        shape = RectangleShape,
        colors = ButtonColors(Color.Transparent, Color.Transparent, Color.Transparent, Color.Transparent)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .diskCachePolicy(CachePolicy.DISABLED)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .data(image),
            contentDescription = null,
            modifier = Modifier
                .then(modifier)
                .padding(horizontal = paddings.modPadding)
                .border(
                    width = paddings.borderStroke,
                    color = Color.White,
                    shape = RoundedCornerShape(percent = 10)
                )
                .clip(RoundedCornerShape(percent = 10)),
            contentScale = ContentScale.Crop,
            )
    }
}