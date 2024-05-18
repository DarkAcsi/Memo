package com.coursework.memo.support_classes

import kotlinx.serialization.Serializable

@Serializable
data class GameSupport(
    val size: Int,
    val players: Int,
    val packImage: String,
)