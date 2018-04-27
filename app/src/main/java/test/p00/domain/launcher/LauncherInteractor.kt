package test.p00.domain.launcher

import io.reactivex.Observable
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.SettingsRepositoryFactory
import test.p00.domain.abs.Interactor

class LauncherInteractor(
        private val settingsRepository: SettingsRepository =
                                        SettingsRepositoryFactory.create()) : Interactor {

    fun shouldShowOnBoardingWizard(): Observable<Boolean> =
            settingsRepository
                    .fetch()
                    .map { settings -> !settings.wasOnBoardingWizardShown }

    fun shouldShowOnBoarding(): Observable<Boolean> =
            settingsRepository
                    .fetch()
                    .map { settings -> !settings.wasOnBoardingShown }

}