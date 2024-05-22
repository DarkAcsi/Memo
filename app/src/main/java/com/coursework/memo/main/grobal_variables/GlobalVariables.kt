package com.coursework.memo.main.grobal_variables

import com.coursework.memo.preferences.local_settings.LocalSettingsData

interface GlobalVariables {

    val data : LocalSettingsData

    fun setVariables(data: LocalSettingsData)

}