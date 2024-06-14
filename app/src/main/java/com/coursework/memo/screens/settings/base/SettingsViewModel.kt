package com.coursework.memo.screens.settings.base

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.memo.R
import com.coursework.memo.main.grobal_variables.GlobalVariables
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import com.coursework.memo.preferences.usecases.SaveSettings
import com.coursework.memo.screens.settings.pages.backside.Backside
import com.coursework.memo.screens.settings.pages.images.ImagePack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveSettings: SaveSettings
) : ViewModel() {

    private val _settingsData = mutableStateOf(LocalSettingsData())
    private val settingsData: State<LocalSettingsData> = _settingsData

    private val _listImagePacks = mutableListOf<MutableState<ImagePack>>()
    val listImagePacks: List<State<ImagePack>> = _listImagePacks

    private val _listBacksides = mutableListOf<MutableState<Backside>>()
    val listBacksides: List<State<Backside>> = _listBacksides


    private var previousImagePack = 0
    private var previousBackside = 0

    fun initSettingsData(globalVariables: GlobalVariables, context: Context) {
        _settingsData.value = globalVariables.data
        val titles = context.resources.getStringArray(R.array.titles)
        val paths = context.resources.getStringArray(R.array.paths)
        _listImagePacks.clear()
        _listImagePacks.addAll(paths.mapIndexed { index, path ->
            mutableStateOf(
                ImagePack(
                    name = titles[index],
                    path = path,
                    titleImage = "file:///android_asset/images/$path/" + context.assets.list("images/$path")
                        ?.toList()?.get(0),
                    index = index,
                    selected = settingsData.value.imagePack == path
                )
            )
        })

        val backsidesCard = context.assets.list("backs")?.toList() ?: listOf()
        _listBacksides.clear()
        _listBacksides.addAll(backsidesCard.mapIndexed { index, backside ->
            mutableStateOf(
                Backside(
                    name = backside,
                    index = index,
                    selected = settingsData.value.backside == backside
                )
            )
        })

        listImagePacks.forEachIndexed { index, state ->
            if (state.value.selected) previousImagePack = index
        }
        listBacksides.forEachIndexed { index, state ->
            if (state.value.selected) previousBackside = index
        }
    }

    fun saveSettingsData(globalVariables: GlobalVariables) {
        globalVariables.setVariables(settingsData.value)
        viewModelScope.launch {
            saveSettings(settingsData.value)
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SelectImagePack -> selectImagePack(event)
            is SettingsEvent.SelectBackside -> selectBackside(event)
        }
    }

    private fun selectImagePack(event: SettingsEvent.SelectImagePack) {
        if (previousImagePack == event.index) return
        _listImagePacks[previousImagePack].value =
            _listImagePacks[previousImagePack].value.copy(selected = false)
        _listImagePacks[event.index].value =
            _listImagePacks[event.index].value.copy(selected = true)
        _settingsData.value =
            _settingsData.value.copy(imagePack = _listImagePacks[event.index].value.path)
        previousImagePack = event.index
    }

    private fun selectBackside(event: SettingsEvent.SelectBackside) {
        if (previousBackside == event.index) return
        _listBacksides[previousBackside].value =
            _listBacksides[previousBackside].value.copy(selected = false)
        _listBacksides[event.index].value = _listBacksides[event.index].value.copy(selected = true)
        _settingsData.value =
            _settingsData.value.copy(backside = _listBacksides[event.index].value.name)
        previousBackside = event.index
    }

}