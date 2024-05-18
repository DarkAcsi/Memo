package com.coursework.memo.support_classes

import kotlinx.serialization.Serializable

@Serializable
data class SizeSupport(
    val packImage: String,
    val route: String
)