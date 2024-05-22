package com.coursework.memo.preferences.grobal_variables

import com.coursework.memo.preferences.local_settings.LocalSettingsData

interface GlobalVariables {

    fun setVariables(data: LocalSettingsData)

    fun getVariables(): LocalSettingsData

}