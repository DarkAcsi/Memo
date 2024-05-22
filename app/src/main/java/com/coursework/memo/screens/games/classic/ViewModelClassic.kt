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
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.base.states.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Collections.shuffle
import javax.inject.Inject

@HiltViewModel
class ViewModelClassic @Inject constructor(
    readSettings: ReadSettings
) : GameViewModel() {

    private val _localSettingsData = mutableStateOf(LocalSettingsData("", ""))
    override val localSettingsData: State<LocalSettingsData> = _localSettingsData

    private var stateTopBar = mutableStateOf(TopBarState(2))

    private val _listCardStates = mutableListOf<CardState>()
    val listCardStates: List<CardState> = _listCardStates

    init {
//        readSettings().onEach { data ->
//            _localSettingsData.value = data
//        }.launchIn(viewModelScope)

        Log.d("Deb", "init viewModel")
        _localSettingsData.value = LocalSettingsData("1.jpg", "Animals")
    }

    override fun getStateTopBar(players: Int): TopBarState {
        stateTopBar.value = TopBarState(players)
        return stateTopBar.value
    }

    override fun initCardStates(rows: Int, columns: Int, context: Context) {
        viewModelScope.launch {
            val images =
                context.assets.list("images/${localSettingsData.value.imagePack}")?.toList()
            Log.d("Deb", "for 5 ${localSettingsData.value.imagePack}")
            shuffle(images!!)
            val size = rows * columns / 2
            Log.d("Deb", "images.size ${images.size} size $size")
            val images2 = images.slice(0 until size) + images.slice(0 until size)
            shuffle(images2)
            _listCardStates.clear()
            _listCardStates.addAll(images2.map { image ->
                CardState(
                    backSide = "file:///android_asset/backs/${localSettingsData.value.backSide}",
                    faceSide = "file:///android_asset/images/${localSettingsData.value.imagePack}/$image",
                    open = false
                )
            })
            Log.d("Deb", "in fun ${listCardStates.size}")
        }
    }

    private var firstCard = -1

    override fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.EventClickCard -> {
                viewModelScope.launch {
                    listCardStates[event.index].changeOpen(true)
                    if (firstCard < 0) {
                        firstCard = event.index
                    } else if (listCardStates[firstCard].faceSide == listCardStates[event.index].faceSide) {
                        stateTopBar.value = stateTopBar.value.nextStep(true)
                        firstCard = -1
                    } else {
                        stateTopBar.value = stateTopBar.value.nextStep(false)
                        listCardStates[firstCard].changeOpen(false)
                        listCardStates[event.index].changeOpen(false)
                    }
                }
            }
        }
    }
}