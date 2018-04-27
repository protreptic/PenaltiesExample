package test.p00.domain.launcher

import io.reactivex.Observable
import io.reactivex.Observable.just
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.SettingsRepositoryFactory

class LauncherInteractor(
        private val settingsRepository: SettingsRepository =
                                        SettingsRepositoryFactory.create()) {

    fun shouldShowOnBoardingWizard(): Observable<Boolean> =
            settingsRepository.fetch()
                      .flatMap { settings -> just(!settings.wasOnBoardingWizardShown) }

    fun shouldShowOnBoarding(): Observable<Boolean> =
        settingsRepository.fetch()
                  .flatMap { settings -> just(!settings.wasOnBoardingShown) }

}