package com.coursework.memo.screens.games.componens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.support.GamePaddings
import com.coursework.memo.screens.games.support.GameSettings

@Preview(backgroundColor = 0xFF5CFC0C, showBackground = true)
@Composable
fun ViewGameCard() {
    GameCard(
        modifier = Modifier,
        paddings = GameSettings("", 2, 6, 5).getGamePaddings(),
        state = CardState("", "", false),
        backSide = "",
        index = 2,
        event = {}
    )
}

@Composable
fun GameCard(
    modifier: Modifier,
    paddings: GamePaddings,
    state: CardState?,
    backSide: String,
    index: Int,
    event: (GameEvent) -> Unit,
) {
    val image = if (state != null && state.open)
        state.faceSide ?: backSide else backSide
    Button(
        { if (state != null && !state.open) event(GameEvent.EventClickCard(index)) },
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = paddings.modPadding, vertical = paddings.modPadding),
        shape = RoundedCornerShape(percent = 15),
        colors = ButtonColors(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(1f)
                .border(
                    width = paddings.borderStroke,
                    color = Color.White,
                    shape = RoundedCornerShape(percent = 15)
                )
                .clip(RoundedCornerShape(percent = 15)),
            contentScale = ContentScale.FillBounds,
//            placeholder = BitmapPainter(ImageBitmap.imageResource(R.drawable.test_face_side))
        )
    }
}