package test.p00.data.repository.settings.datasource

import test.p00.data.repository.settings.datasource.impl.SettingsRealmDataSource

object SettingsDataSourceFactory {

    fun create(): SettingsDataSource = SettingsRealmDataSource()

}