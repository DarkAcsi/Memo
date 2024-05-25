package com.coursework.memo.screens.games.find.drag_and_drop

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.ui.theme.colorsPlayers
import com.coursework.memo.ui.theme.colorsPlayersWait

private val shapes = listOf(
    RoundedCornerShape(topEndPercent = 100),
    RoundedCornerShape(topStartPercent = 100),
    RoundedCornerShape(bottomEndPercent = 100),
    RoundedCornerShape(bottomStartPercent = 100)
)

@Preview(showBackground = true)
@Composable
fun TestPlayerSide() {
    Column {
        PlayerSide(order = 0, eventGame = {})
        PlayerSide(order = 1, eventGame = {})
        PlayerSide(order = 2, eventGame = {})
        PlayerSide(order = 3, eventGame = {})
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerSide(order: Int, eventGame: (GameEvent) -> Unit) {

    val color = colorsPlayersWait[order]
    val colorSelect = colorsPlayers[order]
    val shape = shapes[order]

    var bgColor by remember { mutableStateOf(color) }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(color, shape)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event
                        .mimeTypes()
                        .contains(ClipDescription.MIMETYPE_TEXT_INTENT)
                },
                target = object : DragAndDropTarget {

                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        val indexCard =
                            event.toAndroidDragEvent().clipData.getIndexCard() ?: return false
                        eventGame(GameEvent.EventDragAndDropCard(indexCard, order))
                        return true
                    }

                    override fun onEntered(event: DragAndDropEvent) {
                        super.onEntered(event)
                        bgColor = colorSelect
                    }

                    override fun onExited(event: DragAndDropEvent) {
                        super.onExited(event)
                        bgColor = color
                    }

                }
            ),
    ) {}
}

private fun ClipData.getIndexCard(): Int? {
    return (0 until itemCount)
        .mapNotNull(::getItemAt).firstNotNullOfOrNull { item ->
            item.intent?.getStringExtra(DragDropConstants.DATA)?.takeIf { it.isNotEmpty() }
        }?.toInt()
}
