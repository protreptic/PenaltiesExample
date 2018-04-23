package test.p00.data.repository.settings.datasource

import test.p00.data.repository.settings.datasource.impl.SharedPreferencesSettingsDataSource

object SettingsDataSourceFactory {

    fun create(): SettingsDataSource = SharedPreferencesSettingsDataSource()

}