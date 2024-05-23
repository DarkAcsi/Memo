package com.coursework.memo.screens.settings.pages.backside

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.coursework.memo.R
import com.coursework.memo.screens.settings.base.SettingsEvent


@Preview(showBackground = true)
@Composable
fun TestCardBackside() {
    Column {
        CardBackside(
            card = Backside("", "", 3),
            select = {}
        )
        CardBackside(
            card = Backside("", "", 3, true),
            select = {}
        )
        CardBackside(
            card = Backside("", "", 3),
            select = {}
        )
        CardBackside(
            card = Backside("", "", 3),
            select = {}
        )
    }
}

@Composable
fun CardBackside(card: Backside, select: (SettingsEvent) -> Unit) {
    Button(
        { select(SettingsEvent.SelectImagePack(card.index)) },
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 2.dp)
            .border(
                6.dp,
                if (!card.selected) Color.Gray else Color.Blue,
                RoundedCornerShape(percent = 30)
            ),
        shape = RoundedCornerShape(percent = 30),
        colors = ButtonColors(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color.Transparent
        ),
        contentPadding = PaddingValues(4.dp)
    ) {
        AsyncImage(
            model = card.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            placeholder = BitmapPainter(ImageBitmap.imageResource(id = R.drawable.test_face_side)),
        )
    }
}
