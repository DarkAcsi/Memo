package com.coursework.memo.main.grobal_variables

import com.coursework.memo.preferences.local_settings.LocalSettingsData

class GlobalVariablesImpl : GlobalVariables {

    override var data = LocalSettingsData("", "")
        private set

    override fun setVariables(data: LocalSettingsData) {
        this.data = data
    }

}