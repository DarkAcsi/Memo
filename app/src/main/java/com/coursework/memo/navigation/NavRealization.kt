package com.coursework.memo.navigation

import androidx.navigation.NavHostController
import com.coursework.memo.support_classes.GameSupport
import com.coursework.memo.support_classes.SizeSupport
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NavRealization(private val navController: NavHostController): Navigator {

    override fun back(){
        navController.navigateUp()
    }

    override fun toHome(){
        navController.popBackStack(Routes.HOME, inclusive = false)
    }

    override fun toSize(support: SizeSupport) {
        navController.navigate(Routes.SIZE + Json.encodeToString(support))
    }

    override fun toGame(route: String, support: GameSupport){
        navController.navigate(route + Json.encodeToString(support))
    }

}