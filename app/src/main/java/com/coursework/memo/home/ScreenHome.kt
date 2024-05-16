package com.coursework.memo.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.Routes

@Preview(showSystemUi = true)
@Composable
fun TestHome(){
    val navController = rememberNavController()
    ScreenHome(navController).Home()
}

class ScreenHome(private val navController: NavHostController) {

    @Composable
    fun Home() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBarHome() }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text("Memo", fontSize = 30.sp)
                    Button({ navController.navigate(Routes.Size.route) }){
                        Text("Классика")
                    }
                    Button({}){
                        Text("Найди!")
                    }
                    Button({}){
                        Text("Домик")
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarHome() {
        TopAppBar(
            title = {},
            actions = {
                Button({ }) {
                    Row(modifier = Modifier.width(120.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(Icons.Filled.Star, contentDescription = "?")
                        Text("Star", modifier = Modifier.fillMaxWidth(1f), textAlign = TextAlign.Center)
                    }
                }
            }
        )
    }
}