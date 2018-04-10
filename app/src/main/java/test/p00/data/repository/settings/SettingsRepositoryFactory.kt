package test.p00.data.repository.settings

import test.p00.data.repository.settings.datasource.SettingsDataSourceFactory
import test.p00.data.repository.settings.impl.SettingsRepositoryImpl

object SettingsRepositoryFactory {

    /**
     *
     */
    fun create(): SettingsRepository = SettingsRepositoryImpl(SettingsDataSourceFactory.create())

}