package com.coursework.memo.screens.games.find.drag_and_drop

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.unit.dp

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.cardSourceModifier(index: Int): Modifier {
    return Modifier
        .size(130.dp)
        .dragAndDropSource {
            detectTapGestures(
                onLongPress = {
                    startTransfer(
                        DragAndDropTransferData(
                            clipData = ClipData.newIntent(
                                "foodItem",
                                Intent(DragDropConstants.ACTION).apply {
                                    putExtra(
                                        DragDropConstants.DATA,
                                        index
                                    )
                                },
                            )
                        )
                    )
                }
            )
        }
}