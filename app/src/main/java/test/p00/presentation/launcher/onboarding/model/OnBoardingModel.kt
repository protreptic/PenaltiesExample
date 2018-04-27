package test.p00.presentation.launcher.onboarding.model

import test.p00.data.model.onboarding.OnBoarding

data class OnBoardingModel(val backgroundUri: String = "", val pages: List<OnBoardingPageModel> = listOf()) {

    object Mapper {

        fun map(onBoarding: OnBoarding) =
                    OnBoardingModel(onBoarding.backgroundUri,
                                    onBoarding.pages.map { OnBoardingPageModel.Mapper.map(it) })
    }

}