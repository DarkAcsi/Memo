package com.coursework.memo.screens.settings.pages.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.coursework.memo.R

@Preview(showBackground = true)
@Composable
fun TestPageTheme(){
    PageTheme()
}

@Composable
fun PageTheme(){
    Image(
        BitmapPainter(ImageBitmap.imageResource(R.drawable.coming_soon)),
        null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight,
    )
}