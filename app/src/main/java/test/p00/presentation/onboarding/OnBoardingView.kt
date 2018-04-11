package test.p00.presentation.onboarding

import test.p00.presentation.base.View
import test.p00.presentation.onboarding.model.OnBoardingModel

interface OnBoardingView : View {

    fun displayOnBoarding(onBoarding: OnBoardingModel)
    fun closeOnBoarding()

}