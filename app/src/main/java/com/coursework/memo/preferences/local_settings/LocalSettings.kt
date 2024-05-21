package com.coursework.memo.preferences.local_settings

import kotlinx.coroutines.flow.Flow

interface LocalSettings {

    suspend fun saveSettings(localSettingsData: LocalSettingsData)

    fun readSettings(): Flow<LocalSettingsData>

}