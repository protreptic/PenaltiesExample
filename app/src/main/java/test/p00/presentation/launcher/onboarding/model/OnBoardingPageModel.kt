package test.p00.presentation.launcher.onboarding.model

import test.p00.data.model.onboarding.OnBoardingPage

data class OnBoardingPageModel(val message: String, val contentUri: String) {

    object Mapper {

        fun map(onBoardingPage: OnBoardingPage) =
                    OnBoardingPageModel(onBoardingPage.message,
                                        onBoardingPage.contentUri)

    }

}