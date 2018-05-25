package test.p00.domain.onboarding

import io.reactivex.Observable
import test.p00.data.model.onboarding.OnBoarding
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.settings.SettingsRepository
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class OnBoardingInteractor
    @Inject constructor(private val onBoardingRepository: OnBoardingRepository,
                        private val settingsRepository: SettingsRepository) {

    fun displayOnBoarding(): Observable<OnBoarding> =
            settingsRepository
                    .fetch()
                    .map { settingsRepository.retain(it.apply { wasOnBoardingShown = true }) }
                    .flatMap { onBoardingRepository.fetch() }

}