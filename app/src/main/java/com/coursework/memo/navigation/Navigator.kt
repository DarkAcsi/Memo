package com.coursework.memo.navigation

import com.coursework.memo.screens.support_classes.GameSupport
import com.coursework.memo.screens.support_classes.SizeSupport


interface Navigator {

    fun back()

    fun toHome()

    fun toSize(support: SizeSupport)

    fun toGame(route: String, support: GameSupport)

}