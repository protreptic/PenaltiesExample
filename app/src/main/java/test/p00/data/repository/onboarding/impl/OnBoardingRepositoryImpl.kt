package test.p00.data.repository.onboarding.impl

import test.p00.data.model.onboarding.OnBoarding
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.onboarding.datasource.OnBoardingDataSource

class OnBoardingRepositoryImpl(private val source: OnBoardingDataSource) : OnBoardingRepository {

    override fun fetch() = source.fetch()
    override fun retain(onBoarding: OnBoarding) = source.retain(onBoarding)

}