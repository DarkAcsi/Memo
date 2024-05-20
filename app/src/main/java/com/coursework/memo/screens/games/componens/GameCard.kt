package com.coursework.memo.screens.games.componens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Card(
    imagePack: String,
    image: String,
    modifier: Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("file:///android_asset/images/$imagePack/$image"),
        contentDescription = null,
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = modPadding.dp)
            .border(
                width = borderStroke.dp,
                color = Color.White,
                shape = RoundedCornerShape(percent = 10)
            )
            .clip(RoundedCornerShape(percent = 10)),
        contentScale = ContentScale.Crop,

        )
}