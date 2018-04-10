package test.p00.data.repository.settings.datasource

import io.reactivex.Observable
import test.p00.data.model.settings.Settings

interface SettingsDataSource {

    /**
     *
     */
    fun fetch(): Observable<Settings>

    /**
     *
     */
    fun retain(settings: Settings): Observable<Settings>

}