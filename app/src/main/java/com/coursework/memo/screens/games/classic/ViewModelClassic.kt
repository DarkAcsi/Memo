package com.coursework.memo.screens.games.classic

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.ReadSettings
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.GameViewModel
import com.coursework.memo.screens.games.base.states.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Collections.shuffle
import javax.inject.Inject

@HiltViewModel
class ViewModelClassic @Inject constructor(
    readSettings: ReadSettings
) : GameViewModel() {

    private val _localSettingsData = mutableStateOf(LocalSettingsData("", ""))
    override val localSettingsData: State<LocalSettingsData> = _localSettingsData

    private val _stateTopBar = mutableStateOf(TopBarState(2))
    private val stateTopBar: State<TopBarState> = _stateTopBar

    init {
        readSettings().onEach { data ->
            _localSettingsData.value = data
        }.launchIn(viewModelScope)

        _localSettingsData.value = LocalSettingsData("1.jpg", "Animals")
        Log.d("Deb", "init viewModel")
    }

    override fun getStateTopBar(players: Int): TopBarState {
        _stateTopBar.value = TopBarState(players)
        return stateTopBar.value
    }

    override fun getImages(rows: Int, columns: Int, context: Context): List<String> {
        val images = context.assets.list("images/${localSettingsData.value.imagePack}")?.toList()
        shuffle(images!!)
        val size = rows * columns / 2
        val images2 = images.slice(1..size) + images.slice(1..size)
        shuffle(images2)
        Log.d("Deb", "in fun2 ${images2.size}")
        return images2.map{ "file:///android_asset/images$it" }
    }

    override fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.Lion -> {}
            is GameEvent.Cat -> {}
        }
    }

}