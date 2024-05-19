package com.coursework.memo.preferences.usecases

import com.coursework.memo.preferences.local_settings.LocalSettings
import com.coursework.memo.preferences.local_settings.LocalSettingsData
import kotlinx.coroutines.flow.Flow

class ReadSettings(
    private val localSettings: LocalSettings
) {

    operator fun invoke(): Flow<LocalSettingsData> {
        return localSettings.readSettings()
    }
}