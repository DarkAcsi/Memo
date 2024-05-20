package com.coursework.memo.preferences

import com.coursework.memo.preferences.local_settings.LocalSettings
import com.coursework.memo.preferences.usecases.AppSettingsCases
import com.coursework.memo.preferences.usecases.ReadSettings
import com.coursework.memo.preferences.usecases.SaveSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppSettingsCases(
        localSettings: LocalSettings
    ): AppSettingsCases = AppSettingsCases(
        readSettings = ReadSettings(localSettings),
        saveSettings = SaveSettings(localSettings)
    )
}