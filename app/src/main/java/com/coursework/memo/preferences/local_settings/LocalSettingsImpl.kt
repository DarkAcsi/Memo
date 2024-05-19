package com.coursework.memo.preferences.local_settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.coursework.memo.preferences.PreferencesConstants
import com.coursework.memo.preferences.PreferencesConstants.SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalSettingsImpl(
    private val context: Context,
) : LocalSettings {

    override suspend fun saveSettings(localSettings: LocalSettingsData) {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.IMAGE_PACK] = localSettings.imagePack
            settings[PreferenceKeys.BACK_CARD] = localSettings.backCard
        }
    }

    override fun readSettings(): Flow<LocalSettingsData> {
        return context.dataStore.data.map { preferences ->
            LocalSettingsData(
                preferences[PreferenceKeys.IMAGE_PACK] ?: "",
                preferences[PreferenceKeys.BACK_CARD] ?: ""
            )
        }
    }
}

private val readOnlyProperty = preferencesDataStore(name = SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val IMAGE_PACK = stringPreferencesKey(PreferencesConstants.IMAGE_PACK)
    val BACK_CARD = stringPreferencesKey(PreferencesConstants.BACK_CARD)
}