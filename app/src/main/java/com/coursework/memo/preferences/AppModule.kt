package com.coursework.memo.preferences

import com.coursework.memo.preferences.local_settings.LocalSettings
import com.coursework.memo.preferences.local_settings.LocalSettingsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLocalSettings(localSettingsImpl: LocalSettingsImpl): LocalSettings

}