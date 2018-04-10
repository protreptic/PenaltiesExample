package test.p00.data.repository.settings.impl

import test.p00.data.model.settings.Settings
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.datasource.SettingsDataSource

class SettingsRepositoryImpl(private val source: SettingsDataSource) : SettingsRepository {

    override fun fetch() = source.fetch()
    override fun retain(settings: Settings) = source.retain(settings)

}