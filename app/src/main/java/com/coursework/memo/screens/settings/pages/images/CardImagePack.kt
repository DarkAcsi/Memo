package com.coursework.memo.screens.settings.pages.images

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coursework.memo.R
import com.coursework.memo.screens.settings.base.SettingsEvent

@Preview(showBackground = true)
@Composable
fun TestCardImagePack() {
    Column {
        CardImagePack(
            card = ImagePack("name", "", "", 0),
            select = {},
        )
        CardImagePack(
            card = ImagePack("name", "", "", 0, true),
            select = {},
        )
    }
}

@Composable
fun CardImagePack(card: ImagePack, select: (SettingsEvent) -> Unit) {
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = card.titleImage,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Fit,
                placeholder = BitmapPainter(ImageBitmap.imageResource(id = R.drawable.test_face_side)),
            )
            Text(
                card.name,
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Color.DarkGray
            )
        }
    }
}