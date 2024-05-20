package com.coursework.memo.navigation

import androidx.navigation.NavHostController
import com.coursework.memo.screens.support_classes.GameSupport
import com.google.gson.Gson

class NavigatorImpl(private val navController: NavHostController): Navigator {

    override fun back(){
        navController.navigateUp()
    }

    override fun toHome(){
        navController.popBackStack(Routes.Home.route, inclusive = false)
    }

    override fun toSize(routeGame: String) {
        navController.navigate(Routes.Size.route + "/" + routeGame)
    }

    override fun toGame(route: String, support: GameSupport){
        navController.navigate(route + "/" + Gson().toJson(support))
    }

    override fun toSettings() {
        navController.navigate(Routes.Settings.route)
    }

}