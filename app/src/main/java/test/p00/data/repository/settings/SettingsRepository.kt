package test.p00.data.repository.settings

import io.reactivex.Observable
import test.p00.data.model.settings.Settings

interface SettingsRepository {

    /**
     *
     */
    fun fetch(): Observable<Settings>

    /**
     *
     */
    fun retain(settings: Settings): Observable<Settings>

}