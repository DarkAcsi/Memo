package com.coursework.memo.main.grobal_variables

import com.coursework.memo.preferences.local_settings.LocalSettingsData

object Constants {
    private const val PATH = "file:///android_asset/"
    const val PATH_IMAGE_PACKS = PATH + "images/"
    const val PATH_BACKSIDES = PATH + "backs/"

    val DEFAULT_LOCAL_SETTINGS_DATA = LocalSettingsData(
        backside = "1.jpg",
        imagePack = "Animals",
    )
}