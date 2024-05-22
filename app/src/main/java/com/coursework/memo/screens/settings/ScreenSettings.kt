package com.coursework.memo.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.coursework.memo.R
import com.coursework.memo.main.grobal_variables.GlobalVariables
import com.coursework.memo.main.grobal_variables.GlobalVariablesImpl
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.navigation.NavigatorImpl

@Preview(showSystemUi = true)
@Composable
fun TestSettings() {
    val navigator = NavigatorImpl(rememberNavController())
    ScreenSettings(navigator, GlobalVariablesImpl())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSettings(navigator: Navigator, globalVariables: GlobalVariables) {
    val viewModel: SettingsViewModel = hiltViewModel()
    viewModel.initSettingsData(globalVariables)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton({ navigator.back() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back))
                    }
                },
                actions = {
                    IconButton({
                        globalVariables.setVariables(viewModel.settingsData.value)
                        navigator.back()
                    }) {
                        Icon(Icons.Filled.Close, stringResource(R.string.close))
                    }
                }
            )
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}

//@ExperimentalPagerApi
//@Composable
//fun AccompanistViewPagerExample() {
//    val pagerState = rememberPagerState()
//    HorizontalPager(state = pagerState) {
//        // Add your child composables here
//    }
//}