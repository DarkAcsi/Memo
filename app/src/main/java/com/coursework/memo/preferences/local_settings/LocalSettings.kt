package com.coursework.memo.preferences.local_settings

import kotlinx.coroutines.flow.Flow

interface LocalSettings {

    suspend fun saveSettings(localSettings: LocalSettingsData)

    fun readSettings(): Flow<LocalSettingsData>

}