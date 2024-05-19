package com.coursework.memo.navigation

import androidx.navigation.NavHostController
import com.coursework.memo.screens.support_classes.GameSupport
import com.coursework.memo.screens.support_classes.SizeSupport
import com.google.gson.Gson

class NavRealization(private val navController: NavHostController): Navigator {

    override fun back(){
        navController.navigateUp()
    }

    override fun toHome(){
        navController.popBackStack(Routes.HOME, inclusive = false)
    }

    override fun toSize(support: SizeSupport) {
        navController.navigate(Routes.SIZE + "/" + Gson().toJson(support))
    }

    override fun toGame(route: String, support: GameSupport){
        navController.navigate(route + "/" + Gson().toJson(support))
    }

}