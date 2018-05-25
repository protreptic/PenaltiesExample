package test.p00.data.repository.settings.datasource.impl

import android.content.Context
import android.content.Context.MODE_PRIVATE
import io.reactivex.Observable
import test.p00.data.model.settings.Settings
import test.p00.data.repository.settings.datasource.SettingsDataSource
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class SharedPreferencesSettingsDataSource
    @Inject constructor (private val context: Context): SettingsDataSource {

    private val name = "default"
    private val fieldWasOnBoardingShown = "wasOnBoardingShown"
    private val fieldWasOnBoardingWizardShown = "wasOnBoardingWizardShown"

    override fun fetch(): Observable<Settings> = Observable.just(Settings().apply {
        context.getSharedPreferences(name, MODE_PRIVATE).let { storage ->
            wasOnBoardingShown = storage.getBoolean(fieldWasOnBoardingShown, false)
            wasOnBoardingWizardShown = storage.getBoolean(fieldWasOnBoardingWizardShown, false)
        }
    })

    override fun retain(settings: Settings): Observable<Settings> =
        Observable.just(settings.apply {
            context
                .getSharedPreferences(name, MODE_PRIVATE)
                .edit()
                .putBoolean(fieldWasOnBoardingShown, settings.wasOnBoardingShown)
                .putBoolean(fieldWasOnBoardingWizardShown, settings.wasOnBoardingWizardShown)
                .apply()
        })

    fun wipe() {
        context
            .getSharedPreferences(name, MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }

}