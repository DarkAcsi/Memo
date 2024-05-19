package com.coursework.memo.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.navigation.NavRealization
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.Routes
import com.coursework.memo.screens.support_classes.SizeSupport

@Preview(showSystemUi = true)
@Composable
fun TestHome(){
    val navigator = NavRealization(rememberNavController())
    ScreenHome(navigator).Screen()
}

class ScreenHome(private val navigator: Navigator) {

    @Composable
    fun Screen() {
        Home()
    }

    @Composable
    private fun Home() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                IconButton({}) {
                    Icon(Icons.Filled.Settings, stringResource(R.string.settings))
                }
            },
            floatingActionButtonPosition = FabPosition.Start,
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(stringResource(R.string.app_name), fontSize = 30.sp)
                    Button({ navigator.toSize(SizeSupport("Animals", "1", Routes.CLASSIC)) }){
                        Text(stringResource(R.string.classic_game))
                    }
                    Button({}){
                        Text(stringResource(R.string.find_game))
                    }
                    Button({}){
                        Text(stringResource(R.string.house_game))
                    }
                }
            }
        }
    }
}