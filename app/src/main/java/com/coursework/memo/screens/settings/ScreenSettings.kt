package com.coursework.memo.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.coursework.memo.R
import com.coursework.memo.main.addition.BackHandler
import com.coursework.memo.main.grobal_variables.GlobalVariables
import com.coursework.memo.navigation.Navigator
import com.coursework.memo.screens.settings.base.SettingsViewModel
import com.coursework.memo.screens.settings.base.TabPage
import com.coursework.memo.screens.settings.base.TabSettings
import com.coursework.memo.screens.settings.pages.backside.PageBackside
import com.coursework.memo.screens.settings.pages.images.PageImages
import com.coursework.memo.screens.settings.pages.theme.PageTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ScreenSettings(navigator: Navigator, globalVariables: GlobalVariables) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val context = LocalContext.current
    LaunchedEffect(key1 = 1) {
        viewModel.initSettingsData(globalVariables, context)
    }

    BackHandler {
        viewModel.saveSettingsData(globalVariables)
        navigator.back()
    }

    val pagerSelect = rememberPagerState(TabPage.entries.size)
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton({
                        viewModel.saveSettingsData(globalVariables)
                        navigator.back()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back))
                    }
                }
            )
        }

    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabSettings(selectedIndex = pagerSelect.currentPage, onSelect = {
                scope.launch { pagerSelect.animateScrollToPage(it.ordinal) }
            })
            HorizontalPager(state = pagerSelect) { index ->
                Column(Modifier.fillMaxSize()) {
                    when (index) {
                        0 -> PageImages(viewModel.listImagePacks, viewModel::onEvent)
                        1 -> PageBackside(viewModel.listBacksides, viewModel::onEvent)
                        2 -> PageTheme()
                    }
                }
            }
        }
    }
}