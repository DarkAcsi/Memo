package com.coursework.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.games.GameClassic
import com.coursework.memo.home.ScreenHome
import com.coursework.memo.home.ScreenSize
import com.coursework.memo.ui.theme.MemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(Routes.Home.route) { ScreenHome(navController).Home() }
        composable(Routes.Size.route) { ScreenSize(navController).Size() }
        composable(Routes.GameClassic.route + "/{rows}") { argument ->
            val rows = argument.arguments?.getString("rows")
            GameClassic(navController).Game(rows!!.toInt()) }
    }
}

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Size : Routes("size")
    data object GameClassic : Routes("classic")
}
