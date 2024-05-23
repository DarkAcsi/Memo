package com.coursework.memo.screens.settings.base

sealed class SettingsEvent {

    data class SelectImagePack(val index: Int) : SettingsEvent()
    data class SelectBackside(val index: Int) : SettingsEvent()

}