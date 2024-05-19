package com.coursework.memo.preferences.usecases

import com.coursework.memo.preferences.local_settings.LocalSettings
import com.coursework.memo.preferences.local_settings.LocalSettingsData

class SaveSettings(
    private val localSettings: LocalSettings
) {
    suspend operator fun invoke(settings: LocalSettingsData) {
        localSettings.saveSettings(settings)
    }
}