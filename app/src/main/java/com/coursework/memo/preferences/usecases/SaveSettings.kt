package com.coursework.memo.preferences.usecases

import com.coursework.memo.preferences.local_settings.LocalSettings
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import javax.inject.Inject

class SaveSettings @Inject constructor(
    private val localSettings: LocalSettings
) {

    suspend operator fun invoke(settings: LocalSettingsData) {
        localSettings.saveSettings(settings)
    }

}