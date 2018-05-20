package test.p00.data.repository.settings.datasource

import test.p00.data.repository.settings.datasource.impl.SharedPreferencesSettingsDataSource
import test.p00.data.repository.settings.datasource.impl.SqliteSettingsDataStore

object SettingsDataSourceFactory {

    fun create(): SettingsDataSource = SharedPreferencesSettingsDataSource()

}