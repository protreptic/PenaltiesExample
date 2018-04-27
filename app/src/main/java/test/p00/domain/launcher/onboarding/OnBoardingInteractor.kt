package test.p00.domain.launcher.onboarding

import io.reactivex.Observable
import test.p00.data.model.onboarding.OnBoarding
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.onboarding.OnBoardingRepositoryFactory
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.SettingsRepositoryFactory

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class OnBoardingInteractor(
        private val onBoardingRepository: OnBoardingRepository =
                                          OnBoardingRepositoryFactory.create(),
        private val settingsRepository: SettingsRepository =
                                        SettingsRepositoryFactory.create()) {

    fun displayOnBoarding(): Observable<OnBoarding> =
            settingsRepository.fetch()
                    .flatMap { settingsRepository.retain(it.apply { wasOnBoardingShown = true }) }
                    .flatMap { onBoardingRepository.fetch() }

}