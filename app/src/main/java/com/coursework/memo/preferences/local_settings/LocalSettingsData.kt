package com.coursework.memo.preferences.local_settings

data class LocalSettingsData(
    var backside: String = "",
    var imagePack: String = "",
) {
    fun isEmpty(): Boolean {
        return backside == "" && imagePack == ""
    }
}
