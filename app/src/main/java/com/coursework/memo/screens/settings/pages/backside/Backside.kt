package com.coursework.memo.screens.settings.pages.backside

import com.coursework.memo.main.grobal_variables.Constants

data class Backside(
    val name: String,
    val index: Int,
    val selected: Boolean = false,
    val image: String = Constants.PATH_BACKSIDES + name,
)