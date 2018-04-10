package test.p00.data.repository.onboarding

import test.p00.data.repository.onboarding.datasource.OnBoardingDataSourceFactory
import test.p00.data.repository.onboarding.impl.OnBoardingRepositoryImpl

object OnBoardingRepositoryFactory {

    /**
     * Возвращает хранилище приветственного экрана.
     */
    fun create(): OnBoardingRepository =
                  OnBoardingRepositoryImpl(OnBoardingDataSourceFactory.create())

}