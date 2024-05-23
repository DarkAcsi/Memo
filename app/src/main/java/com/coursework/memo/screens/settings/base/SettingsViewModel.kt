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
//
//    private val _settingData = mutableStateOf(LocalSettingsData())
//    private val settingsData: State<LocalSettingsData> = _settingData
//
//    private val _settingData = mutableStateOf(LocalSettingsData())
//    private val settingsData: State<LocalSettingsData> = _settingData


    fun initSettingsData(globalVariables: GlobalVariables, context: Context) {
        _settingsData.value = globalVariables.data
//        val images =
//            context.assets.list("images")?.toList()
        val titles = context.resources.getStringArray(R.array.titles)
        val paths = context.resources.getStringArray(R.array.paths)
        _listImagePacks.clear()
        _listImagePacks.addAll(paths.mapIndexed { index, path ->
            mutableStateOf(ImagePack(
                name = titles[index],
                titleImage = context.assets.list("images/$path")?.toList()?.get(0) ?: "",
                index = index,
                selected = settingsData.value.imagePack == titles[index]
            ))
        })
    }

    fun saveSettingsData() {
        viewModelScope.launch {
            saveSettings(settingsData.value)
        }
    }

}