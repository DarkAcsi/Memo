package com.coursework.memo.preferences.grobal_variables

import com.coursework.memo.preferences.local_settings.LocalSettingsData

class GlobalVariablesImpl: GlobalVariables {

    var data = LocalSettingsData("", "")

    override fun setVariables(data: LocalSettingsData) {
        this.data = data
    }

    override fun getVariables(): LocalSettingsData {
        return this.data
    }
}