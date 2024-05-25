package com.coursework.memo.screens.games.find

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
class ViewModelFind @Inject constructor() : GameViewModel() {

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

    override fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.EventClickCard -> clickCard(event.index)
            is GameEvent.EventDropCard -> dropCard(event.indexCard, event.order)
        }
    }

    private var firstCard = -1
    private fun clickCard(index: Int) {
        if (firstCard >= 0)
            _listCards[firstCard].value = _listCards[firstCard].value.copy(open = false)
        _listCards[index].value = _listCards[index].value.copy(open = true)
        firstCard = index
        stateTopBar.value = stateTopBar.value.nextStepFindGame()
    }

    private fun dropCard(index: Int?, order: Int){
        if (index == null || firstCard < 0){
            return
        } else if (_listCards[firstCard].value.faceSide == _listCards[index].value.faceSide) {
            _listCards[index].value = _listCards[index].value.copy(open = true)
            stateTopBar.value = stateTopBar.value.resultFindGame(order, true)
            stateGame.value.stepsToWin.intValue -= 1
            firstCard = -1
            if (stateGame.value.stepsToWin.intValue == 0)
                stateGame.value = stateGame.value.copy(finishedGame = true)
        } else {
            stateTopBar.value = stateTopBar.value.resultFindGame(order, false)
            viewModelScope.launch {
                _listCards[index].value = _listCards[index].value.copy(open = true)
                delay(300)
                _listCards[index].value = _listCards[index].value.copy(open = false)
            }

        }
    }
}