package com.coursework.memo.screens.games.find.drag_and_drop

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.support.GamePaddings
import com.google.gson.Gson

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.cardSourceModifier(index: Int, event: (GameEvent) -> Unit): Modifier {
    return Modifier
        .dragAndDropSource {
            detectTapGestures(
                onTap = { event(GameEvent.EventClickCard(index)) },
                onLongPress = {
                    startTransfer(
                        DragAndDropTransferData(
                            clipData = ClipData.newIntent(
                                "cardIndex",
                                Intent(DragDropConstants.ACTION).apply {
                                    putExtra(
                                        DragDropConstants.DATA,
                                        Gson().toJson(index)
                                    )
                                },
                            )
                        )
                    )
                }
            )
        }
}

@Composable
fun FindGameCard(
    modifier: Modifier,
    paddings: GamePaddings,
    state: CardState,
    backSide: String,
    findModifier: Modifier,
) {
    val image = if (state.open)
        state.faceSide ?: backSide else backSide
    Button(
        {},
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
                .then(if (!state.open && state.faceSide != null) findModifier else Modifier)
                .fillMaxHeight()
                .border(
                    width = paddings.borderStroke,
                    color = Color.White,
                    shape = RoundedCornerShape(percent = 15)
                )
                .clip(RoundedCornerShape(percent = 15)),
            contentScale = ContentScale.Fit,
        )
    }
}