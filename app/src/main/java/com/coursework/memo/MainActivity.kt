package com.coursework.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.coursework.memo.navigation.NavGraph
import com.coursework.memo.ui.theme.MemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        actionBar?.hide()
        setContent {
            MemoTheme {
//                SetBackground()
                NavGraph()
            }
        }
    }
}

// set containerColor = Color.Transparent in each screen
//@Composable
//fun SetBackground(){
//    Image(
//        BitmapPainter(ImageBitmap.imageResource(R.drawable.background)),
//        contentDescription = null,
//        contentScale = ContentScale.FillBounds,
//        modifier = Modifier.fillMaxSize()
//    )
//}