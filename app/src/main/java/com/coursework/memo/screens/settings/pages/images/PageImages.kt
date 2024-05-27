package com.coursework.memo.screens.settings.pages.images

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coursework.memo.screens.settings.base.SettingsEvent

@Preview(showBackground = true)
@Composable
fun TestPageImages() {
    PageImages(
        images = listOf(),
        select = {}
    )
}

@Composable
fun PageImages(
    images: List<State<ImagePack>>,
    select: ( SettingsEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),

        ) {
        items(images) { pack ->
            CardImagePack(card = pack.value, select = select)
        }
    }
}