package com.coursework.memo.screens.games.house

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.coursework.memo.main.grobal_variables.Constants
import com.coursework.memo.screens.games.base.GameEvent
import com.coursework.memo.screens.games.base.GameViewModel
import com.coursework.memo.screens.games.base.states.CardState
import com.coursework.memo.screens.games.base.states.GameState
import com.coursework.memo.screens.games.base.states.TopBarState
import com.coursework.memo.screens.games.support.GameSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Collections.shuffle
import javax.inject.Inject

@HiltViewModel
class ViewModelHouse @Inject constructor() : GameViewModel() {

    override var stateGame = mutableStateOf(GameState())
        private set

    override var stateTopBar = mutableStateOf(TopBarState(2))
        private set

    private val _listCards = mutableListOf<MutableState<CardState>>()
    override val listCards: List<State<CardState>> = _listCards

    override fun initStateTopBar(players: Int) {
        stateTopBar.value = TopBarState(players)
    }

    override fun initCardStates(gameSettings: GameSettings, context: Context) {
        viewModelScope.launch {
            val images =
                context.assets.list("images/${gameSettings.imagePack}")?.toList()
            shuffle(images!!)
            val size = gameSettings.rows * gameSettings.columns

            val images1: List<String>
            val images2: List<String>
            if (size <= images.size) {
                stateGame.value.stepsToWin.intValue = size
                images2 = images.slice(0 until stateGame.value.stepsToWin.intValue)
                images1 = images2.toMutableList()
            } else {
                stateGame.value.stepsToWin.intValue = images.size - (size - images.size)
                images2 = images.slice(0 until stateGame.value.stepsToWin.intValue)
                images1 =
                    images.slice(stateGame.value.stepsToWin.intValue until images.size) + images.toMutableList()
            }
            shuffle(images1)

            _listCards.clear()
            _listCards.addAll(images1.map { image ->
                mutableStateOf(
                    CardState(
                        backSide = Constants.PATH_BACKSIDES + gameSettings.backside,
                        faceSide = Constants.PATH_IMAGE_PACKS + "${gameSettings.imagePack}/$image",
                        open = false,
                    )
                )
            } + images2.map { image ->
                mutableStateOf(
                    CardState(
                        backSide = Constants.PATH_BACKSIDES + gameSettings.backside,
                        faceSide = Constants.PATH_IMAGE_PACKS + "${gameSettings.imagePack}/$image",
                        open = true,
                    )
                )
            })
        }
    }

    override fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.EventClickCard -> clickCard(event.index)
        }
    }

    private fun clickCard(index: Int) {
        if (stateGame.value.showCards) return
        _listCards[index].value = _listCards[index].value.copy(open = true)
        if (_listCards[index].value.faceSide == _listCards[listCards.size - stateGame.value.stepsToWin.intValue].value.faceSide) {
            stateGame.value.stepsToWin.intValue -= 1
            if (stateGame.value.stepsToWin.intValue == 0)
                stateGame.value = stateGame.value.copy(finishedGame = true)
            setPauseClick(true, index)
        } else {
            setPauseClick(false, index)
        }
    }

    private fun setPauseClick(win: Boolean, index: Int) {
        stateGame.value = stateGame.value.copy(showCards = true)
        stateTopBar.value = stateTopBar.value.nextStep(win)
        viewModelScope.launch {
            delay(300)
            if (!win) _listCards[index].value = _listCards[index].value.copy(open = false)
            stateGame.value = stateGame.value.copy(showCards = false)
        }
    }
}