package test.p00.domain.launcher

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observable.*
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.SettingsRepositoryFactory

class LauncherInteractor(
        private val repository: SettingsRepository =
                                SettingsRepositoryFactory.create()) {

    fun shouldShowOnBoardingWizard(): Observable<Boolean> =
            repository.fetch()
                      .flatMap { settings -> just(!settings.wasOnBoardingWizardShown) }

    fun shouldShowOnBoarding(): Observable<Boolean> =
        repository.fetch()
                  .flatMap { settings -> just(!settings.wasOnBoardingShown) }

    fun markOnBoardingAsShown(): Completable =
        repository.fetch()
                  .map { settings -> repository.retain(
                         settings.apply { wasOnBoardingShown = true }) }
                  .ignoreElements()

}