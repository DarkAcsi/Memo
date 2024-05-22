package com.coursework.memo.screens.games.classic

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.ReadSettings
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.GameViewModel
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.base.states.GameState
import com.coursework.memo.screens.games.base.states.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Collections.shuffle
import javax.inject.Inject

@HiltViewModel
class ViewModelClassic @Inject constructor(
    readSettings: ReadSettings
) : GameViewModel() {

    private val _localSettingsData = mutableStateOf(LocalSettingsData("", ""))
    override val localSettingsData: State<LocalSettingsData> = _localSettingsData

    override var stateGame = mutableStateOf(GameState())
        private set

    private val _listCards = mutableListOf<MutableState<CardState>>()
    override val listCards: List<State<CardState>> = _listCards

    override var stateTopBar = mutableStateOf(TopBarState(2))
        private set

    init {
        readSettings().onEach { data ->
            _localSettingsData.value = data
            _localSettingsData.value = LocalSettingsData("1.jpg", "Animals")
            stateGame.value = stateGame.value.copy(loadedData = true)
            Log.d("Deb", "initLocalSettingsData")
        }.launchIn(viewModelScope)
        Log.d("Deb", "init viewModel")
    }

    override fun initStateTopBar(players: Int) {
        stateTopBar.value = stateTopBar.value.copy(players = players)
    }

    override fun initCardStates(rows: Int, columns: Int, context: Context) {
        if (!stateGame.value.loadedData) return
        viewModelScope.launch {
            Log.d("Deb", "initCardStates start")
            val images =
                context.assets.list("images/${localSettingsData.value.imagePack}")?.toList()
            shuffle(images!!)
            val size = rows * columns / 2
            val images2 = images.slice(0 until size) + images.slice(0 until size)
            shuffle(images2)
            _listCards.clear()
            _listCards.addAll(images2.map { image ->
                mutableStateOf(
                    CardState(
                        backSide = "file:///android_asset/backs/${localSettingsData.value.backSide}",
                        faceSide = "file:///android_asset/images/${localSettingsData.value.imagePack}/$image",
                        open = false,
                    )
                )
            })
            Log.d("Deb", "initCardStates")
        }
    }

    private var firstCard = -1

    override fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.EventClickCard -> clickCard(event)
        }
    }

    private fun clickCard(event: GameEvent.EventClickCard) {
        if (stateGame.value.showCards) return
        _listCards[event.index].value = _listCards[event.index].value.copy(open = true)
        if (firstCard < 0) {
            firstCard = event.index
        } else if (_listCards[firstCard].value.faceSide == _listCards[event.index].value.faceSide) {
            setPauseClick(true, event.index)
        } else {
            setPauseClick(false, event.index)
        }
    }

    private fun setPauseClick(win: Boolean, index: Int){
        stateGame.value = stateGame.value.copy(showCards = true)
        stateTopBar.value = stateTopBar.value.nextStep(win)
        viewModelScope.launch {
            delay(300)
            if (!win) {
                _listCards[firstCard].value = _listCards[firstCard].value.copy(open = false)
                _listCards[index].value =
                    _listCards[index].value.copy(open = false)
            }
            firstCard = -1
            stateGame.value = stateGame.value.copy(showCards = false)
        }
    }

}