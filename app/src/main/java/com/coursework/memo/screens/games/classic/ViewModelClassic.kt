package com.coursework.memo.screens.games.classic

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
class ViewModelClassic @Inject constructor() : GameViewModel() {

    override var stateGame = mutableStateOf(GameState())
        private set

    private val _listCards = mutableListOf<MutableState<CardState>>()
    override val listCards: List<State<CardState>> = _listCards

    override var stateTopBar = mutableStateOf(TopBarState(0))
        private set

    override fun initStateTopBar(players: Int) {
        stateTopBar.value = TopBarState(players)
    }

    override fun initCardStates(gameSettings: GameSettings, context: Context) {
        val images =
            context.assets.list("images/${gameSettings.imagePack}")?.toList()
        shuffle(images!!)

        val size = gameSettings.rows * gameSettings.columns / 2
        stateGame.value.stepsToWin.intValue = size

        val images2 = images.slice(0 until size) + images.slice(0 until size)
        shuffle(images2)
        _listCards.clear()
        _listCards.addAll(images2.map { image ->
            mutableStateOf(
                CardState(
                    backSide = Constants.PATH_BACKSIDES + gameSettings.backside,
                    faceSide = Constants.PATH_IMAGE_PACKS + "${gameSettings.imagePack}/$image",
                    open = false,
                )
            )
        })
    }

    private var firstCard = -1

    override fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.EventClickCard -> clickCard(event.index)
            else -> {}
        }
    }

    private fun clickCard(index: Int) {
        if (stateGame.value.showCards) return
        _listCards[index].value = _listCards[index].value.copy(open = true)
        if (firstCard < 0) {
            firstCard = index
        } else if (_listCards[firstCard].value.faceSide == _listCards[index].value.faceSide) {
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
            if (!win) {
                _listCards[firstCard].value = _listCards[firstCard].value.copy(open = false)
                _listCards[index].value = _listCards[index].value.copy(open = false)
            }
            firstCard = -1
            stateGame.value = stateGame.value.copy(showCards = false)
        }
    }

}