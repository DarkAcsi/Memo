package com.coursework.memo.screens.settings.pages.images

data class ImagePack(
    val name: String,
    val path: String,
    val titleImage: String,
    val index: Int,
    val selected: Boolean = false,
)