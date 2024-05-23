package com.coursework.memo.preferences.local_settings

import android.app.Application
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
import javax.inject.Inject

class LocalSettingsImpl @Inject constructor(
    private val application: Application
) : LocalSettings {

    override suspend fun saveSettings(localSettingsData: LocalSettingsData) {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.IMAGE_PACK] = localSettingsData.imagePack
            settings[PreferenceKeys.BACK_CARD] = localSettingsData.backside
        }
    }

    override fun readSettings(): Flow<LocalSettingsData> {
        return application.dataStore.data.map { preferences ->
            LocalSettingsData(
                preferences[PreferenceKeys.BACK_CARD] ?: "",
                preferences[PreferenceKeys.IMAGE_PACK] ?: "",
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